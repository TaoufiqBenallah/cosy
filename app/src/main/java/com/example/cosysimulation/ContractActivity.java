package com.example.cosysimulation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.cosysimulation.adapters.ContractListAdapter;
import com.example.cosysimulation.api.SingleContractRequest;
import com.example.cosysimulation.databinding.ContractDetailsBinding;
import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.viewsmodels.ContractViewModel;
import com.example.cosysimulation.viewsmodels.ContractsListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContractActivity extends AppCompatActivity {

    ContractViewModel contractViewModel;

    @BindView(R.id.backToList)
    Button backToList;

    @BindView(R.id.detailsError)
    TextView detailsError;

    @BindView(R.id.detailsLoading)
    ProgressBar detailsLoading;

    @BindView(R.id.courtiercard)
    CardView courtierCard;

    @BindView(R.id.vendeurcard)
    CardView vendeurCard;

    String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ContractDetailsBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.contract_details);

        ButterKnife.bind(this);


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        int idOfContract = Integer.parseInt(extras.getString("key"));

        SingleContractRequest singleRequest = new SingleContractRequest("VENDEUR", idOfContract + "");

        contractViewModel = ViewModelProviders.of(this).get(ContractViewModel.class);
        contractViewModel.call(singleRequest);

        contractViewModel.newModelView.observe(this,newModel -> {


            if(newModel != null){

                Log.d("TAG", "newModel " + newModel.getUserConnected().getRole() + "" + (newModel.getUserConnected().getRole().equals("VENDEUR")));
                if(newModel.getUserConnected().getRole().equals("VENDEUR")){
                    vendeurCard.setVisibility(View.VISIBLE);
                    courtierCard.setVisibility(View.VISIBLE);
                    detailsLoading.setVisibility(View.GONE);
                    detailsError.setVisibility(View.GONE);
                    if(newModel.getContractModel() != null) {
                        dataBinding.setContract(newModel.getContractModel());
                    }


                }else if(newModel.getUserConnected().getRole().equals("COURTIER")){
                    vendeurCard.setVisibility(View.GONE);
                    courtierCard.setVisibility(View.VISIBLE);
                    detailsLoading.setVisibility(View.GONE);
                    detailsError.setVisibility(View.GONE);
                    if(newModel.getContractModel() != null) {
                        dataBinding.setContract(newModel.getContractModel());
                    }

                }else {
                    vendeurCard.setVisibility(View.GONE);
                    courtierCard.setVisibility(View.VISIBLE);
                    detailsLoading.setVisibility(View.GONE);
                    detailsError.setVisibility(View.GONE);
                }
            }
        });

        contractViewModel.isLoading.observe(this,isLoading -> {
            Log.d("TAG", "loading");
            if(isLoading != null && isLoading){
                vendeurCard.setVisibility(View.GONE);
                courtierCard.setVisibility(View.GONE);
                detailsLoading.setVisibility(View.VISIBLE);
                detailsError.setVisibility(View.GONE);
            }

        });

        contractViewModel.error.observe(this,Error -> {
            Log.d("TAG", "erroR");
            if(Error != null && Error){

                vendeurCard.setVisibility(View.GONE);
                courtierCard.setVisibility(View.GONE);
                detailsLoading.setVisibility(View.GONE);
                detailsError.setVisibility(View.VISIBLE);
            }
        });

        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}