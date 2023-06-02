package com.example.adapter.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {
    private String productName;
    private String productCode;
    private int quantity;
    private int amount;

    public ProductModel() {
    }

    public ProductModel(String productName, String productCode, int quantity, int amount) {
        this.productName = productName;
        this.productCode = productCode;
        this.quantity = quantity;
        this.amount = amount;
    }

    protected ProductModel(Parcel in) {
        productName = in.readString();
        productCode = in.readString();
        quantity = in.readInt();
        amount = in.readInt();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(productCode);
        dest.writeInt(quantity);
        dest.writeInt(amount);
    }
}
