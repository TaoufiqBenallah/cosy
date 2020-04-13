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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cosysimulation.adapters.ContractListAdapter;
import com.example.cosysimulation.databinding.ActivityMainBinding;
import com.example.cosysimulation.viewsmodels.ContractsListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ContractsListViewModel contractsListViewModel;

    @BindView(R.id.contractList)
    RecyclerView contractList;

    @BindView(R.id.newContractBtn)
    Button newContractBtn;

    @BindView(R.id.listLoading)
    ProgressBar listLoading;

    @BindView(R.id.listError)
    TextView listError;


    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ActivityMainBinding main = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ButterKnife.bind(this);

        main.contractList.setLayoutManager(new LinearLayoutManager(this));

        contractsListViewModel = ViewModelProviders.of(this).get(ContractsListViewModel.class);
        contractsListViewModel.call();

        contractsListViewModel.contractList.observe(this,contractModels -> {
            if(contractModels != null){
                contractList.setVisibility((View.VISIBLE));
                adapter = new ContractListAdapter(MainActivity.this, contractModels, new ContractListAdapter.OnButtonPressed() {
                    @Override
                    public void onClicked(int position) {
                        contractsListViewModel.deleteContract(position);
                    }
                });
                main.contractList.setAdapter(adapter);
            }
        });

        contractsListViewModel.isLoading.observe(this,isLoading -> {
            if(isLoading != null){
                listLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE );
                if(isLoading){
                    listError.setVisibility(View.GONE);
                    contractList.setVisibility((View.GONE));
                }
            }
        });

        contractsListViewModel.error.observe(this,Error -> {
            if(Error != null){
                listError.setVisibility(Error ? View.VISIBLE : View.GONE );
            }
        });

        newContractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newContractIntent = new Intent(getApplicationContext(), NewContractActivity.class);
                MainActivity.this.startActivity(newContractIntent);
            }
        });

    }

    @Override
    public void onResume()
    {
        super.onResume();
        contractsListViewModel = ViewModelProviders.of(this).get(ContractsListViewModel.class);
        contractsListViewModel.call();
    }

}
