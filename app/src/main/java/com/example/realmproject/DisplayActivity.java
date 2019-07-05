package com.example.realmproject;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;

public class DisplayActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private static final String TAG="Finder";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        //initialising recyclerView to show data from database via Adapter
        mRecyclerView=findViewById(R.id.personRecycler);
        //Reflecting results in Log after creating RecyclerView
        Log.i(TAG, "onCreate: before adapter creation" + "Also the recyclerView id is " + mRecyclerView.getId());
        Realm realm=Realm.getDefaultInstance();
      final RealmResults<MyPerson> myPersonRealmResults=realm.where(MyPerson.class).findAll();// creating a realm result of MyPerson type
        MyAdapter myAdapter=new MyAdapter(myPersonRealmResults,this);
        Log.i(TAG, "onCreate: After adapter creation");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.i(TAG, "onCreate: After linear layout manager creation and setting");
        mRecyclerView.setAdapter(myAdapter);
        Log.i(TAG, "onCreate: After setAdapter");
    }
}
