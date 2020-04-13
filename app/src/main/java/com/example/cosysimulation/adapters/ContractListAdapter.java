package com.example.cosysimulation.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosysimulation.ContractActivity;
import com.example.cosysimulation.CrudListener;
import com.example.cosysimulation.R;
import com.example.cosysimulation.databinding.ContratBinding;
import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.viewsmodels.ContractViewModel;
import com.example.cosysimulation.viewsmodels.ContractsListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContractListAdapter extends RecyclerView.Adapter<ContractListAdapter.ContractViewHolder> {

    List<ContractModel> contracts;
    Context context;
    OnButtonPressed onButtonPressed;

    public ContractListAdapter(Context context, List<ContractModel> contracts, OnButtonPressed onButtonPressed){
        this.context = context;
        this.contracts = contracts;
        this.onButtonPressed = onButtonPressed;
    }

    @NonNull
    @Override
    public ContractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContratBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.contrat, parent, false); // ContratBinding >> as your list item layout named "contrat"
        return new ContractViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractViewHolder holder, int position) {
        holder.binding.setContract(contracts.get(position));
    }

    @Override
    public int getItemCount() {
        return this.contracts.size();
    }

    public class ContractViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ContractsListViewModel listViewModel;

        @BindView(R.id.courtier)
        TextView courtier;

        @BindView(R.id.delete)
        Button deleteButton;

        ContratBinding binding;

        @BindView(R.id.contratImage)
        ImageView contractImage;

        public ContractViewHolder(@NonNull ContratBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);

            courtier.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
            contractImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            int idOfContract = contracts.get(position).getId();

            if(deleteButton.getId() == v.getId()){
                // action here
                onButtonPressed.onClicked(idOfContract);
            }
            else {
                Intent myIntent = new Intent(context, ContractActivity.class);
                myIntent.putExtra("key", idOfContract + ""); //Optional parameters
                context.startActivity(myIntent);
            }
        }
    }

    public interface OnButtonPressed {
        void onClicked(int position);
    }
}
