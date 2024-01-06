package fr.uge.foodstock.foodstockversionf.repository;

import fr.uge.foodstock.foodstockversionf.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
