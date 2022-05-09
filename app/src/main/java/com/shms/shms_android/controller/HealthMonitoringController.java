package com.shms.shms_android.controller;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.shms.shms_android.R;
import com.shms.shms_android.model.Database;
import com.shms.shms_android.model.User;
import com.shms.shms_android.view.DashBoard;
import com.shms.shms_android.view.HealthMonitoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HealthMonitoringController implements View.OnClickListener {

    private HealthMonitoring mon;

    public HealthMonitoringController(HealthMonitoring mon) {
        this.mon = mon;
        setText();
    }

    private void setText() {
        Connection con=null;
        Statement st=null;
        ResultSet rs;
        User user = mon.getUser();
        try {
            con = Database.getDatabase().getCon();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM `healthmonitoring` WHERE email = '"+user.getEmail()+"';");
            if(rs.next()){
                String temp = rs.getString("temperature");
                String hr = rs.getString("heartRate");
                String bp = rs.getString("bloodPressure");
                String sg = rs.getString("sugar");
                mon.setText(temp, hr, bp, sg);
            }
        }catch (Exception e){
            Toast.makeText(mon, "Check internet connection"+user, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(mon, DashBoard.class);
        i.putExtra("user", mon.getUser());
        mon.startActivity(i);
        mon.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
}
