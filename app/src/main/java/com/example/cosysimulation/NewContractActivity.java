package com.example.cosysimulation;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.viewsmodels.ContractViewModel;
import com.example.cosysimulation.viewsmodels.NewContractViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewContractActivity extends AppCompatActivity {

    NewContractViewModel newContractViewModel;

    @BindView(R.id.addTitle)
    TextView addTitle;

    @BindView(R.id.backToListFromNew)
    Button backToList;

    @BindView(R.id.addSubmit)
    Button addSubmit;

    @BindView(R.id.champOne)
    EditText champOne;

    @BindView(R.id.champTwo)
    EditText champTwo;

    @BindView(R.id.champThree)
    EditText champThree;

    @BindView(R.id.addError)
    TextView addError;

    @BindView(R.id.addSuccess)
    TextView addSuccess;

    @BindView(R.id.addNoAcess)
    TextView addNoAccess;

    @BindView(R.id.addLoading)
    ProgressBar addLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_contract);

        ButterKnife.bind(this);

        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        newContractViewModel = ViewModelProviders.of(this).get(NewContractViewModel.class);

        addSuccess.setVisibility(View.GONE);
        addNoAccess.setVisibility(View.GONE);
        addError.setVisibility(View.GONE);
        addLoading.setVisibility(View.GONE);


        newContractViewModel.isLoading.observe(this, isLoading -> {
            if(isLoading != false && isLoading != null){
                addTitle.setVisibility(View.GONE);
                champOne.setVisibility(View.GONE);
                champTwo.setVisibility(View.GONE);
                champThree.setVisibility(View.GONE);
                addSubmit.setVisibility(View.GONE);
                addSuccess.setVisibility(View.GONE);
                addNoAccess.setVisibility(View.GONE);
                addError.setVisibility(View.GONE);

                addLoading.setVisibility(View.VISIBLE);

            }
        });

        newContractViewModel.success.observe(this, success -> {
            if(success != false && success != null){

                addTitle.setVisibility(View.VISIBLE);
                champOne.setVisibility(View.VISIBLE);
                champTwo.setVisibility(View.VISIBLE);
                champThree.setVisibility(View.VISIBLE);
                addSubmit.setVisibility(View.VISIBLE);
                addSuccess.setVisibility(View.VISIBLE);
                addNoAccess.setVisibility(View.GONE);
                addError.setVisibility(View.GONE);

                addLoading.setVisibility(View.GONE);

            }
        });

        newContractViewModel.error.observe(this, error -> {
            if(error != false && error != null){
                addTitle.setVisibility(View.VISIBLE);
                champOne.setVisibility(View.VISIBLE);
                champTwo.setVisibility(View.VISIBLE);
                champThree.setVisibility(View.VISIBLE);
                addSubmit.setVisibility(View.VISIBLE);
                addSuccess.setVisibility(View.GONE);
                addNoAccess.setVisibility(View.GONE);
                addError.setVisibility(View.VISIBLE);

                addLoading.setVisibility(View.GONE);
            }
        });

        addSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String champOneValue = champOne.getText().toString();
                String champTwoValue = champTwo.getText().toString();
                int champThreeValue = Integer.parseInt(champThree.getText().toString());

                ContractModel contractModel = new ContractModel(1, champOneValue, champTwoValue, champThreeValue, "Courtier "+champThreeValue, "URLHERE");

                newContractViewModel.call(contractModel);
            }
        });
    }

}
