package com.example.cosysimulation.di;

import com.example.cosysimulation.NewContractActivity;
import com.example.cosysimulation.api.ApiModule;
import com.example.cosysimulation.services.ContractService;
import com.example.cosysimulation.viewsmodels.ContractViewModel;
import com.example.cosysimulation.viewsmodels.ContractsListViewModel;
import com.example.cosysimulation.viewsmodels.NewContractViewModel;

import dagger.Component;

@Component(modules = { ApiModule.class})
public interface ContractApiComponent {

    public void inject(ContractService contractService);
    public void inject(ContractsListViewModel contractListViewModel);
    public void inject(ContractViewModel contractViewModel);
    public void inject(NewContractViewModel newContractViewModel);

}
