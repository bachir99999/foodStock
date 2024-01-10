package fr.uge.foodstock.foodstockversionf.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class ShoppingList {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser user;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL)
    private List<Product> products;


    public ShoppingList(){}

    public Long getId() {
        return id;
    }
    public MyUser getUser() {
        return user;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setMyUser(MyUser user) {
        this.user = user;
    }

    public void addProduct(Product product){
        products.add(Objects.requireNonNull(product));
    }

    public void removeProduct(Product product){
        products.remove(product);
    }
}
