package com.example.cosysimulation.viewsmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cosysimulation.api.SingleContractRequest;
import com.example.cosysimulation.di.DaggerContractApiComponent;
import com.example.cosysimulation.di.DaggerUserConnectedApiComponent;
import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.models.NewModel;
import com.example.cosysimulation.models.UserConnected;
import com.example.cosysimulation.services.ContractService;
import com.example.cosysimulation.services.UserConnectedService;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ContractViewModel extends ViewModel {

    public MutableLiveData<NewModel> newModelView = new MutableLiveData<NewModel>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();

    @Inject
    ContractService contractService;

    @Inject
    UserConnectedService userConnectedService;

    CompositeDisposable disposable = new CompositeDisposable();

    public ContractViewModel(){
        DaggerContractApiComponent.create().inject(this);
        DaggerUserConnectedApiComponent.create().inject(this);
    }

    public void call(SingleContractRequest singleContractRequest){
        fetchContract(singleContractRequest);
    }

    public void fetchContract(SingleContractRequest singleContractRequest){

        isLoading.setValue(true);

        disposable.add(
                contractService.getContract(singleContractRequest)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<NewModel>() {

                            @Override
                            public void onSuccess(NewModel newModel) {
                                isLoading.setValue(false);
                                error.setValue(false);
                                newModelView.setValue(newModel);
                            }

                            @Override
                            public void onError(Throwable e) {
                                error.setValue(true);
                                isLoading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        disposable.clear();
    }
}
