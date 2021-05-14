package sk.stuba.fei.uim.oop.assignment3.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.ShoppingCart;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "id" })
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;
    private int amount;

    public Item(Long productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public void incrementAmount(int amount){
        this.amount += amount;
    }
}
