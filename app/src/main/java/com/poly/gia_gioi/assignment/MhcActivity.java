package com.poly.gia_gioi.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

public class MhcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhc);
        Toolbar toolbarmhc = findViewById(R.id.toolmhc);
        toolbarmhc.setTitle("BookManager");

        setSupportActionBar(toolbarmhc);

        Timer timer=new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(MhcActivity.this,LoginActivity.class));
                finish();
            }
        },1500);
    }
}
