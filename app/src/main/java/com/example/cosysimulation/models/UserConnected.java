package com.example.cosysimulation.models;

import com.google.gson.annotations.SerializedName;

public class UserConnected {

    @SerializedName("role")
    String role;


    public UserConnected(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

