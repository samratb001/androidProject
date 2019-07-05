package com.example.realmproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    }

    public void onToggle(View view){        //Male Female toggle portion
        Boolean state=swt.isChecked();
        if(state) {swt.setText("Female");}
        else swt.setText("Male");
    }

    //DataBaseStore
    public void onSaveButton(View view){
         Realm realm=Realm.getDefaultInstance();
         realm.beginTransaction();
         MyPerson myPerson=realm.createObject(MyPerson.class, System.currentTimeMillis()/1000); //object is being created with id according to creation
         try{
             myPerson.setName(sname.getText().toString());
             myPerson.setDept(sdept.getText().toString());
             myPerson.setPhone(sphone.getText().toString());
             myPerson.setRoll(Integer.parseInt(sroll.getText().toString()));
             myPerson.setGender(swt.getText().toString());
             realm.commitTransaction();
             Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex) {

            realm.cancelTransaction();
            Toast.makeText(mContext, "Failure" + ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

     public void onDisplay(View view){

         Intent intent=new Intent(this,DisplayActivity.class);
         startActivity(intent);

       }

  }



