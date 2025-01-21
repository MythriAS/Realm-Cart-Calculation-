package com.example.realmretrofit.RealmClasses;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProductsRealm extends RealmObject {
    @PrimaryKey
    private String productUUID = UUID.randomUUID().toString();
    private String productName;
    private Double productPrice;
    private Double productTaxRate;

    public ProductsRealm() {
    }

    public ProductsRealm(String productName, Double productPrice, Double productTaxRate) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productTaxRate = productTaxRate;
    }


    public String getProductUUID() {
        return productUUID;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Double getProductTaxRate() {
        return productTaxRate;
    }
}
