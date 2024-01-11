package fr.uge.foodstock.foodstockversionf.repository;

import fr.uge.foodstock.foodstockversionf.entity.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    Optional<ShoppingList> findByUserId(long userId);
}
