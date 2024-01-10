package fr.uge.foodstock.foodstockversionf.entity;


import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long barcode;
    private String name;
    private Integer lipid;
    private Integer glucid;
    private Integer protein;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser MyUser;

    @ManyToOne
    private ShoppingList shoppingList;

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBarcode() {
        return barcode;
    }

    public Integer getGlucid() {
        return glucid;
    }

    public Integer getLipid() {
        return lipid;
    }

    public Integer getProtein() {
        return protein;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public fr.uge.foodstock.foodstockversionf.entity.MyUser getMyUser() {
        return MyUser;
    }

    public void setGlucid(Integer glucid) {
        this.glucid = glucid;
    }

    public void setLipid(Integer lipid) {
        this.lipid = lipid;
    }

    public void setMyUser(fr.uge.foodstock.foodstockversionf.entity.MyUser myUser) {
        MyUser = myUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public void updateFields(Product updatedProduct) {
        if (updatedProduct.getName() != null) {
            this.name = updatedProduct.getName();
        }

        if (updatedProduct.getLipid() != null) {
            this.lipid = updatedProduct.getLipid();
        }

        if (updatedProduct.getGlucid() != null) {
            this.glucid = updatedProduct.getGlucid();
        }

        if (updatedProduct.getProtein() != null) {
            this.protein = updatedProduct.getProtein();
        }
    }
}
