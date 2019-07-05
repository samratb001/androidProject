package com.example.realmproject;

import android.app.Application;

import io.realm.Realm;

public class RealmInitial extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
