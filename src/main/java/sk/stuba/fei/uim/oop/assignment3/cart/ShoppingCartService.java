package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.item.Item;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequest;

import java.util.ArrayList;

@Service
public class ShoppingCartService implements IShoppingCartService{
    private ShoppingCartRepository repository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository repository){
        this.repository = repository;
    }

    @Override
    public ShoppingCart findById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public ShoppingCart create() {
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setShoppingList(new ArrayList<>());
        newShoppingCart.setPayed(true);
        return this.repository.save(newShoppingCart);
    }

    @Override
    public void deleteShoppingCart(long id) {
        ShoppingCart foundShoppingCart = findById(id);
        this.repository.delete(foundShoppingCart);
    }
}
