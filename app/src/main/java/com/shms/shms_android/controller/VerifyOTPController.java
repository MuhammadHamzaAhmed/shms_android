package com.shms.shms_android.controller;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.shms.shms_android.view.VerifyOTP;

public class VerifyOTPController implements OnClickListener{

    private VerifyOTP verifyOTP;

    public VerifyOTPController(VerifyOTP verifyOTP) {
        this.verifyOTP = verifyOTP;
    }

    @Override
    public void onClick(View v) {
        String otp = verifyOTP.getOtp();
        String entered = verifyOTP.getEditText();
        if(otp.equals(entered)){

        }else{
            Toast.makeText(verifyOTP, "Wrong OTP", Toast.LENGTH_LONG).show();
        }
    }
}
