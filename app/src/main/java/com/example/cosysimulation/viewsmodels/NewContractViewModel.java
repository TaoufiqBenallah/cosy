package com.example.cosysimulation.viewsmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cosysimulation.NewContractActivity;
import com.example.cosysimulation.di.DaggerContractApiComponent;
import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.services.ContractService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NewContractViewModel extends ViewModel {
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> error = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> success = new MutableLiveData<Boolean>();

    CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    ContractService contractService;

    public NewContractViewModel(){
        super();
        DaggerContractApiComponent.create().inject(this);
    }

    public void call(ContractModel contractModel){
        isLoading.setValue(true);

        disposable.add(
                contractService.newContract(contractModel)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<String>() {

                            @Override
                            public void onSuccess(String s) {

                                Log.d("TAG", "VIEWmodel SUCCESS ?");
                                success.setValue(true);
                                isLoading.setValue(false);
                                error.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                success.setValue(false);
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
