package com.example.cosysimulation.services;

import com.example.cosysimulation.api.ContractApi;
import com.example.cosysimulation.api.SingleContractRequest;
import com.example.cosysimulation.api.UserConnectedApi;
import com.example.cosysimulation.di.DaggerContractApiComponent;
import com.example.cosysimulation.di.DaggerUserConnectedApiComponent;
import com.example.cosysimulation.models.UserConnected;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserConnectedService {
    public static UserConnectedService instance;

    @Inject
    public UserConnectedApi api;

    private UserConnectedService(){
        DaggerUserConnectedApiComponent.create().inject(this);
    }

    public static UserConnectedService getInstance(){
        if(instance == null){
            instance = new UserConnectedService();
        }
        return instance;
    }

    public Single<UserConnected> getUserConnected() {
        return api.getUserConnected();
    }

}
