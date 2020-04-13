package com.example.cosysimulation.api;

import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.models.UserConnected;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface UserConnectedApi {

    @GET("user.php")
    Single<UserConnected> getUserConnected();
}
