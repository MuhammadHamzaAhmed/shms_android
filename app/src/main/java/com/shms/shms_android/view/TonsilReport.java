package com.shms.shms_android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shms.shms_android.R;
import com.shms.shms_android.controller.DiabetesController;
import com.shms.shms_android.controller.TonsilController;
import com.shms.shms_android.model.User;

public class TonsilReport extends AppCompatActivity {
    private LinearLayout mainLayout;
    private User user;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tonsil_report);

        changeStatusBarColor();
        mainLayout = findViewById(R.id.tonsil_cards);
        back = findViewById(R.id.back_tonsil);
        user = (User) getIntent().getSerializableExtra("user");
        TonsilController con = new TonsilController(this);
        back.setOnClickListener(con);
    }

    public void changeStatusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Window win = getWindow();
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void addCard(String date, String result, String suggestion){
        CardView view = new CardView(mainLayout.getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -1, 1);
        lp.setMargins(20, 50, 20, 0);
        view.setLayoutParams(lp);
        view.setCardBackgroundColor(Color.parseColor("#80880808"));
        view.setCardElevation(6);
        view.setRadius(20);

        LinearLayout layout = new LinearLayout(view.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));

        LinearLayout.LayoutParams txt = new LinearLayout.LayoutParams(-1, -2);
        txt.setMargins(0,0,0,30);
        TextView date_t = new TextView(layout.getContext());
        date_t.setLayoutParams(txt);
        date_t.setText(date);
        date_t.setTypeface(Typeface.DEFAULT_BOLD);
        date_t.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
        date_t.setTextColor(Color.WHITE);

        TextView result_t = new TextView(layout.getContext());
        result_t.setLayoutParams(txt);
        result_t.setText(result);
        result_t.setTypeface(Typeface.DEFAULT_BOLD);
        result_t.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
        result_t.setTextColor(Color.WHITE);

        TextView sug_t = new TextView(layout.getContext());
        sug_t.setLayoutParams(txt);
        sug_t.setText(suggestion);
        sug_t.setTypeface(Typeface.DEFAULT_BOLD);
        sug_t.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
        sug_t.setTextColor(Color.WHITE);

        layout.addView(date_t);
        layout.addView(result_t);
        layout.addView(sug_t);
        view.addView(layout);

        mainLayout.addView(view);
    }

    public User getUser() {
        return user;
    }
}