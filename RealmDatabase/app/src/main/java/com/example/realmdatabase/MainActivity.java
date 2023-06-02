package com.example.realmdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realmdatabase.model.DataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    @BindView(R.id.btnInsert)
//    Button btnInsert;
//    @BindView(R.id.btnUpdate)
//    Button btnUpdate;
//    @BindView(R.id.btnRead)
//    Button btnRead;
//    @BindView(R.id.btnDelete)
//    Button btnDelete;
//    @BindView(R.id.showTxtView)
//    TextView showTxtView;
//
//    @BindView(R.id.txtName)
//    EditText txtName;
//    @BindView(R.id.txtAge)
//    EditText txtAge;
//    @BindView(R.id.txtGender)
//    Spinner txtGender;
//    @BindView(R.id.btnSubmit)
//    Button btnSubmit;


    final String TAG="mainActivity";
    DataModel dataModel;
    Realm realm;

    Button btnInsert, btnUpdate, btnRead, btnDelete;
    TextView showTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.bind(MainActivity.this);
        realm = Realm.getDefaultInstance();
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnRead = findViewById(R.id.btnRead);
        btnDelete = findViewById(R.id.btnDelete);
        showTxtView = findViewById(R.id.showTxtView);


        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnInsert) {
            showInsertDialog();
        }
        if (v.getId() == R.id.btnRead) {
            showData();
        }
    }

    private void showInsertDialog() {
        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.insert_dialog, null);
        dialog_builder.setView(view);

        final EditText txtName = findViewById(R.id.txtName);
        final EditText txtAge = findViewById(R.id.txtAge);
        Spinner txtGender = findViewById(R.id.txtGender);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        AlertDialog alertDialog = dialog_builder.show();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
            }
        });
    }

    private void showData() {
        List<DataModel> dataModels = realm.where(DataModel.class).findAll();
        showTxtView.setText("");
        for (int i = 0; i < dataModels.size(); i++) {
            showTxtView.append("ID: " + dataModels.get(i).getId() + " Name: " + dataModels.get(i).getName() + " Age: " + dataModels.get(i).getAge() + " Gender: " + dataModels.get(i).getGender());
        }
    }


}

// alertDialog.dismiss();
//         dataModel = new DataModel();
//         Number current_id = realm.where(DataModel.class).max("id");
//        long nxtId;
//        if (current_id == null) {
//        nxtId = 1;
//        } else {
//        nxtId = current_id.intValue() + 1;
//        }
//        dataModel.setId(nxtId);
//        dataModel.setName(txtName.getText().toString());
//        dataModel.setAge(Integer.parseInt(txtName.getText().toString()));
//        dataModel.setGender(txtGender.getSelectedItem().toString());
//
//
//        realm.executeTransaction(new Realm.Transaction() {
//@Override
//public void execute(Realm realm) {
//        realm.copyToRealmOrUpdate(dataModel);
//        }
//        });