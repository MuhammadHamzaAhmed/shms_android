package com.shms.shms_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.shms.shms_android.R;
import com.shms.shms_android.controller.SetNewPasswordController;
import com.shms.shms_android.model.User;

public class SetNewPassword extends AppCompatActivity {
    private EditText old;
    private EditText new_p;
    private EditText re_new;
    private TextInputLayout oldLay;
    private boolean canShow;
    private Button change;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        changeStatusBarColor();
        canShow = getIntent().getBooleanExtra("frame", true);
        user = (User) getIntent().getSerializableExtra("user");
        oldLay = findViewById(R.id.old_pass_lay);
        old = findViewById(R.id.old_pass);
        new_p = findViewById(R.id.new_pass);
        re_new = findViewById(R.id.retype_pass);
        change = findViewById(R.id.change);
        if(!canShow)
            oldLay.setVisibility(View.INVISIBLE);
        SetNewPasswordController con = new SetNewPasswordController(this);
        change.setOnClickListener(con);

    }

    public void changeStatusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Window win = getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public String getOld() {
        return old.getText().toString();
    }

    public String getNew_p() {
        return new_p.getText().toString();
    }

    public String getRe_new() {
        return re_new.getText().toString();
    }

    public TextInputLayout getOldLay() {
        return oldLay;
    }

    public boolean isCanShow() {
        return canShow;
    }

    public Button getChange() {
        return change;
    }

    public User getUser() {
        return user;
    }
}