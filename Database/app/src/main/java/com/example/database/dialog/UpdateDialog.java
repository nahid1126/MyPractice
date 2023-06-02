package com.example.database.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.database.R;
import com.example.database.model.DataModel;


public class UpdateDialog extends DialogFragment {
    EditText txtName;
    EditText txtAge;
    Spinner txtGender;
    Button btnSubmit;
    DataModel dataModel;

    private UpdateDialogInterface updateDialogInterface;

    public void setUpdateDialogInterface(UpdateDialogInterface updateDialogInterface) {
        this.updateDialogInterface = updateDialogInterface;
        dataModel=new DataModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_dialog, container, false);
        txtName = view.findViewById(R.id.txtName);
        txtAge = view.findViewById(R.id.txtAge);
        txtGender = view.findViewById(R.id.txtGender);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        txtName.setText(dataModel.getName());
        txtAge.setText(""+dataModel.getAge());
        //txtGender.setSelection(0);
//        if (dataModel.getGender().equalsIgnoreCase("Male")){
//            txtGender.setSelection(0);
//        }else {
//            txtGender.setSelection(1);
//        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialogInterface.onUpdate(txtName.getText().toString(),
                        txtAge.getText().toString(), txtGender.getSelectedItem().toString());
            }
        });
        return view;
    }
}
