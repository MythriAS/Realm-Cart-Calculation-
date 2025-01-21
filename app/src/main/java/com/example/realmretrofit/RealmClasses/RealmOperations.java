package com.example.realmretrofit.RealmClasses;

import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmOperations {
    public List<ProductsRealm> getProductList() {
        List<ProductsRealm> productsRealmList = null;
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<ProductsRealm> _productRealmList = realm.where(ProductsRealm.class).findAll();
            if (_productRealmList != null) {
                productsRealmList = realm.copyFromRealm(_productRealmList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productsRealmList;
    }


    public void insertProductDetails(ProductsRealm productsRealm) {
        try (Realm realm = Realm.getDefaultInstance()) {

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm _realm) {
                    _realm.insert(productsRealm);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


