package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.item.Item;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ShoppingCartResponse {
    private Long id;
    private List<Item> shoppingList;
    private boolean payed;


    public ShoppingCartResponse(ShoppingCart s) {
        this.id = s.getId();
        this.shoppingList = s.getShoppingList();
        this.payed = s.isPayed();
    }
}
