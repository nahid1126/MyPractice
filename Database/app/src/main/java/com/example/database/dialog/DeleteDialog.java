package com.example.database.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.database.R;

public class DeleteDialog extends DialogFragment {
    private DeleteDialogInterface deleteDialogInterface;

    public void setDeleteDialogInterface(DeleteDialogInterface deleteDialogInterface) {
        this.deleteDialogInterface = deleteDialogInterface;
    }

    EditText txtDelete;
    Button btnDelete;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delete_dialog, container, false);
        txtDelete = view.findViewById(R.id.txtDeleteId);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialogInterface.onDelete(txtDelete.getText().toString());
            }
        });

        return view;
    }
}
