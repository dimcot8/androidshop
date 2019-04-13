package com.example.project1;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmResults;

import static com.example.project1.DeletedItems.myAdapter;
import static io.realm.RealmObject.getRealm;
import static io.realm.com_example_project1_ProductRealmProxy.insertOrUpdate;

public class MainActivity extends AppCompatActivity {

    static   View view1;
    static MyAdapter adapter;
    static ListView listView;
    static RealmList<Product> productRealmList;
    static Realm myRealm = Realm.getDefaultInstance();
    static  RealmList<Product>realmList;
    ImageButton cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realmList = new RealmList<>();
        cart = findViewById(R.id.cart);
        listView = findViewById(R.id.listView);

        Product refrigerator = new Product("Refrigerator");
        Product microwave = new Product("Microwave");
        Product computer = new Product("Computer");
        Product notebook = new Product("Notebook");
         Product phone = new Product("Phone");
        Product TV = new Product("TV");
        Product watch = new Product("Watch");


        productRealmList = new RealmList<>();
        productRealmList.add(refrigerator);
        productRealmList.add(microwave);
        productRealmList.add(computer);
        productRealmList.add(notebook);
        productRealmList.add(phone);
        productRealmList.add(TV);
        productRealmList.add(watch);

        adapter = new MyAdapter(this, R.layout.adapter_layout, productRealmList);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, final int position,
                                    long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose the action");
                builder.setMessage("Do you want to remove the product or to add it to your cart?");
                builder.setView(view1);
                builder.setNeutralButton("Cancel", null);
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                                productRealmList.remove(adapter.getItem(position));
//                                adapter.notifyDataSetChanged();

                        Product toRemove = productRealmList.get(position);
                        productRealmList.remove(toRemove);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                myRealm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
//productRealmList.remove(position);
                                        realmList.add(productRealmList.get((position)));
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                AlertDialog alertDialog = builder.show();
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeletedItems.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onDestroy() {
            Realm.getDefaultInstance().close();
        super.onDestroy();
    }


    @Override
    public void setActionBar(android.widget.Toolbar toolbar) {
        super.setActionBar(toolbar);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }
}