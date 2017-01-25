package com.example.n3815.new_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by N3815 on 2016-12-08.
 */

public class SubActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub); // activity_main 메인 액티비티를 호출하라! → layout/activity_main.xml

        Log.v("==========================","onCreate");
    }
}
