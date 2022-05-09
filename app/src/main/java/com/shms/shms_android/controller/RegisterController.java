package com.shms.shms_android.controller;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.shms.shms_android.R;
import com.shms.shms_android.model.Database;
import com.shms.shms_android.model.Date;
import com.shms.shms_android.view.Heart;
import com.shms.shms_android.view.LoginView;
import com.shms.shms_android.view.RegisterView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class RegisterController implements View.OnClickListener{
    private final RegisterView view;
    private DatePickerDialog dialog;


    public RegisterController(RegisterView view) {
        this.view = view;
        initDatePicker(view);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int mon = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        mon += 1;
        view.setReg_dob(Date.getStringDate(year, mon, day));
        view.setDBDate(Date.getDatabaseDate(year, mon, day));
    }

    private void initDatePicker(RegisterView view) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker v, int year, int month, int day) {
                month += 1;
                String date = Date.getStringDate(year, month, day);
                view.setReg_dob(date);
                view.setDBDate(Date.getDatabaseDate(year, month, day));
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int mon = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        dialog = new DatePickerDialog(view, AlertDialog.THEME_HOLO_LIGHT, dateSetListener, year, mon, day);
    }

    @Override
    public void onClick(View v) {
        if(view.getAlready(v))
            already();
        else if(view.getRegister(v))
            register();
        else if(view.getReg_dob(v))
            dialog.show();

    }

    private void register() {
        String name = view.getReg_name();
        String email = view.getReg_email();
        String pass = view.getReg_pass();
        String dob = view.getDbDate();
        int gen = view.getGender();
        String re = "^(?=.{1,64}@)[\\p{L}0-9\\+_-]+(\\.[\\p{L}0-9\\+_-]+)*@"
                + "[^-][\\p{L}0-9\\+-]+(\\.[\\p{L}0-9\\+-]+)*(\\.[\\p{L}]{2,})$";
        if(name.equals("") || email.equals("")||pass.equals("")||dob.equals(""))
            Toast.makeText(view, "Fill the form first", Toast.LENGTH_LONG).show();
        else if(!email.matches(re))
            Toast.makeText(view, "Wrong email address", Toast.LENGTH_LONG).show();
        else if(!pass.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"))
            Toast.makeText(view, "Password minimum eight characters, at least one uppercase letter, one lowercase letter and one number", Toast.LENGTH_LONG).show();
        else {
            try {
                Connection con = Database.getDatabase().getCon();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * from user where email=\"" + email + "\"");
                if(rs.next()){
                    Toast.makeText(view, "Account already associated with this male", Toast.LENGTH_LONG).show();
                }else{
                    String sql = "INSERT INTO user(email,name,password,gender,dob)\n" +
                            "VALUES(\""+email+"\", \""+name+"\",\""+pass+"\","+gen+",'"+dob+"');";
                    Toast.makeText(view, "Account Created", Toast.LENGTH_LONG).show();
                    view.startActivity(new Intent(view, LoginView.class));
                    view.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
                }
            }catch (Exception e){
                Toast.makeText(view, "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void already() {
        view.startActivity(new Intent(view, LoginView.class));
        view.overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
    }
}
