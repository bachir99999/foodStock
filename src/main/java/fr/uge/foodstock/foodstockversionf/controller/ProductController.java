package fr.uge.foodstock.foodstockversionf.controller;

import fr.uge.foodstock.foodstockversionf.entity.MyUser;
import fr.uge.foodstock.foodstockversionf.entity.Product;
import fr.uge.foodstock.foodstockversionf.repository.ProductRepository;
import fr.uge.foodstock.foodstockversionf.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/users")
public class ProductController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{idUser}/products")
    public ResponseEntity<List<Product>> getProductsOfUser(@PathVariable Long idUser) {
        var user = userRepository.findById(idUser);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get().getProducts(), HttpStatus.OK);
    }

    @GetMapping("/{idUser}/products/{barcode}")
    public ResponseEntity<Product> getProductOfUserWithBarcode(@PathVariable Long idUser, @PathVariable Long barcode){
        var user = userRepository.findById(idUser);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var filteredProducts = user.get().getProducts().stream().filter(p -> p.getBarcode().equals(barcode)).toList();
        if(filteredProducts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(filteredProducts.get(0), HttpStatus.OK);
    }
}

