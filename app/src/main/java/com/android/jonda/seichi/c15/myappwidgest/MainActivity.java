package com.android.jonda.seichi.c15.myappwidgest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.jonda.seichi.c15.service.MyJobService;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Trigger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
