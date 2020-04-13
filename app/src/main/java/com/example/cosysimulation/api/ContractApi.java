package com.example.cosysimulation.api;

import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.models.NewModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ContractApi {

    @GET("contrats.php")
    Single<List<ContractModel>> getContractList();

    @Headers("Content-Type: application/json")
    @POST("contrat.php")
    Single<NewModel> getContract(@Body SingleContractRequest body);

    @Headers("Content-Type: application/json")
    @POST("addContrat.php")
    Single<String> newContract(@Body ContractModel body);


    @Headers("Content-Type: application/json")
    @POST("deleteContrat.php")
    Single<String> deleteContract(@Body SingleContractRequest body);

}
