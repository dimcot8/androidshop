package com.example.project1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import io.realm.RealmList;

import static android.media.CamcorderProfile.get;
import static com.example.project1.DeletedItems.listView1;
import static com.example.project1.DeletedItems.myAdapter;
import static com.example.project1.MainActivity.realmList;

public class MyAdapter2  extends ArrayAdapter<Product> {
    int myResource;
    private Context myContext;

    public MyAdapter2(Context context, int resource, RealmList<Product> objects) {
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
//            final   Button delete = convertView.findViewById(R.id.delete);
//            delete.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View v) {
//
//                        }
//                    });


        }

        TextView product = convertView.findViewById(R.id.textV);
        product.setText(title);

        return convertView;

    }
}