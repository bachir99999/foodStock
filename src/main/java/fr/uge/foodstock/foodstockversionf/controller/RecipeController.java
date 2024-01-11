package fr.uge.foodstock.foodstockversionf.controller;

import fr.uge.foodstock.foodstockversionf.entity.Product;
import fr.uge.foodstock.foodstockversionf.entity.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class  RecipeController {
    private List<Recipe> recipes = List.of(new Recipe(1L, "grec", new Product(1L, 12345L)));

    @GetMapping
    public ResponseEntity<List<Recipe>> getRecipes(@RequestBody Product product) {
        return new ResponseEntity<>(recipes.stream().filter(r -> r.getProducts().stream().anyMatch(p -> p.getBarcode().equals(product.getBarcode()))).toList(), HttpStatus.OK);
    }
}

