package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.dialog.DeleteDialog;
import com.example.database.dialog.DeleteDialogInterface;
import com.example.database.dialog.InsertDialog;
import com.example.database.dialog.InsertDialogInterface;
import com.example.database.dialog.UpdateDialog;
import com.example.database.dialog.UpdateDialogInterface;
import com.example.database.model.DataModel;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "MainActivity";
    private Button btnInsert;
    private Button btnUpdate;
    private Button btnRead;
    private Button btnDelete;
    private TextView showTxtView;
    private DeleteDialog deleteDialog;
    private InsertDialog insertDialog;
    private UpdateDialog updateDialog;


    DataModel dataModel;
    Realm realm;
    List<DataModel> dataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    private void showInsertDialog() {
        insertDialog = new InsertDialog();
        insertDialog.setInsertDialogInterface(new InsertDialogInterface() {
            @Override
            public void onSubmit(String n, String a, String g) {
                if (TextUtils.isEmpty(n) || TextUtils.isEmpty(a) || TextUtils.isEmpty(g)) {
                    Toast.makeText(MainActivity.this, "Enter Name Age Gender First",
                            Toast.LENGTH_SHORT).show();
                } else {
                    insertDialog.dismiss();
                    dataModel = new DataModel();
                    Number current_id = realm.where(DataModel.class).max("id");
                    long nxtId;
                    if (current_id == null) {
                        nxtId = 1;
                    } else {
                        nxtId = current_id.intValue() + 1;
                    }
                    dataModel.setId(nxtId);
                    dataModel.setName(n);
                    dataModel.setAge(Integer.parseInt(a));
                    dataModel.setGender(g);


                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(dataModel);
                        }
                    });
                    Toast.makeText(MainActivity.this, "submited", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        insertDialog.show(((AppCompatActivity) MainActivity.this).getSupportFragmentManager(),
                "Insert Dialog");

    }


    private void showData() {
        dataModels = realm.where(DataModel.class).findAll();
        showTxtView.setText("");
        for (int i = 0; i < dataModels.size(); i++) {
            showTxtView.append("ID: " + dataModels.get(i).getId() + "\nName: " + dataModels.get(i)
                    .getName() + "\nAge: " + dataModels.get(i).getAge() + "\nGender: " + dataModels
                    .get(i).getGender() + "\n\n");
        }
    }

    private void showDeleteDialog() {
        deleteDialog = new DeleteDialog();

        deleteDialog.setDeleteDialogInterface(new DeleteDialogInterface() {
            @Override
            public void onDelete(String x) {
                if (TextUtils.isEmpty(x)) {
                    Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    deleteDialog.dismiss();
                    long id = Long.parseLong(x);
                    dataModel = realm.where(DataModel.class).equalTo("id", id).findFirst();
                    if (dataModel != null) {
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                dataModel.deleteFromRealm();
                                Toast.makeText(MainActivity.this, "Delete Successful "
                                        + id, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(MainActivity.this, "ID not Found",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        deleteDialog.show(((AppCompatActivity) MainActivity.this).getSupportFragmentManager(),
                "Delete Dialog");
       /* dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.delete_dialog);

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        EditText txtDelete=dialog.findViewById(R.id.txtDeleteId);
        Button btnDelete=dialog.findViewById(R.id.btnDelete);
        dialog.show();*/


    }

    private void showUpdateDialog() {
        deleteDialog = new DeleteDialog();
        deleteDialog.setDeleteDialogInterface(new DeleteDialogInterface() {
            @Override
            public void onDelete(String x) {
                if (TextUtils.isEmpty(x)) {
                    Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    deleteDialog.dismiss();
                    long id = Long.parseLong(x);
                    dataModel = realm.where(DataModel.class).equalTo("id", id).findFirst();
                    if (dataModel != null) {
                        showUpdateDialog(dataModel);
                    } else {
                        Toast.makeText(MainActivity.this, "ID not Found",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        deleteDialog.show(((AppCompatActivity) MainActivity.this).getSupportFragmentManager(),
                "Delete Dialog");

    }

    private void showUpdateDialog(DataModel dataModel) {
        updateDialog = new UpdateDialog();

        updateDialog.setUpdateDialogInterface(new UpdateDialogInterface() {
            @Override
            public void onUpdate(String n, String a, String g) {
                updateDialog.dismiss();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        dataModel.setName(n);
                        dataModel.setAge(Integer.parseInt(a));
                        dataModel.setGender(g);
                        realm.copyToRealmOrUpdate(dataModel);
                    }
                });
                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        updateDialog.show(((AppCompatActivity) MainActivity.this).getSupportFragmentManager(),
                "Insert Dialog");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnInsert) {
            showInsertDialog();
        }
        if (v.getId() == R.id.btnUpdate) {
            showUpdateDialog();
        }
        if (v.getId() == R.id.btnRead) {
            showData();
        }
        if (v.getId() == R.id.btnDelete) {
            showDeleteDialog();
        }
    }


}