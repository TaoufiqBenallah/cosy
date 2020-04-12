package com.example.cosysimulation.viewsmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cosysimulation.api.SingleContractRequest;
import com.example.cosysimulation.di.DaggerContractApiComponent;
import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.services.ContractService;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ContractViewModel extends ViewModel {

    public MutableLiveData<ContractModel> contract = new MutableLiveData<ContractModel>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();

    @Inject
    ContractService contractService;

    CompositeDisposable disposable = new CompositeDisposable();

    public ContractViewModel(){
        DaggerContractApiComponent.create().inject(this);
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
                        .subscribeWith(new DisposableSingleObserver<ContractModel>() {

                            @Override
                            public void onSuccess(ContractModel contractModel) {
                                isLoading.setValue(false);
                                error.setValue(false);
                                contract.setValue(contractModel);
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
