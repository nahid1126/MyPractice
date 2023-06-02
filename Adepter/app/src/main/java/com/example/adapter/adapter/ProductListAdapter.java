package com.example.adapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.R;
import com.example.adapter.model.ProductModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private Context context;
    private List<ProductModel> productModelList;
    private ProductListInterface productListInterface;

    public ProductListAdapter(Context context, List<ProductModel> productModelList, ProductListInterface productListInterface) {
        this.context = context;
        this.productModelList = productModelList;
        this.productListInterface = productListInterface;
    }

    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
        ProductModel productModel = productModelList.get(position);

        holder.txtName.setText(productModel.getProductName());
        holder.txtCode.setText(productModel.getProductCode());
        holder.txtQuantity.setText(productModel.getQuantity()+"");
        holder.txtAmount.setText(productModel.getAmount()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              productListInterface.onItemClick(position,productModel);
            }
        });

    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rowProductName)
        TextView txtName;
        @BindView(R.id.rowProductCode)
        TextView txtCode;
        @BindView(R.id.rowProductQuantity)
        TextView txtQuantity;
        @BindView(R.id.rowProductAmount)
        TextView txtAmount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
