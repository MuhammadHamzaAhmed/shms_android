package com.shms.shms_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shms.shms_android.R;
import com.shms.shms_android.controller.ForgotPasswordController;

public class ForgotPassword extends AppCompatActivity {

    private ImageView back;
    private EditText email;
    private Button next;
    private ForgotPasswordController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        changeStatusBarColor();

        controller = new ForgotPasswordController(this);
        back = findViewById(R.id.forgotBack);
        email = findViewById(R.id.forgetEmail);
        next = findViewById(R.id.forgotNext);

        back.setOnClickListener(controller);
        next.setOnClickListener(controller);
    }

    public void changeStatusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Window win = getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public boolean checkBack(View view){
        return back.equals(view);
    }

    public String getEmail(){
        return email.getText().toString();
    }

    public boolean checkNext(View view){
        return next.equals(view);
    }
}