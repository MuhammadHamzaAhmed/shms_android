package com.shms.shms_android.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.shms.shms_android.R;
import com.shms.shms_android.model.Database;
import com.shms.shms_android.model.User;
import com.shms.shms_android.view.DashBoard;
import com.shms.shms_android.view.ForgotPassword;
import com.shms.shms_android.view.Heart;
import com.shms.shms_android.view.LoginView;
import com.shms.shms_android.view.RegisterView;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController implements View.OnClickListener {
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onClick(View view) {
        if (loginView.checkForgotPassword(view))
            forgotPassword();
        else if (loginView.checkNewUser(view))
            newUser();
        else if (loginView.checkLoginButton(view))
            loginButton();
    }

    private void loginButton() {
        String re = "^(?=.{1,64}@)[\\p{L}0-9\\+_-]+(\\.[\\p{L}0-9\\+_-]+)*@"
                + "[^-][\\p{L}0-9\\+-]+(\\.[\\p{L}0-9\\+-]+)*(\\.[\\p{L}]{2,})$";
        if(loginView.getEmail().matches(re) && !loginView.getPassword().equals("")){
            try {
                Database db = Database.getDatabase();
                if(db.getCon() != null) {
                    Statement st = db.getCon().createStatement();
                    ResultSet rs = st.executeQuery("SELECT * from user where email=\"" + loginView.getEmail() + "\" and password=\""+loginView.getPassword()+"\"");
                    if(rs.next()) {
                        String username = rs.getString("name");
                        String profile = rs.getString("profile");
                        Intent i =new Intent(loginView, DashBoard.class);
                        User user = new User(username, loginView.getEmail(), profile, loginView.getPassword());
                        i.putExtra("user", (Serializable) user);
                        loginView.startActivity(i);
                        loginView.overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                        loginView.finish();
                    }else {
                        Toast.makeText(loginView, "Wrong Credentials",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(loginView, "Sorry for inconvenience maintaining server.",Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){
                Toast.makeText(loginView, "No internet connection", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }else{
            Toast.makeText(loginView, "WrongMail or password", Toast.LENGTH_LONG).show();
        }
    }

    private void newUser() {
        loginView.startActivity(new Intent(loginView, RegisterView.class));
        loginView.overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    private void forgotPassword() {
        loginView.startActivity(new Intent(loginView, ForgotPassword.class));
        loginView.overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
}
