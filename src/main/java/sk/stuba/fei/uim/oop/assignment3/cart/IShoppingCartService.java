package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.item.ItemRequest;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

public interface IShoppingCartService {
    ShoppingCart create();
    ShoppingCart findById(Long id);
    void deleteShoppingCart(long id);
    ShoppingCart addProductToCart(Long id, ItemRequest request) throws BadRequestException, NotFoundException;
    ShoppingCart payForShoppingCart(Long id) throws BadRequestException;
}
