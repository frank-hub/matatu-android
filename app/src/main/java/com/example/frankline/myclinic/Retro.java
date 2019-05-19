package com.example.frankline.myclinic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Retro {


//    @SerializedName("id")
//    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("illness")
    @Expose
    private String illness;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("start")
    @Expose
    private String start;

    public Retro(){

    }

    public Retro( Integer id, String name, String email,String phone, String start, String illness) {

//        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.start = start;
        this.illness = illness;
    }



//    public Integer getId() {
//        return id;
//    }
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }


}
