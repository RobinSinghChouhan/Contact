package com.example.sql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.sql_lite.Contact_List.adapter;
import static com.example.sql_lite.MainActivity.contactList;

public class ContactDetails extends AppCompatActivity {
    ImageView image;
    TextView name,mobile,email;
    Button delete;
    SQLiteDatabase database1;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        image=findViewById(R.id.imageView);
        delete=findViewById(R.id.delete);
        database1=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        Intent intent=getIntent();
        name.setText(intent.getStringExtra("Name"));
        mobile.setText(intent.getStringExtra("Mobile"));
        email.setText(intent.getStringExtra("Email"));
        String img=intent.getStringExtra("Image");
        id=intent.getStringExtra("Id");
        image.setImageURI(Uri.parse(img));
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(id);
            }
        });
    }
    public void deleteData(String id1){
        String sql="delete from contact where id=?";
        SQLiteStatement statement=database1.compileStatement(sql);
        statement.bindString(1,id1);
        statement.execute();
//        adapter.notifyDataSetChanged();
        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        Log.i("D",id1);
        adapter.notifyDataSetChanged();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
