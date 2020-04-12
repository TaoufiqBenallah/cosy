package com.example.cosysimulation.services;

import com.example.cosysimulation.api.ContractApi;
import com.example.cosysimulation.api.SingleContractRequest;
import com.example.cosysimulation.di.DaggerContractApiComponent;
import com.example.cosysimulation.models.ContractModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ContractService {

    public static ContractService instance;

    @Inject
    public ContractApi api;

    private ContractService(){
        DaggerContractApiComponent.create().inject(this);
    }

    public static ContractService getInstance(){
        if(instance == null){
            instance = new ContractService();
        }
        return instance;
    }

    public Single<ContractModel> getContract(SingleContractRequest request) {
        return api.getContract(request);
    }

    public Single<String> newContract(ContractModel contractModel) {
        return api.newContract(contractModel);
    }

    public Single<List<ContractModel>> getContracts(){
        return api.getContractList();
    }
}
