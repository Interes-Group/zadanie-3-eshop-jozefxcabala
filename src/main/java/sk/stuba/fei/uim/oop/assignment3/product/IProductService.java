package sk.stuba.fei.uim.oop.assignment3.product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product findById(Long id);
    Product updateProduct(long id, String name, String description);
    void deleteProduct(long id);
    Product getProductAmount(Long id);
    Product incrementProductAmount(Long id, ProductRequest request);
}
