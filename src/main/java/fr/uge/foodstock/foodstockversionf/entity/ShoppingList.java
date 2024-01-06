package fr.uge.foodstock.foodstockversionf.entity;


import jakarta.persistence.*;

import java.util.List;

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

}
