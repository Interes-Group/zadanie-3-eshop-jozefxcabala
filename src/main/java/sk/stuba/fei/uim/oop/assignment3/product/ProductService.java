package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService implements IProductService{
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Product updateProduct(long id, String name, String description) {
        Product foundProduct = findById(id);
        if(name != null)
            foundProduct.setName(name);
        if(description != null)
            foundProduct.setDescription(description);
        return foundProduct;
    }

    @Override
    public void deleteProduct(long id) {
        Product foundProduct = findById(id);
        this.repository.delete(foundProduct);
    }

    @Override
    public Product getProductAmount(Long id) {
        return findById(id);
    }

    @Override
    public Product incrementProductAmount(Long id, ProductRequest request) {
        Product foundProduct = findById(id);
        foundProduct.incrementAmount(request.getAmount());
        return foundProduct;
    }

    @Override
    public Product create(ProductRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setAmount(request.getAmount());
        newProduct.setUnit(request.getUnit());
        newProduct.setPrice(request.getPrice());
        return this.repository.save(newProduct);
    }
}
