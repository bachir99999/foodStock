package fr.uge.foodstock.foodstockversionf.repository;

import fr.uge.foodstock.foodstockversionf.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
}
