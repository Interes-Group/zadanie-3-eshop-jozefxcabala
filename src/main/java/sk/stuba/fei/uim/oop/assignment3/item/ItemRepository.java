package sk.stuba.fei.uim.oop.assignment3.item;

import org.springframework.data.repository.CrudRepository;
import sk.stuba.fei.uim.oop.assignment3.cart.ShoppingCart;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
