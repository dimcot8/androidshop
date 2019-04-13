package com.example.project1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static com.example.project1.MainActivity.adapter;
import static com.example.project1.MainActivity.myRealm;
import static com.example.project1.MainActivity.productRealmList;
import static com.example.project1.MainActivity.realmList;

public class DeletedItems extends AppCompatActivity {
//    static  RealmList<Product>realmList;
    static MyAdapter2 myAdapter;
 static    ListView listView1;
    static View view1;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deleted_items);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView1 = findViewById(R.id.listView1);

//        realmList.add(hvv);

        myAdapter = new MyAdapter2(this, R.layout.adapter_layout2, realmList);
        listView1.setAdapter(myAdapter);


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1 = LayoutInflater.from(DeletedItems.this).inflate(R.layout.alert_layout, null);
                editText = view1.findViewById(R.id.ent);
                new AlertDialog.Builder(DeletedItems.this)
                        .setTitle("Create new product")
                        .setMessage("Put down the name of the new product")
                        .setView(view1)
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Add",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        myRealm.executeTransaction(new Realm.Transaction() {
                                            @Override
                                            public void execute(Realm realm) {
                                                final Product product1 = myRealm.createObject(Product.class);
                                                product1.setProduct(editText.getText().toString());
                                                realmList.add(product1);
                                                myAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                })

                        .create()
                        .show();
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, final int position,
                                    long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(DeletedItems.this)
                        .setTitle("Removing the product")
                        .setMessage("Are you sure u want to remove the proyour cart?")
                        .setView(view1)
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                productRealmList.remove(adapter.getItem(position));
//                                adapter.notifyDataSetChanged();

                                Product toRemove = realmList.get(position);
                                realmList.remove(toRemove);
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .show();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.clear:
                realmList.removeAll(realmList);
                myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onDestroy() {
        Realm.getDefaultInstance().close();
        super.onDestroy();
//            myRealm.close();
//            myRealm = null;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
