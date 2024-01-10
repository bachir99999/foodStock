package fr.uge.foodstock.foodstockversionf.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String userName;
    private String email;

    private String password;


    @OneToMany(mappedBy = "myUser")
    private List<Product> products;

    public MyUser(String firstname, String userName, String email, String password){
        this.firstname = firstname;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public MyUser(MyUser user){
        System.out.println();
    }

    public  MyUser(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
