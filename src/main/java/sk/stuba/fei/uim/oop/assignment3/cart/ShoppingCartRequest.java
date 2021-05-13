package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.item.Item;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

import java.util.List;

@Setter
@Getter
public class ShoppingCartRequest {
    private List<Item> shoppingList;
    private boolean payed;
}
