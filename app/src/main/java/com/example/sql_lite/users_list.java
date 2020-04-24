package com.example.sql_lite;

public class users_list {
    String id;
    String name;
    String email;
    String mobile;
    String image;
    public users_list(){

    }
    public users_list(String id,String name,String email,String mobile,String image){
        this.id=id;
        this.name=name;
        this.email=email;
        this.mobile=mobile;
        this.image=image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
