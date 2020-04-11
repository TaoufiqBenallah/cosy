package com.example.cosysimulation;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewContractActivity extends AppCompatActivity {

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

        addSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String champOneValue = champOne.getText().toString();
                String champTwoValue = champTwo.getText().toString();
                int champThreeValue = Integer.parseInt(champThree.getText().toString());

                Log.d("TAG", champOneValue);

                Log.d("TAG", champTwoValue);

                Log.d("TAG", champThreeValue + "");
            }
        });
    }

}
