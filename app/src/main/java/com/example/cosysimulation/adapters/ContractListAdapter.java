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
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosysimulation.ContractActivity;
import com.example.cosysimulation.R;
import com.example.cosysimulation.models.ContractModel;
import com.example.cosysimulation.viewsmodels.ContractViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContractListAdapter extends RecyclerView.Adapter<ContractListAdapter.ContractViewHolder> {

    List<ContractModel> contracts;
    Context context;
    public ContractListAdapter(Context context, List<ContractModel> contracts){
        this.context = context;
        this.contracts = contracts;
    }

    @NonNull
    @Override
    public ContractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contrat, parent, false);
        return new ContractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractViewHolder holder, int position) {
        holder.bind(contracts.get(position));
    }

    @Override
    public int getItemCount() {
        return this.contracts.size();
    }

    public class ContractViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.courtier)
        TextView courtier;

        @BindView(R.id.delete)
        Button deleteButton;


        @BindView(R.id.contratImage)
        ImageView contractImage;

        public ContractViewHolder(@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);

            courtier.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
            contractImage.setOnClickListener(this);
        }

        public void bind(ContractModel contractModel){
            courtier.setText(contractModel.getCourtier());
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            int idOfContract = contracts.get(position).getId();

            if(deleteButton.getId() == v.getId()){
                Toast.makeText(context, "DELETE THIS " + idOfContract, Toast.LENGTH_LONG).show();
            }
            else {
                Intent myIntent = new Intent(context, ContractActivity.class);
                myIntent.putExtra("key", idOfContract + ""); //Optional parameters
                context.startActivity(myIntent);
            }
        }
    }
}
