package sk.stuba.fei.uim.oop.assignment3.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.item.Item;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Item> shoppingList;
    private boolean payed;

    private int priceOfShoppingCart;

    public Item findItem(Long id){
        for (Item item : this.shoppingList) {
            if (item.getProductId().equals(id))
                return item;
        }

        return null;
    }

    public void calculatePriceOfShoppingCart(int price){
        this.priceOfShoppingCart += price;
    }
}
