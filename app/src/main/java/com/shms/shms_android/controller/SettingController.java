package com.shms.shms_android.controller;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.shms.shms_android.R;
import com.shms.shms_android.view.DashBoard;
import com.shms.shms_android.view.LoginView;
import com.shms.shms_android.view.SetNewPassword;
import com.shms.shms_android.view.Settings;

public class SettingController implements View.OnClickListener {

    private final Settings set;

    public SettingController(Settings set) {
        this.set = set;
    }

    @Override
    public void onClick(View v) {
        if(set.getBackButton(v)){
            Intent i = new Intent(set, DashBoard.class);
            i.putExtra("user", set.getUser());
            set.startActivity(i);
            set.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        }else if(set.getChangePassword(v)){
            Intent i = new Intent(set, SetNewPassword.class);
            i.putExtra("user", set.getUser());
            set.startActivity(i);
            set.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        }else if(set.getLogOut(v)){
            Intent i = new Intent(set, LoginView.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            set.startActivity(i);
            set.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
            set.finish();
        }else if(set.getHelp(v)){
            Uri uri = Uri.parse("http://www.google.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            set.startActivity(intent);
        }
    }
}
