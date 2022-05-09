package com.shms.shms_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shms.shms_android.R;
import com.shms.shms_android.controller.VerifyOTPController;

public class VerifyOTP extends AppCompatActivity {
    private EditText[] editText;
    private Button button;
    private String otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        changeStatusBarColor();
        editText = new EditText[6];
        int[] id = new int[]{R.id.OTP1, R.id.OTP2, R.id.OTP3, R.id.OTP4, R.id.OTP5, R.id.OTP6};
        button = findViewById(R.id.cirRegisterButton);
        for(int i=0;i<6;i++) {
            editText[i] = findViewById(id[i]);
            editText[i].setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    for(int i=0;i<6;i++){
                        if(editText[i].getText().toString().length()==0){
                            editText[i].requestFocus();
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        editText[0].requestFocus();
        Intent i = getIntent();
        otp = i.getStringExtra("OTP");
        TextView v = findViewById(R.id.mail);
        v.setText(i.getStringExtra("email"));
        button.setOnClickListener(new VerifyOTPController(this));

    }

    public void changeStatusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Window win = getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void backLogin(){
        startActivity(new Intent(this, LoginView.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    public String getEditText() {
        StringBuilder otp = new StringBuilder();
        for(EditText edi:editText){
            otp.append(edi.getText().toString());
        }
        return otp.toString();
    }

    public Boolean getButton(View view) {
        return button.equals(view);
    }

    public String getOtp() {
        return otp;
    }

    public void verify(View view) {
    }
}