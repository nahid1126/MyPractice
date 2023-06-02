package com.example.adapter.activity;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.adapter.R;
import com.example.adapter.adapter.ProductListAdapter;
import com.example.adapter.adapter.ProductListInterface;
import com.example.adapter.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edtProductName)
    EditText txtProductName;
    @BindView(R.id.edtProductCode)
    EditText txtProductCode;
    @BindView(R.id.edtQuantity)
    EditText txtQuantity;
    @BindView(R.id.edtAmount)
    EditText txtAmount;


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    //arrayLIst
    List<ProductModel> productList;
    List<String> productNameList;
    private ProductListAdapter productListAdapter;


    @OnClick(R.id.btnAdd)
    public void onClick() {
        String productName = txtProductName.getText().toString();
        String productCode = txtProductCode.getText().toString();
        String productQuantity = txtQuantity.getText().toString();
        String productAmount = txtAmount.getText().toString();
        if (TextUtils.isEmpty(productName)) {
            Toast.makeText(this, "Enter Product Name first", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(productCode)) {
            Toast.makeText(this, "Enter Product Code first", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(productQuantity)) {
            Toast.makeText(this, "Enter Product Quantity first", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(productAmount)) {
            Toast.makeText(this, "Enter Product Amount first", Toast.LENGTH_SHORT).show();
        } else {
            ProductModel productModel = new ProductModel(productName, productCode, Integer.parseInt(productQuantity), Integer.parseInt(productAmount));

            productList.add(productModel);
            Toast.makeText(this, "successfully data added " + productList.size(), Toast.LENGTH_SHORT).show();
            clear();
            getProductNameList();
            setUpRecyclerView();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        productList = new ArrayList<>();

        if (productList.size() > 0) {
            getProductNameList();
        }
    }

    private void setUpRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        productListAdapter = new ProductListAdapter(MainActivity.this, productList, new ProductListInterface() {
            @Override
            public void onItemClick(int position, ProductModel productModel) {

                Intent intent = new Intent(MainActivity.this, ProductDetailsActivity.class);
                intent.putExtra("model",productModel);
                startActivity(intent);
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();


    }

    private void clear() {
        txtProductName.setText("");
        txtProductCode.setText("");
        txtQuantity.setText("");
        txtAmount.setText("");
    }

    private void getProductNameList() {
        productNameList = new ArrayList<>();
        for (ProductModel model : productList) {
            productNameList.add(model.getProductName());
            Log.d("check ", String.valueOf(productNameList));
        }
    }
}