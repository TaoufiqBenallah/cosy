package com.example.cosysimulation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cosysimulation.adapters.ContractListAdapter;
import com.example.cosysimulation.databinding.ActivityMainBinding;
import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.viewsmodels.ContractsListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ContractsListViewModel contractsListViewModel;

    @BindView(R.id.contractList)
    RecyclerView contractList;

    @BindView(R.id.newContractBtn)
    Button newContractBtn;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityMainBinding main = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ButterKnife.bind(this);
        contractList.setLayoutManager(new LinearLayoutManager(this));

        ContractModel model1 = new ContractModel(1, "GHH", "f");
        ContractModel model2 = new ContractModel(2, "hhj", "f");

        contractsListViewModel = ViewModelProviders.of(this).get(ContractsListViewModel.class);
        contractsListViewModel.call();

        contractsListViewModel.contractList.observe(this,contractModels -> {
            adapter = new ContractListAdapter(MainActivity.this, contractModels);
            contractList.setAdapter(adapter);
        });

        newContractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newContractIntent = new Intent(getApplicationContext(), NewContractActivity.class);
                MainActivity.this.startActivity(newContractIntent);
            }
        });

    }
}
