package com.example.designtask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designtask.fragment.FavoriteFragment;
import com.example.designtask.fragment.HomeFragment;
import com.example.designtask.fragment.SearchFragment;
import com.example.designtask.model.ModelClass;
import com.example.designtask.sharedPreferences.SharedPreferencesDemo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String msg = "Message";
    ModelClass modelClass;
    SharedPreferencesDemo sharedpreferencesDemo;

    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.txtAge)
    EditText txtAge;
    @BindView(R.id.showName)
    TextView showName;
    @BindView(R.id.showAge)
    TextView showAge;


    @OnClick(R.id.btnSubmit)
    public void onClick() {
        String name = txtName.getText().toString();
        String age = txtAge.getText().toString();

        if (TextUtils.isEmpty(txtName.getText().toString())) {
            Toast.makeText(MainActivity.this, "Enter Name First", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(txtAge.getText().toString())) {
            Toast.makeText(MainActivity.this, "Enter Age First", Toast.LENGTH_SHORT).show();
        } else {
            modelClass = new ModelClass(name, Integer.parseInt(age));
            sharedpreferencesDemo.setName(name);
            sharedpreferencesDemo.setAge(Integer.parseInt(age));
            clear();
            Toast.makeText(MainActivity.this, "Submit", Toast.LENGTH_SHORT).show();
        }
    }

    @BindView(R.id.bottomNevegation)
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sharedpreferencesDemo = new SharedPreferencesDemo(MainActivity.this);
        showName.setText(sharedpreferencesDemo.getName());
        showAge.setText(sharedpreferencesDemo.getAge() + "");

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.nav_favorite:
                    fragment = new FavoriteFragment();
                    break;
                case R.id.nav_search:
                    fragment = new SearchFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            return true;
        });
    }

    private void clear() {
        txtAge.setText("");
        txtName.setText("");
    }
}