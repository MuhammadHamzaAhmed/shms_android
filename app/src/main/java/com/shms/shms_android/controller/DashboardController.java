package com.shms.shms_android.controller;

import android.content.Intent;
import android.view.View;

import com.shms.shms_android.R;
import com.shms.shms_android.model.Database;
import com.shms.shms_android.view.DashBoard;
import com.shms.shms_android.view.Diabetes;
import com.shms.shms_android.view.HealthMonitoring;
import com.shms.shms_android.view.Heart;
import com.shms.shms_android.view.Settings;
import com.shms.shms_android.view.TonsilReport;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardController implements View.OnClickListener {
    private final DashBoard board;

    public DashboardController(DashBoard board) {
        this.board = board;
        try {
            Connection con = Database.getDatabase().getCon();
            PreparedStatement st = con.prepareStatement("SELECT profile from profile where email=?");
            st.setString(1, board.getUser().getEmail());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Blob blb = rs.getBlob(1);
                InputStream in1 = blb.getBinaryStream();
                board.setProfilePic(in1);
            }
        }catch (Exception e){ }
    }

    @Override
    public void onClick(View v) {
        if (board.isHealth(v))
            health();
        else if (board.isHeartAttack(v))
            heartAttack();
        else if (board.isDiabetes(v))
            diabetes();
        else if (board.isTonsil(v))
            tonsil();
        else if (board.isSetting(v))
            setting();
        else if (board.isProfilePic(v))
            profilePic();
    }

    private void profilePic() {

    }

    private void setting() {
        Intent i = new Intent(board, Settings.class);
        i.putExtra("user", board.getUser());
        board.startActivity(i);
        board.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }

    private void tonsil() {
        Intent i = new Intent(board, TonsilReport.class);
        i.putExtra("user", board.getUser());
        board.startActivity(i);
        board.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }

    private void diabetes() {
        Intent i = new Intent(board, Diabetes.class);
        i.putExtra("user", board.getUser());
        board.startActivity(i);
        board.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }

    private void heartAttack() {
        Intent i = new Intent(board, Heart.class);
        i.putExtra("user", board.getUser());
        board.startActivity(i);
        board.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }

    private void health() {
        Intent i = new Intent(board, HealthMonitoring.class);
        i.putExtra("user", board.getUser());
        board.startActivity(i);
        board.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
}
