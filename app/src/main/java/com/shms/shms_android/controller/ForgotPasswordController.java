package com.shms.shms_android.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.shms.shms_android.R;
import com.shms.shms_android.model.Database;
import com.shms.shms_android.model.MailSender;
import com.shms.shms_android.view.ForgotPassword;
import com.shms.shms_android.view.LoginView;
import com.shms.shms_android.view.VerifyOTP;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class ForgotPasswordController implements View.OnClickListener {
    private final ForgotPassword forgotPassword;

    public ForgotPasswordController(ForgotPassword forgotPassword) {
        this.forgotPassword = forgotPassword;
    }

    @Override
    public void onClick(View v) {
        if (forgotPassword.checkBack(v))
            back();
        else if (forgotPassword.checkNext(v))
            next();
    }

    private void next() {
        String re = "^(?=.{1,64}@)[\\p{L}0-9\\+_-]+(\\.[\\p{L}0-9\\+_-]+)*@"
                + "[^-][\\p{L}0-9\\+-]+(\\.[\\p{L}0-9\\+-]+)*(\\.[\\p{L}]{2,})$";
        if (forgotPassword.getEmail().matches(re)) {
            try {
                Database db = Database.getDatabase();
                if (db.getCon() != null) {
                    Statement st = db.getCon().createStatement();
                    ResultSet rs = st.executeQuery("SELECT * from user where email=\"" + forgotPassword.getEmail() + "\"");
                    if (rs.next()) {
                        Random rand = new Random();
                        Intent i = new Intent(forgotPassword, VerifyOTP.class);
                        int otp = 100000+rand.nextInt(900000);
                        i.putExtra("OTP", otp);
                        i.putExtra("email", forgotPassword.getEmail());
                        forgotPassword.startActivity(i);
                        forgotPassword.overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                        try {
                            MailSender.resetPassword(rs.getString("name"), otp, rs.getString("email"));
                        }catch (Exception e) {
                            Toast.makeText(forgotPassword, e.toString(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(forgotPassword, "Email not found", Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(forgotPassword, "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(forgotPassword, "WrongMail", Toast.LENGTH_SHORT).show();
        }
    }

    private void back() {
        forgotPassword.startActivity(new Intent(forgotPassword, LoginView.class));
        forgotPassword.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
}
