package com.example.project1;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Product extends RealmObject {
    @Required
    public String product;
    public Product() {
    }
    public Product(String product) {
        this.product = product;
    }
    public String getProduct() {
            return product;
        }
        public void setProduct(final String product) {
            this.product = product;
        }
}
