package fr.uge.foodstock.foodstockversionf.entity;


import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long barcode;
    private String name;
    private Integer lipid;
    private Integer glucid;
    private Integer protein;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser MyUser;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    private ShoppingList shoppingList;






}
