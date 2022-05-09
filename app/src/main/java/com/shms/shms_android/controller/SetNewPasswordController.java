package com.shms.shms_android.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.shms.shms_android.model.Database;
import com.shms.shms_android.view.SetNewPassword;

import java.sql.Connection;
import java.sql.Statement;

public class SetNewPasswordController implements View.OnClickListener {
    private SetNewPassword pass;
    public SetNewPasswordController(SetNewPassword pass) {
        this.pass = pass;
    }

    @Override
    public void onClick(View v) {
        if(pass.getNew_p().equals("") || pass.getRe_new().equals(""))
            Toast.makeText(pass, "Password minimum eight characters, at least one uppercase letter, one lowercase letter and one number", Toast.LENGTH_LONG).show();
        else if(!pass.getNew_p().equals(pass.getRe_new()))
            Toast.makeText(pass, "Password are not same!", Toast.LENGTH_LONG).show();
        else if(pass.isCanShow() && !pass.getUser().getPassword().equals(pass.getOld()))
            Toast.makeText(pass, "Old password not correct", Toast.LENGTH_LONG).show();
        else{
            try {
                Connection con = Database.getDatabase().getCon();
                Statement st = con.createStatement();
                String password = pass.getNew_p();
                st.execute("UPDATE user SET `password`='"+password+"' WHERE email='"+ pass.getUser().getEmail()+"';");
                Toast.makeText(pass, "Password Changed",Toast.LENGTH_LONG).show();
                pass.getUser().setPassword(password);
                pass.finish();
            }catch (Exception e){
                Toast.makeText(pass, "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        }
    }
}
