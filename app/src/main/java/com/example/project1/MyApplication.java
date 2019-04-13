package com.example.project1;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Realm.init(this);
        final RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("myRealmDataBase")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .addModule(new RealmModule())
                .build();
        Realm.setDefaultConfiguration(configuration);

    }
}
