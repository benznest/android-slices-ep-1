package com.benzneststudios.myandroidslice;


import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sliceView = findViewById(R.id.sliceView);
//        sliceView.setMode(View.FOCUSABLES_TOUCH_MODE);
//        LiveData liveData = SliceLiveData.fromUri(this, Uri.parse("content://com.benzneststudios.myandroidslice/calculator"));
//        liveData.observe(this, sliceView);
    }
}
