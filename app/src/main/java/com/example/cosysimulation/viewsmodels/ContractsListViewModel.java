package com.example.cosysimulation.viewsmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cosysimulation.models.ContractModel;

import java.util.ArrayList;
import java.util.List;

public class ContractsListViewModel extends ViewModel {
    public MutableLiveData<List<ContractModel>> contractList = new MutableLiveData<List<ContractModel>>();
    MutableLiveData<Boolean> isLoading;
    MutableLiveData<Boolean> error;

    public void call(){
        fetchContracts();
    }

    public void fetchContracts(){

        ContractModel contractModel = new ContractModel(2, "Item 1", "h");
        ContractModel contractModel1 = new ContractModel(11, "Item 2", "h");
        ContractModel contractModel2 = new ContractModel(82, "Item 3", "j");
        ContractModel contractModel3 = new ContractModel(575, "Item 4", "h");

        List<ContractModel> list = new ArrayList<ContractModel>();

        list.add(contractModel);

        list.add(contractModel1);

        list.add(contractModel2);

        list.add(contractModel3);

        contractList.setValue(list);

    }



}
