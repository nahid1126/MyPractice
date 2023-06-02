package com.example.adapter.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.adapter.R;
import com.example.adapter.model.ProductModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends AppCompatActivity {

    @BindView(R.id.txtProductName)
    TextView txtProductName;
    @BindView(R.id.txtProductCode)
    TextView txtProductCode;
    @BindView(R.id.txtProductQuantity)
    TextView txtProductQuantity;
    @BindView(R.id.txtProductAmount)
    TextView txtProductAmount;

    ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

         productModel = getIntent().getParcelableExtra("model");
        if(productModel != null){
            txtProductName.setText(productModel.getProductName());
            txtProductCode.setText(productModel.getProductCode());
            txtProductQuantity.setText(productModel.getQuantity()+"");
            txtProductAmount.setText(productModel.getAmount()+"");
        }

    }
}