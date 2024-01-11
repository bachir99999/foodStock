package fr.uge.foodstock.foodstockversionf.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Product> products;

    public Recipe(Long id, String name, Product product){
        setId(id);
        setName(name);
        products = List.of(product);
    }

    public Recipe(){}

    public Long getId() { return id; }

    public String getName() { return name; }

    public List<Product> getProducts() { return products; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setProducts(List<Product> products) { this.products = products; }
}
