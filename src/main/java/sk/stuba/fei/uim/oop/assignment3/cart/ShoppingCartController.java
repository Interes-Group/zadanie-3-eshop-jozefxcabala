package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService service;

    @PostMapping()
    public ResponseEntity<ShoppingCartResponse> addShoppingCart(){
        return new ResponseEntity<>(new ShoppingCartResponse(this.service.create()), HttpStatus.CREATED);
    }
}