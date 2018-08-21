package com.benzneststudios.myandroidslice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.math.MathUtils;

public class MySliceBroadcastReceiver extends BroadcastReceiver {

    public static String ACTION_CHANGE_TEMP = "com.benzneststudios.myandroidslice.ACTION_CHANGE_TEMP";
    public static String EXTRA_TEMP_VALUE = "ccom.benzneststudios.myandroidslice.EXTRA_TEMP_VALUE";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (ACTION_CHANGE_TEMP.equals(action) && intent.getExtras() != null) {
            int newValue = intent.getExtras().getInt(EXTRA_TEMP_VALUE, 25);
            updateVolume(context, newValue);
        }
    }

    private void updateVolume(Context context, int newValue) {

        MyData.volume = newValue;

        // Should notify the URI to let any slices that might be displaying know to update.
        Uri uri = Uri.parse("content://com.benzneststudios.myandroidslice/volume");
        context.getContentResolver().notifyChange(uri, null);
    }
}