package fr.uge.foodstock.foodstockversionf.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

   /* @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Product> products; */

}
