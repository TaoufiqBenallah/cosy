package com.example.cosysimulation.models;

import com.google.gson.annotations.SerializedName;

public class ContractModel {
    @SerializedName("id")
    private int id;

    @SerializedName("champOne")
    private String champOne;

    @SerializedName("champTwo")
    private String champTwo;

    @SerializedName("champThree")
    private int champThree;

    @SerializedName("courtier")
    private String courtier;

    @SerializedName("imageUrl")
    private String imageUrl;

    public ContractModel(int id, String champOne, String champTwo, int champThree, String courtier, String imageUrl) {
        this.id = id;
        this.champOne = champOne;
        this.champTwo = champTwo;
        this.champThree = champThree;
        this.courtier = courtier;
        this.imageUrl = imageUrl;
    }

    public ContractModel(int id, String courtier, String imageUrl) {
        this.id = id;
        this.courtier = courtier;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChampOne() {
        return champOne;
    }

    public void setChampOne(String champOne) {
        this.champOne = champOne;
    }

    public String getChampTwo() {
        return champTwo;
    }

    public void setChampTwo(String champTwo) {
        this.champTwo = champTwo;
    }

    public int getChampThree() {
        return champThree;
    }

    public void setChampThree(int champThree) {
        this.champThree = champThree;
    }

    public String getCourtier() {
        return courtier;
    }

    public void setCourtier(String courtier) {
        this.courtier = courtier;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
