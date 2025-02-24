package com.shobuj.controller;

import com.shobuj.entity.Cart;
import com.shobuj.entity.CartItem;
import com.shobuj.entity.User;
import com.shobuj.request.AddCartItemRequest;
import com.shobuj.request.UpdateCartItemRequest;
import com.shobuj.service.CartService;
import com.shobuj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest request,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.addItemToCart(request, jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @RequestBody UpdateCartItemRequest request,
            @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(request.getCartItemId(), request.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.removeItemFromCart(id, jwt);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart/calculate")
    public ResponseEntity<Double> calculateCartTotals(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        double total = cartService.calculateCartTotals(cart);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    // Delete by food id
    @DeleteMapping("/cart-item/food/{foodId}/remove")
    public ResponseEntity<Cart> removeCartItemByFoodId(
            @PathVariable Long foodId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.removeItemFromCartByFoodId(foodId, jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Update by food id
    @PutMapping("/cart-item/food/{foodId}/update")
    public ResponseEntity<CartItem> updateCartItemQuantityByFoodId(
            @PathVariable Long foodId,
            @RequestBody UpdateCartItemRequest request,
            @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantityByFoodId(foodId, request.getQuantity(), jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    // Get cart by food id
    @GetMapping("/cart-item/food/{foodId}")
    public ResponseEntity<CartItem> getCartItemByFoodId(
            @PathVariable Long foodId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.getCartItemByFoodId(foodId, jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
}