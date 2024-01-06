package fr.uge.foodstock.foodstockversionf.repository;

import fr.uge.foodstock.foodstockversionf.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
