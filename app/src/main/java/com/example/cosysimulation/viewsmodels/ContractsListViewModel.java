package com.example.cosysimulation.viewsmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cosysimulation.api.SingleContractRequest;
import com.example.cosysimulation.di.DaggerContractApiComponent;
import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.services.ContractService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ContractsListViewModel extends ViewModel {

    public MutableLiveData<List<ContractModel>> contractList = new MutableLiveData<List<ContractModel>>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();;

    @Inject
    ContractService contractService;

    CompositeDisposable disposable = new CompositeDisposable();

    public ContractsListViewModel(){
        super();
        DaggerContractApiComponent.create().inject(this);
    }

    public void call(){
        fetchContracts();
    }


    public void fetchContracts(){

        isLoading.setValue(true);

        disposable.add(
                contractService.getContracts()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<ContractModel>>() {

                            @Override
                            public void onSuccess(List<ContractModel> friendsModels) {
                                isLoading.setValue(false);
                                error.setValue(false);
                                contractList.setValue(friendsModels);
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

    public void deleteContract(int position){

        isLoading.setValue(true);

        SingleContractRequest request = new SingleContractRequest("WHATEVER NOW", position + "");

        disposable.add(
                contractService.deleteContract(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<String>() {

                            @Override
                            public void onSuccess(String response) {
                                call();
                                isLoading.setValue(false);
                                error.setValue(false);

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
