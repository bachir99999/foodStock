package fr.uge.foodstock.foodstockversionf.controller;

import fr.uge.foodstock.foodstockversionf.entity.MyUser;
import fr.uge.foodstock.foodstockversionf.entity.Product;
import fr.uge.foodstock.foodstockversionf.repository.UserRepository;
import jdk.javadoc.doclet.Reporter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class  UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<MyUser>> getAllUsers() {
        List<MyUser> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<MyUser> getUserById(@PathVariable Long idUser) {
        MyUser user = userRepository.findById(idUser).get();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MyUser> createUser(@RequestBody MyUser user) {
        if (user.getProducts() != null) {
            for (Product product : user.getProducts()) {
                product.setMyUser(user); // Associer chaque produit à l'utilisateur
            }
        }
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}

