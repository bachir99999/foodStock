package fr.uge.foodstock.foodstockversionf.controller;

import fr.uge.foodstock.foodstockversionf.entity.MyUser;
import fr.uge.foodstock.foodstockversionf.entity.Product;
import fr.uge.foodstock.foodstockversionf.entity.ShoppingList;
import fr.uge.foodstock.foodstockversionf.repository.ProductRepository;
import fr.uge.foodstock.foodstockversionf.repository.ShoppingListRepository;
import fr.uge.foodstock.foodstockversionf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ShoppingList")
public class ShoppingListController {
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{idUser}")
    public ResponseEntity<ShoppingList> getShoppingList(@PathVariable("idUser") long idUser) {
        var shoppingList = shoppingListRepository.findById(idUser).get();
        if (shoppingList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shoppingList, HttpStatus.OK);
    }

    @GetMapping("/{idUser}/products/{barcode}")
    public ResponseEntity<Product> getProductsFromShoppingList(@PathVariable("idUser") long idUser, @PathVariable("barcode") long barcode) {
        var shoppingListOptional = shoppingListRepository.findById(idUser);

        if (shoppingListOptional.isPresent()) {
            var shoppingList = shoppingListOptional.get();
            var productOptional = shoppingList.getProducts().stream()
                    .filter(product -> product.getBarcode() == barcode)
                    .findFirst();

            return productOptional.map(product -> ResponseEntity.ok(product))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{idUser}/products/{barcode}")
    public ResponseEntity<Product> patchProductInShoppingList(
            @PathVariable("idUser") long idUser,
            @PathVariable("barcode") long barcode,
            @RequestBody Product updatedProduct) {

       var shoppingListOptional = shoppingListRepository.findById(idUser);

        if (shoppingListOptional.isPresent()) {
            var shoppingList = shoppingListOptional.get();
            var productOptional = shoppingList.getProducts().stream()
                    .filter(product -> product.getBarcode() == barcode)
                    .findFirst();

            if (productOptional.isPresent()) {
                var existingProduct = productOptional.get();
                existingProduct.updateFields(updatedProduct);

                shoppingListRepository.save(shoppingList);

                return ResponseEntity.ok(existingProduct);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idUser}/products")
    public ResponseEntity<Product> postProductToShoppingList(
            @PathVariable("idUser") long idUser,
            @RequestBody Product newProduct) {

        var shoppingListOptional = shoppingListRepository.findById(idUser);

        if (shoppingListOptional.isPresent()) {
            var shoppingList = shoppingListOptional.get();
            shoppingList.addProduct(newProduct);
            shoppingListRepository.save(shoppingList);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idUser}/products/{barcode}")
    public ResponseEntity<Void> deleteProductFromShoppingList(
            @PathVariable("idUser") long idUser,
            @PathVariable("barcode") long barcode) {

        Optional<ShoppingList> shoppingListOptional = shoppingListRepository.findById(idUser);

        if (shoppingListOptional.isPresent()) {
            ShoppingList shoppingList = shoppingListOptional.get();
            Optional<Product> productOptional = shoppingList.getProducts().stream()
                    .filter(product -> product.getBarcode() == barcode)
                    .findFirst();

            if (productOptional.isPresent()) {
                Product productToDelete = productOptional.get();
                shoppingList.removeProduct(productToDelete);
                shoppingListRepository.save(shoppingList);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<ShoppingList> createShoppingList(@PathVariable("idUser") long idUser, @RequestBody ShoppingList shoppingList) {
        var userOptional = userRepository.findById(idUser);

        if (userOptional.isPresent()) {
            var user = userOptional.get();
            shoppingList.setMyUser(user);
            var savedShoppingList = shoppingListRepository.save(shoppingList);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedShoppingList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
