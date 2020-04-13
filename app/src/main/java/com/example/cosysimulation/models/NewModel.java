package com.example.cosysimulation.models;

import com.example.cosysimulation.viewsmodels.ContractViewModel;
import com.google.gson.annotations.SerializedName;

public class NewModel {
    @SerializedName("contractModel")
    ContractModel contractModel;

    @SerializedName("userConnected")
    UserConnected userConnected;


    public NewModel(ContractModel contractModel, UserConnected userConnected) {
        this.contractModel = contractModel;
        this.userConnected = userConnected;
    }

    public ContractModel getContractModel() {
        return contractModel;
    }

    public void setContractModel(ContractModel contractModel) {
        this.contractModel = contractModel;
    }

    public UserConnected getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(UserConnected userConnected) {
        this.userConnected = userConnected;
    }

}
