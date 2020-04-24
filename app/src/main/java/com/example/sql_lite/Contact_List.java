package com.example.sql_lite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.sql_lite.MainActivity.contactList;

public class Contact_List extends AppCompatActivity {
    ListView contact_list;
    SQLiteDatabase databaseNow;
    static ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__list);
        contact_list=findViewById(R.id.contact_list);
        databaseNow=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        contact_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        adapter=new ContactAdapter(Contact_List.this,contactList);
        contact_list.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Cursor c=databaseNow.rawQuery("select * from contact",null);
        int nameIndex=c.getColumnIndex("name");
        int mobileIndex=c.getColumnIndex("email");
        int emailIndex=c.getColumnIndex("mobile");
        int imageIndex=c.getColumnIndex("image");
        int idIndex=c.getColumnIndex("id");
        if(c.moveToFirst()){
            contactList.clear();
            do{
                users_list user=new users_list(c.getString(idIndex),
                        c.getString(nameIndex),
                        c.getString(emailIndex),
                        c.getString(mobileIndex),
                        c.getString(imageIndex));
                contactList.add(user);
                Log.i("DETAILS",c.getString(nameIndex)+" "+c.getString(idIndex));
                for(int i=0;i<contactList.size();i++){
                    Log.i("NOW",contactList.get(i).getId()+"  "+contactList.get(i).getName());
                }
            }while (c.moveToNext());
        }
    }
}
