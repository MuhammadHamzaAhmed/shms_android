package com.shms.shms_android.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.gms.common.util.IOUtils;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.shms.shms_android.R;
import com.shms.shms_android.controller.DashboardController;
import com.shms.shms_android.databinding.ActivityDashBoardBinding;
import com.shms.shms_android.model.User;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashBoard extends AppCompatActivity {

    private CardView health;
    private CardView heartAttack;
    private CardView diabetes;
    private CardView tonsil;
    private CardView setting;
    private TextView username;
    private CircleImageView profile;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        changeStatusBarColor();
        health = findViewById(R.id.health_monitering);
        heartAttack = findViewById(R.id.heartAttack);
        diabetes = findViewById(R.id.diabetes);
        tonsil = findViewById(R.id.tonsil);
        setting = findViewById(R.id.setting);
        username = findViewById(R.id.userName);
        profile = findViewById(R.id.profileImage);
        user = (User) getIntent().getSerializableExtra("user");
        username.setText(user.getUserName());
        DashboardController con = new DashboardController(this);
        health.setOnClickListener(con);
        diabetes.setOnClickListener(con);
        heartAttack.setOnClickListener(con);
        tonsil.setOnClickListener(con);
        setting.setOnClickListener(con);
        profile.setOnClickListener(con);
    }

    public void setProfilePic(InputStream in) {
        try {
            byte[] br = IOUtils.toByteArray(in);
            String s = new String(br, StandardCharsets.UTF_8);
            Bitmap bt = BitmapFactory.decodeByteArray(br, 0, br.length);
            profile.setImageBitmap(bt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window win = getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public boolean isHealth(View v) {
        return v.equals(health);
    }

    public boolean isDiabetes(View v) {
        return v.equals(diabetes);
    }

    public boolean isHeartAttack(View v) {
        return v.equals(heartAttack);
    }

    public boolean isTonsil(View v) {
        return v.equals(tonsil);
    }

    public boolean isSetting(View v) {
        return v.equals(setting);
    }

    public boolean isProfilePic(View v){
        return v.equals(profile);
    }

    public User getUser() {
        return user;
    }
}