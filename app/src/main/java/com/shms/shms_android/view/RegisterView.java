package com.shms.shms_android.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shms.shms_android.R;
import com.shms.shms_android.controller.RegisterController;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class RegisterView extends AppCompatActivity {

    private EditText reg_name;
    private EditText reg_email;
    private EditText reg_pass;
    private Button reg_dob;
    private RadioButton reg_male;
    private RadioButton reg_female;
    private CircularProgressButton register;
    private TextView already;
    private String dbDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_view);
        changeStatusBarColor();
        reg_name = findViewById(R.id.reg_name);
        reg_email = findViewById(R.id.reg_email);
        reg_pass = findViewById(R.id.reg_pass);
        reg_dob = findViewById(R.id.reg_dob);
        reg_male = findViewById(R.id.reg_male);
        reg_female = findViewById(R.id.reg_female);
        reg_male.setSelected(true);
        RegisterController con = new RegisterController(this);
        register = findViewById(R.id.cirRegisterButton);
        already = findViewById(R.id.already);
        register.setOnClickListener(con);
        already.setOnClickListener(con);
        reg_dob.setOnClickListener(con);
    }

    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window win = getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public String getReg_name() {
        return reg_name.getText().toString();
    }

    public String getReg_email() {
        return reg_email.getText().toString();
    }

    public String getReg_pass() {
        return reg_pass.getText().toString();
    }

    public boolean getReg_dob(View v){
        return reg_dob.equals(v);
    }

    public String getReg_dob() {
        return reg_dob.getText().toString();
    }

    public boolean getRegister(View v) {
        return register.equals(v);
    }

    public boolean getAlready(View v) {
        return already.equals(v);
    }

    public void setReg_dob(String reg_dob) {
        this.reg_dob.setText(reg_dob);
    }

    public void setDBDate(String databaseDate) {
        this.dbDate = databaseDate;
    }

    public String getDbDate() {
        return dbDate;
    }

    public int getGender() {
        if(reg_female.isSelected())
            return 2;
        if(reg_male.isSelected())
            return 1;
        return 0;
    }
}