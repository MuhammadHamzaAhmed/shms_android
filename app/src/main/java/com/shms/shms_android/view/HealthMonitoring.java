package com.shms.shms_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.shms.shms_android.R;
import com.shms.shms_android.controller.HealthMonitoringController;
import com.shms.shms_android.model.User;

public class HealthMonitoring extends AppCompatActivity {

    private TextView temp;
    private TextView heartRate;
    private TextView bloodPressure;
    private TextView sugar;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_monitoring);
        changeStatusBarColor();
        temp = findViewById(R.id.health_temp);
        heartRate = findViewById(R.id.health_heart);
        bloodPressure = findViewById(R.id.health_bp);
        sugar = findViewById(R.id.health_sugar);
        Intent i = getIntent();
        user = (User) i.getSerializableExtra("user");
        HealthMonitoringController con = new HealthMonitoringController(this);
        findViewById(R.id.back_health).setOnClickListener(con);

    }

    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window win = getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void setText(String temperature, String heart_rate, String blood_pressure, String sugar){
        temp.setText(temperature);
        heartRate.setText(heart_rate);
        bloodPressure.setText(blood_pressure);
        this.sugar.setText(sugar);
    }

    public User getUser() {
        return user;
    }
}