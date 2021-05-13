package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.product.Product;

public interface IShoppingCartService {
    ShoppingCart create();
    ShoppingCart findById(Long id);
    void deleteShoppingCart(long id);
}
