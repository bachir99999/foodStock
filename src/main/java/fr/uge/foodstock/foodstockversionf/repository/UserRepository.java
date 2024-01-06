package fr.uge.foodstock.foodstockversionf.repository;

import fr.uge.foodstock.foodstockversionf.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {
}
