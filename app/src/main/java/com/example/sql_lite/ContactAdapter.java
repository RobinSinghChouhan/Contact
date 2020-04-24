package com.example.sql_lite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.sql_lite.MainActivity.contactList;

public class ContactAdapter extends BaseAdapter {
private Context context;
public ContactAdapter(Context context, ArrayList<users_list> contactList){
this.context=context;
}
    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    final ViewHolder holder;
    if(convertView==null){
        holder=new ViewHolder();
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.contact_view,null);
        holder.contactName=convertView.findViewById(R.id.textViewName);
        holder.contactImage=convertView.findViewById(R.id.user_profile_pic);
        holder.slide=convertView.findViewById(R.id.slide);
        convertView.setTag(holder);
    }else{
        holder=(ViewHolder)convertView.getTag();
    }
    final users_list user=contactList.get(position);
    holder.contactName.setText(user.getName());


        holder.slide.setTag(R.integer.btn_slide_pos,position);
        holder.slide.setTag(R.integer.btn_slide_view,convertView);
        holder.slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View tempView=(View)holder.slide.getTag(R.integer.btn_slide_view);
                Integer pos=(Integer)holder.slide.getTag(R.integer.btn_slide_pos);
                Log.i("Condition:","Success"+contactList.get(position).getName());
                Intent intent=new Intent(context.getApplicationContext(),ContactDetails.class);
                intent.putExtra("Name",contactList.get(position).getName());
                intent.putExtra("Email",contactList.get(position).getEmail());
                intent.putExtra("Mobile",contactList.get(position).getMobile());
                intent.putExtra("Image",contactList.get(position).getImage());
                intent.putExtra("Id",contactList.get(position).getId());
                ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context,holder.contactImage,"img");
                context.startActivity(intent,optionsCompat.toBundle());
            }
        });


  //  holder.contactImage.setImageURI(Uri.parse(contactList.get(position).getImage()));
          Picasso.get().load(Uri.parse(contactList.get(position).getImage())).into(holder.contactImage);
    return convertView;
    }
    public class ViewHolder{
        private ImageView contactImage;
        private TextView contactName;
        private LinearLayout slide;
    }
//    public Bitmap getImageFromDb(byte[] img){
//    return BitmapFactory.decodeByteArray(img,0,img.length);
//    }
}
