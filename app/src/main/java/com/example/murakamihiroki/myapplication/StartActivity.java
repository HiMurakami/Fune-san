package com.example.murakamihiroki.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    //Startをタップしたときの振る舞い
    public void startTap(View view) {
        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
    }
}
