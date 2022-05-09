package com.shms.shms_android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.shms.shms_android.R;
import com.shms.shms_android.controller.SettingController;
import com.shms.shms_android.model.User;

public class Settings extends AppCompatActivity {
    private ImageView backButton;
    private CardView changePassword;
    private CardView logOut;
    private CardView help;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        changeStatusBarColor();

        user = (User) getIntent().getSerializableExtra("user");

        changePassword = findViewById(R.id.changePassword);
        backButton = findViewById(R.id.back_setting);
        logOut = findViewById(R.id.logout_set);
        help = findViewById(R.id.help);

        SettingController con = new SettingController(this);
        changePassword.setOnClickListener(con);
        backButton.setOnClickListener(con);
        logOut.setOnClickListener(con);
        help.setOnClickListener(con);
    }

    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window win = getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public boolean getBackButton(View v) {
        return backButton.equals(v);
    }

    public boolean getChangePassword(View v) {
        return changePassword.equals(v);
    }

    public boolean getLogOut(View v) {
        return logOut.equals(v);
    }

    public boolean getHelp(View v) {
        return help.equals(v);
    }

    public User getUser() {
        return user;
    }
}