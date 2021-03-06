package com.example.cosysimulation.api;

import com.example.cosysimulation.models.UserConnected;
import com.example.cosysimulation.services.ContractService;
import com.example.cosysimulation.services.UserConnectedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public static String BASE_URL = "http://172.25.8.36/newconceptsphp/";

    @Provides
    public ContractApi provideContractApi(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder().client(client).baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ContractApi.class);
    }

    @Provides
    public UserConnectedApi provideUserConnectedApi(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder().client(client).baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(UserConnectedApi.class);
    }

    @Provides
    public ContractService provideContractService(){
        return ContractService.getInstance();
    }

    @Provides
    public UserConnectedService provideUserConnectedService(){
        return UserConnectedService.getInstance();
    }


}
