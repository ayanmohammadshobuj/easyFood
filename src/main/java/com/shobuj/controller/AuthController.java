package com.shobuj.controller;

import com.shobuj.assets.entity.ProfileImages;
import com.shobuj.config.JwtProvider;
import com.shobuj.entity.Cart;
import com.shobuj.enums.USER_ROLE;
import com.shobuj.entity.User;
import com.shobuj.repository.CartRepository;
import com.shobuj.repository.UserRepository;
import com.shobuj.request.LoginRequest;
import com.shobuj.response.AuthResponse;
import com.shobuj.service.impl.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if(isEmailExist != null){
            throw new Exception("Email is already used");
        }
        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepository.save(cart);

        ProfileImages profileImages = new ProfileImages();
        profileImages.setUser(savedUser);
        userRepository.save(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register Success");
        authResponse.setRole(savedUser.getRole());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req) throws Exception {
        String username = req.getEmail();
        String password = req.getPassword();
        Authentication authentication = authenticate(username, password);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Success");
//        authResponse.setCode("200");
        authResponse.setRole(USER_ROLE.valueOf(role));
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

//    @GetMapping("/email")
//    public ResponseEntity<ResponseDTO<UserC>> getUserByEmail(@RequestBody String email) throws Exception {
//
//        ResponseDTO<UserDTO> response = new ResponseDTO<>();
//
//        UserDTO userDTO = modelMapper.map(userService.userByEmail(email), UserDTO.class);
//
//        response.setPayload(userDTO);
//        response.setMessage("Success");
//        response.setCode("200");
//        response.setHttpStatus(HttpStatus.OK);
//
//        return new ResponseEntity<>(response, response.getHttpStatus());
//    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


}
