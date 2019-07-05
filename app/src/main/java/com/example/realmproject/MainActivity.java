package com.example.realmproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Switch swt;
    private EditText sname;
    private EditText sdept;
    private EditText sroll;
    private EditText sphone;
    private ImageView img_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        sname=findViewById(R.id.editText);   //name
        sdept=findViewById(R.id.editText2);  //dept
        sroll=findViewById(R.id.editText3);  //roll
        sphone=findViewById(R.id.editText4); //phone
        swt=findViewById(R.id.switch1);      //gender
        img_view=findViewById(R.id.avatar);
    }

    public void onToggle(View view){//Male Female toggle portion
        Boolean state=swt.isChecked();
        if(state) {
            swt.setText("Female");
        //   img_view.setBackgroundResource(R.drawable.avatar_girl);
        }
        else {swt.setText("Male");
         // img_view.setBackgroundResource(R.drawable.avatar_male);
        }
    }

    //DataBaseStore
    public void onSaveButton(View view){
        if(sphone.getText().toString().length()!=10){
            Toast.makeText(this, "Phone Number should be 10 digits", Toast.LENGTH_SHORT).show();
            sphone.requestFocus();
        }
         else {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            MyPerson myPerson = realm.createObject(MyPerson.class, System.currentTimeMillis() / 1000); //object is being created with id according to creation
            try {
                myPerson.setName(sname.getText().toString());
                myPerson.setDept(sdept.getText().toString());
                myPerson.setPhone(sphone.getText().toString());
                myPerson.setRoll(Integer.parseInt(sroll.getText().toString()));
                myPerson.setGender(swt.getText().toString());
                realm.commitTransaction();
                Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();

            }catch (Exception ex) {
                realm.cancelTransaction();
                Toast.makeText(mContext, "Failure" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

     public void onDisplay(View view){

             Intent intent=new Intent(this,DisplayActivity.class);
             startActivity(intent);
      }

  }



