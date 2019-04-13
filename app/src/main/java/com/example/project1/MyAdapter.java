package com.example.project1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

//import static com.example.project1.MainActivity.myRealm;
import static com.example.project1.DeletedItems.myAdapter;
import static com.example.project1.MainActivity.adapter;
import static com.example.project1.MainActivity.myRealm;
import static com.example.project1.MainActivity.productRealmList;
import static com.example.project1.MainActivity.view1;
import static java.util.Collections.copy;

class MyAdapter extends ArrayAdapter<Product> {
    int myResource;
    private Context myContext;

    public MyAdapter(Context context, int resource, RealmList<Product> objects) {
        super(context, resource, objects);
        myContext = context;
        myResource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String title = getItem(position).getProduct();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(myContext);
            convertView = inflater.inflate(myResource, parent, false);

//            final Button add = convertView.findViewById(R.id.add);


            TextView product = convertView.findViewById(R.id.textView);
            product.setText(title);

//            return convertView;

        }
        return convertView;
    }
}