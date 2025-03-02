package com.example.realmretrofit.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmretrofit.Interfaces.ProductListenerInterface;
import com.example.realmretrofit.R;
import com.example.realmretrofit.RealmClasses.ProductsRealm;
import com.example.realmretrofit.RealmClasses.RealmOperations;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements ProductListenerInterface {
    private Context context;
    private RecyclerView rvProductList,rvAddedProductList;
    private ProductListAdapter productListAdapter;
    private AddedProductListAdapter addedProductListAdapter;
    private List<ProductsRealm> productsRealmList;
    private AppCompatTextView txtTotalQty, txtSubtotal, txtTotalTax, txtTotalAmount;
    private List<ProductsRealm> addedProductList;

    private double subtotal = 0, totalQty = 0, totalTax = 0, totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        context = ProductListActivity.this;

        rvProductList = findViewById(R.id.rv_product_list);
        rvAddedProductList = findViewById(R.id.rv_added_product_list);

        txtTotalQty = findViewById(R.id.txt_total_qty);
        txtSubtotal = findViewById(R.id.txt_subtotal);
        txtTotalTax = findViewById(R.id.txt_total_tax);
        txtTotalAmount = findViewById(R.id.txt_total_amount);

        rvProductList.setLayoutManager(new LinearLayoutManager(this));
        rvAddedProductList.setLayoutManager(new LinearLayoutManager(this));

        productsRealmList = new RealmOperations().getProductList();
//FIRST RECYCLER VIEW
        if (productsRealmList == null || productsRealmList.size() == 0) {
            insertSomeProductsToRealm();
            productsRealmList = new RealmOperations().getProductList();
        }
        if (productsRealmList != null) {
            productListAdapter = new ProductListAdapter(context, productsRealmList, this);
        }
        rvProductList.setAdapter(productListAdapter);

//SECOND RECYCLER VIEW
        addedProductList = new ArrayList<>();
        if(addedProductList!=null) {
            addedProductListAdapter =new AddedProductListAdapter(context, addedProductList);
        }
        rvAddedProductList.setAdapter(addedProductListAdapter);
    }

    private void insertSomeProductsToRealm() {
        new RealmOperations().insertProductDetails(new ProductsRealm(R.drawable.south,R.drawable.add,"Sourth Indian Meal", 10.0, 10.0));
        new RealmOperations().insertProductDetails(new ProductsRealm(R.drawable.north,R.drawable.add,"North Indian Meal", 20.0, 10.0));
        new RealmOperations().insertProductDetails(new ProductsRealm(R.drawable.dessert,R.drawable.add,"Desserts", 30.0, 10.0));
        new RealmOperations().insertProductDetails(new ProductsRealm(R.drawable.dosa,R.drawable.add,"Masala Dosa", 40.0, 10.0));
        new RealmOperations().insertProductDetails(new ProductsRealm(R.drawable.milkshakes,R.drawable.add,"Milkshake", 50.0, 10.0));
        new RealmOperations().insertProductDetails(new ProductsRealm(R.drawable.icecream,R.drawable.add,"Ice Cream", 60.0, 10.0));
        new RealmOperations().insertProductDetails(new ProductsRealm(R.drawable.juice,R.drawable.add,"Juice", 70.0, 10.0));
        new RealmOperations().insertProductDetails(new ProductsRealm(R.drawable.bevarages,R.drawable.add,"Bevarages", 80.0, 10.0));
    }

    @Override
    public void onProductAdd(ProductsRealm productsRealm) {
        addedProductList.add((productsRealm));
        addedProductListAdapter.notifyDataSetChanged();
        calculateTotals();
    }

    private void calculateTotals() {
        subtotal = 0;
        totalQty = 0;
        totalTax = 0;
        totalAmount = 0;
        for (int p = 0; p < addedProductList.size(); p++) {
            ProductsRealm product = addedProductList.get(p);//FIRST ITEM VALUE
            totalQty = totalQty + 1;//0+1=1
            subtotal = subtotal + product.getProductPrice();
            double productTaxAmount = 0;
            productTaxAmount = (product.getProductTaxRate() / 100) * product.getProductPrice();//10/100*500=50Rs.
            totalTax = totalTax + productTaxAmount;
        }

        totalAmount = subtotal + totalTax;
        displayTotals();
    }

    private void displayTotals() {
        txtTotalQty.setText("" + totalQty);//FIRST 0
        txtSubtotal.setText("Rs " + subtotal);
        txtTotalTax.setText("Rs " + totalTax);
        txtTotalAmount.setText("Rs " + totalAmount);
    }
}




