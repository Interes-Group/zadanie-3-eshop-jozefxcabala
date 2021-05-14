package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.item.Item;
import sk.stuba.fei.uim.oop.assignment3.item.ItemRepository;
import sk.stuba.fei.uim.oop.assignment3.item.ItemRequest;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequest;

import java.util.ArrayList;

@Service
public class ShoppingCartService implements IShoppingCartService{
    private ShoppingCartRepository repository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductRepository productRepository;

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
        newShoppingCart.setPayed(false); //uprav na true
        return this.repository.save(newShoppingCart);
    }

    @Override
    public void deleteShoppingCart(long id) {
        ShoppingCart foundShoppingCart = findById(id);
        this.repository.delete(foundShoppingCart);
    }

    @Override
    public ShoppingCart addProductToCart(Long id, ItemRequest request) throws BadRequestException{
        ShoppingCart foundShoppingCart = findById(id);
        Product foundProduct = this.productService.findById(request.getProductId());

        if(foundShoppingCart.isPayed()){
            throw new BadRequestException();
        }

        if(request.getAmount() > foundProduct.getAmount()){
            throw new BadRequestException();
        }

        Item foundItem = foundShoppingCart.findItem(request.getProductId());

        if(foundItem != null){
            int price = request.getAmount()*foundProduct.getPrice();
            foundItem.incrementAmount(request.getAmount());
            foundProduct.decrementAmount(request.getAmount());
            foundShoppingCart.calculatePriceOfShoppingCart(price);
            this.itemRepository.save(foundItem);
        }
        else{
            int price = request.getAmount()*foundProduct.getPrice();
            Item newItem = new Item();
            newItem.setProductId(request.getProductId());
            newItem.incrementAmount(request.getAmount());
            foundProduct.decrementAmount(request.getAmount());
            foundShoppingCart.getShoppingList().add(newItem);
            foundShoppingCart.calculatePriceOfShoppingCart(price);
            this.itemRepository.save(newItem);
        }

        this.productRepository.save(foundProduct);
        return this.repository.save(foundShoppingCart);
    }

    @Override
    public ShoppingCart payForShoppingCart(Long id) throws BadRequestException {
        ShoppingCart foundShoppingCart = findById(id);

        if(foundShoppingCart.isPayed())
            throw new BadRequestException();
        else
            foundShoppingCart.setPayed(true);

        return this.repository.save(foundShoppingCart);
    }
}
