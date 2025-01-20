package com.shobuj.controller;

import com.shobuj.entity.Restaurant;
import com.shobuj.entity.User;
import com.shobuj.request.CreateRestaurantRequest;
import com.shobuj.response.MessegeResponse;
import com.shobuj.service.RestaurantService;
import com.shobuj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(req, user);
      return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurant(id, req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/jwt")
    public ResponseEntity<Restaurant> updateRestaurantByJwt(@RequestBody CreateRestaurantRequest req,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurantByJwt(req, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessegeResponse> deleteRestaurant(
                                                       @RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurant(id);
        MessegeResponse res = new MessegeResponse();
        res.setMessage("Restaurant deleted successfully.");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
                                                            @RequestHeader("Authorization") String jwt,
                                                            @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String jwt
                                                             ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    // Update Status of all restaurant by openingHours and closingHours automatically
    @PutMapping("/status")
    public ResponseEntity<MessegeResponse> updateRestaurantStatusByTime() throws Exception {
        restaurantService.updateRestaurantStatusByTime();
        MessegeResponse res = new MessegeResponse();
        res.setMessage("Restaurant status updated successfully.");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
