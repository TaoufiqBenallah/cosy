package com.example.cosysimulation.di;

import com.example.cosysimulation.api.ApiModule;
import com.example.cosysimulation.services.UserConnectedService;
import com.example.cosysimulation.viewsmodels.ContractViewModel;
import com.example.cosysimulation.viewsmodels.ContractsListViewModel;

import dagger.Component;

@Component(modules = { ApiModule.class})
public interface UserConnectedApiComponent {
    public void inject(UserConnectedService userConnectedService);
    public void inject(ContractViewModel contracViewModel);
}
