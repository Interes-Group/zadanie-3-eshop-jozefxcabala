package sk.stuba.fei.uim.oop.assignment3.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.item.ItemRequest;
import sk.stuba.fei.uim.oop.assignment3.product.ProductAmountResponse;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequest;
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

    @PostMapping("/{id}/add")
    public ResponseEntity<ShoppingCartResponse> addProductToCart(@PathVariable("id") Long id, @RequestBody ItemRequest request) throws BadRequestException, NotFoundException {
        return new ResponseEntity<>(new ShoppingCartResponse(this.service.addProductToCart(id, request)), HttpStatus.OK);
    }

    @GetMapping("/{id}/pay")
    public ResponseEntity<String> payForShoppingCart(@PathVariable("id") Long id) throws BadRequestException{
        return new ResponseEntity<>(Integer.toString(this.service.payForShoppingCart(id).getPriceOfShoppingCart()), HttpStatus.OK);
    }
}