package com.example.realmretrofit.RealmClasses;

import android.widget.ImageView;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProductsRealm extends RealmObject {
    @PrimaryKey
    private String productUUID = UUID.randomUUID().toString();
    private int productImage;
    private int productAdd;
    private String productName;
    private Double productPrice;
    private Double productTaxRate;

    public ProductsRealm() {
    }

    public ProductsRealm(int productImage, int productAdd, String productName, Double productPrice, Double productTaxRate) {
        this.productImage = productImage;
        this.productAdd = productAdd;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productTaxRate = productTaxRate;
    }

    public String getProductUUID() {
        return productUUID;
    }

    public int getProductImage() {
        return productImage;
    }

    public int getProductAdd() {
        return productAdd;
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
