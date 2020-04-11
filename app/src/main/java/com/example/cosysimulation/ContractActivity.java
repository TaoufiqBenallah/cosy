package com.example.cosysimulation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.cosysimulation.databinding.ContractDetailsBinding;
import com.example.cosysimulation.models.ContractModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContractActivity extends AppCompatActivity {

    @BindView(R.id.backToList)
    Button backToList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContractDetailsBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.contract_details);

        ButterKnife.bind(this);

        ContractModel contractModel = new ContractModel(1, "Mine data Bind", "55", 15, "jgj", "jgjf");

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        int idOfContract = Integer.parseInt(extras.getString("key"));

        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        dataBinding.setContract(contractModel);

    }
}