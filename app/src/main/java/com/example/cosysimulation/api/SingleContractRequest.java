package com.example.cosysimulation.api;

import com.google.gson.annotations.SerializedName;

public class SingleContractRequest {
    @SerializedName("userRole")
    private String userRole;
    @SerializedName("contratId")
    private String contratId;

    public SingleContractRequest(String userRole, String contractId) {
        this.userRole = userRole;
        this.contratId = contractId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getContractId() {
        return contratId;
    }

    public void setContractId(String contractId) {
        this.contratId = contractId;
    }
}
