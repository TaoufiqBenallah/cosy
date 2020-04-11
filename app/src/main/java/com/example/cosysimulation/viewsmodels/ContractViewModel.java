package com.example.cosysimulation.viewsmodels;

import androidx.lifecycle.MutableLiveData;

import com.example.cosysimulation.models.ContractModel;

import java.util.ArrayList;
import java.util.List;

public class ContractViewModel {

    MutableLiveData<ContractModel> contract;
    MutableLiveData<Boolean> isLoading;
    MutableLiveData<Boolean> error;



    public void call(){
        fetchContract();
    }

    public void fetchContract(){
        ContractModel contractModel = new ContractModel(1, "h", "hf", 1, "h", "h");

        ContractModel contractModel0 = new ContractModel(1, "Item 1", "h");
        ContractModel contractModel1 = new ContractModel(1, "Item 2", "h");
        ContractModel contractModel2 = new ContractModel(1, "Item 3", "j");
        ContractModel contractModel3 = new ContractModel(1, "Item 4", "h");

        List<ContractModel> list = new ArrayList<ContractModel>();

        list.add(contractModel0);

        list.add(contractModel1);

        list.add(contractModel2);

        list.add(contractModel3);


        contract.setValue(contractModel);
    }
}
