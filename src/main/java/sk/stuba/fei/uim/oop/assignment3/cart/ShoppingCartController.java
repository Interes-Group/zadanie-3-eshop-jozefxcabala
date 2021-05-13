package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.product.ProductResponse;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService service;

    @PostMapping()
    public ResponseEntity<ShoppingCartResponse> addShoppingCart(){
        return new ResponseEntity<>(new ShoppingCartResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartResponse> getShoppingCartById(@PathVariable("id") Long id){
        return new ResponseEntity<>(new ShoppingCartResponse(this.service.findById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        this.service.deleteShoppingCart(id);
    }
}