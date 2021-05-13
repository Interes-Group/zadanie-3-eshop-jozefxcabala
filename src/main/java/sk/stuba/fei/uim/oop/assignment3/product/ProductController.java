package sk.stuba.fei.uim.oop.assignment3.product;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping()
    public List<ProductResponse> getAllProducts(){
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request){
        return new ResponseEntity<>(new ProductResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(new ProductResponse(this.service.findById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        return new ProductResponse(this.service.updateProduct(id, request.getName(), request.getDescription()));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        this.service.deleteProduct(id);
    }


    @GetMapping("/{id}/amount")
    public ResponseEntity<ProductAmountResponse> getProductAmount(@PathVariable("id") Long id){
        return new ResponseEntity<>(new ProductAmountResponse(this.service.findById(id)), HttpStatus.OK);
    }

    @PostMapping("/{id}/amount")
    public ResponseEntity<ProductAmountResponse> incrementProductAmount(@PathVariable("id") Long id, @RequestBody ProductRequest request){
        return new ResponseEntity<>(new ProductAmountResponse(this.service.incrementProductAmount(id, request)), HttpStatus.OK);
    }

}
