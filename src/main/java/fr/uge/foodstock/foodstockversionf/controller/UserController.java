package fr.uge.foodstock.foodstockversionf.controller;

import fr.uge.foodstock.foodstockversionf.entity.MyUser;
import fr.uge.foodstock.foodstockversionf.entity.Product;
import fr.uge.foodstock.foodstockversionf.repository.ProductRepository;
import fr.uge.foodstock.foodstockversionf.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    @Autowired
    private ProductRepository productRepository;

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
    @Transactional
    public ResponseEntity<MyUser> createUser(@RequestBody MyUser user) {
        MyUser savedUser = userRepository.save(user);

        if (savedUser.getProducts() != null) {
            for (Product product : savedUser.getProducts()) {
                product.setMyUser(savedUser);
                productRepository.save(product);
            }
        }
        List<Product> p = user.getProducts();
        var  l = p.size();
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


}

