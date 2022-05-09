package com.shms.shms_android.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.shms.shms_android.R;
import com.shms.shms_android.model.Database;
import com.shms.shms_android.model.Date;
import com.shms.shms_android.view.DashBoard;
import com.shms.shms_android.view.Diabetes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DiabetesController implements View.OnClickListener {
    private Diabetes diabetes;

    public DiabetesController(Diabetes diabetes) {
        this.diabetes = diabetes;
        try {
            Connection con = Database.getDatabase().getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `diabetes` WHERE email='"+ this.diabetes.getUser().getEmail()+"'");
            while (rs.next()) {
                int rt = rs.getInt("result");
                if(rt != -1) {
                    java.sql.Date date = rs.getDate("date");
                    String sug = "Suggestion: " + rs.getString("sug");
                    String dt = "Date:  " + Date.getStringDate(date.getYear() + 1900, date.getMonth()+1, date.getDate()+1);
                    String result = "Result:  " + ((rt == 0) ? "Negative" : "Positive");
                    this.diabetes.addCard(dt, result, sug);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this.diabetes, "Check you internet connection", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(diabetes, DashBoard.class);
        i.putExtra("user", diabetes.getUser());
        diabetes.startActivity(i);
        diabetes.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
}
