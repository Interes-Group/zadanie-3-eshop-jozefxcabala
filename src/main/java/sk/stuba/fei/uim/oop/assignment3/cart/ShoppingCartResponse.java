package sk.stuba.fei.uim.oop.assignment3.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.item.Item;

import java.util.List;

@Getter
@JsonIgnoreProperties(value = { "priceOfShoppingCart" })
public class ShoppingCartResponse {
    private Long id;
    private List<Item> shoppingList;
    private boolean payed;
    private int priceOfShoppingCart;


    public ShoppingCartResponse(ShoppingCart s) {
        this.id = s.getId();
        this.shoppingList = s.getShoppingList();
        this.payed = s.isPayed();
        this.priceOfShoppingCart = s.getPriceOfShoppingCart();
    }
}
