package com.example.sql_lite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    EditText name,email,mobile;
    ImageView add_image;
    Uri imageUri;
    String imagePath;
    static ArrayList<users_list> contactList;
    Bitmap bitmap;
    Button add_data,list_users;
    private static final int PICK_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        add_image=findViewById(R.id.add_image);
        add_data=findViewById(R.id.add_data);
        contactList=new ArrayList<>();
        list_users=findViewById(R.id.list_users);
        database=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        database.execSQL("create table if not exists contact(id integer primary key,name varchar,email varchar,mobile varchar,image varchar)");
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage();
            }
        });
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
        list_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });
      //  database.execSQL("DELETE FROM contact");
    }

    private void addImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri=data.getData();
            add_image.setImageURI(imageUri);
         imagePath=imageUri.toString();
            Log.i("Detail",imagePath);
        }
    }
    private void addData(){
        String user_name=name.getText().toString();
        String user_email=email.getText().toString();
        String user_mobile=mobile.getText().toString();
        if(!user_name.equals("") && !user_email.equals("") && !user_mobile.equals("")
        && imageUri!=null){
            try{
                String sql="insert into contact(name,email,mobile,image) values(?,?,?,?)";
                SQLiteStatement statement=database.compileStatement(sql);
                statement.bindString(1,user_name);
                statement.bindString(2,user_email);
                statement.bindString(3,user_mobile);
                statement.bindString(4,imagePath);
                statement.execute();
                Toast.makeText(this,"Database Success!",Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                mobile.setText("");
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"Fill all credentials!",Toast.LENGTH_SHORT).show();
        }
    }

    public void showData(){
//        Cursor c=database.rawQuery("select * from contact",null);
//        int nameIndex=c.getColumnIndex("name");
//        int mobileIndex=c.getColumnIndex("email");
//        int emailIndex=c.getColumnIndex("mobile");
//        int imageIndex=c.getColumnIndex("image");
//        int idIndex=c.getColumnIndex("id");
//        if(c.moveToFirst()){
//            contactList.clear();
//            do{
//           users_list user=new users_list(c.getString(idIndex),
//                        c.getString(nameIndex),
//                        c.getString(emailIndex),
//                        c.getString(mobileIndex),
//                        c.getString(imageIndex));
//                contactList.add(user);
//                Log.i("DETAILS",c.getString(nameIndex)+"  "+c.getString(idIndex));
//                for(int i=0;i<contactList.size();i++){
//                    Log.i("NOW",contactList.get(i).getName());
//                }
//            }while (c.moveToNext());
//        }
        startActivity(new Intent(this,Contact_List.class));
    }
}
