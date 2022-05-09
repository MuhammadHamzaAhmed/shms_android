package com.shms.shms_android.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shms.shms_android.R;
import com.shms.shms_android.controller.LoginController;
import com.shms.shms_android.model.Database;

import java.sql.Connection;


public class LoginView extends AppCompatActivity {

    private TextView forgotPassword;
    private TextView new_user;
    private ImageView newUser;
    private Button loginButton;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        changeStatusBarColor();
        try {
            Connection con = Database.getDatabase().getCon();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginController listener = new LoginController(this);

        forgotPassword = findViewById(R.id.forgot_password);
        newUser = findViewById(R.id.newUser);
        new_user = findViewById(R.id.new_user);
        loginButton = findViewById(R.id.loginButton);
        email = findViewById(R.id.email_text);
        password = findViewById(R.id.password_text);

        forgotPassword.setOnClickListener(listener);
        new_user.setOnClickListener(listener);
        newUser.setOnClickListener(listener);
        loginButton.setOnClickListener(listener);
    }

    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window win = getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public boolean checkForgotPassword(View view){
        return forgotPassword.equals(view);
    }

    public boolean checkNewUser(View view){
        return newUser.equals(view) || new_user.equals(view);
    }

    public boolean checkLoginButton(View view){
        return loginButton.equals(view);
    }

    public String getEmail(){
        return email.getText().toString();
    }

    public String getPassword(){
        return password.getText().toString();
    }
}