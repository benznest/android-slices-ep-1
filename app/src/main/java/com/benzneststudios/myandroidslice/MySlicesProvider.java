package com.benzneststudios.myandroidslice;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;

public class MySlicesProvider extends SliceProvider {

    @Override
    public boolean onCreateSliceProvider() {
        return true;
    }

    public Slice onBindSlice(Uri sliceUri) {
        switch (sliceUri.getPath()) {
            case "/volume":
                return createTemperatureSlice(sliceUri);
        }
        return null;
    }

    private Slice createTemperatureSlice(Uri sliceUri) {
        // Construct our parent builder
        ListBuilder listBuilder = new ListBuilder(getContext(), sliceUri, ListBuilder.INFINITY);

        // Add the row to the parent builder
        listBuilder.addRow(new ListBuilder.RowBuilder()
                .setTitle("Volume = " + MyData.volume)
                .setPrimaryAction(getSliceActionOpenActivity()));

        listBuilder.addAction(getSliceActionIncreaseTemperature());
        listBuilder.addAction(getSliceActionDecreaseTemperature());
        // Build the slice
        return listBuilder.build();
    }

    private PendingIntent createPendingIntentWithData(int reqCode, int value) {
        Intent intent = new Intent(MySliceBroadcastReceiver.ACTION_CHANGE_TEMP);
        intent.setClass(getContext(), MySliceBroadcastReceiver.class);
        intent.putExtra(MySliceBroadcastReceiver.EXTRA_TEMP_VALUE, value);
        return PendingIntent.getBroadcast(getContext(), reqCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private SliceAction getSliceActionIncreaseTemperature() {
        Log.d("Slice", "Increase");
        SliceAction tempUp = new SliceAction(createPendingIntentWithData(1111, MyData.volume + 1), IconCompat.createWithResource(getContext(), R.drawable.ic_up), "+");
        return tempUp;
    }

    private SliceAction getSliceActionDecreaseTemperature() {
        Log.d("Slice", "Decrease");
        SliceAction tempDown = new SliceAction(createPendingIntentWithData(2222, MyData.volume - 1), IconCompat.createWithResource(getContext(), R.drawable.ic_down), "-");
        return tempDown;
    }

    private SliceAction getSliceActionOpenActivity() {

        // The primary action; this will activate when the row is tapped
        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);
        SliceAction openTempActivity = new SliceAction(pendingIntent,
                IconCompat.createWithResource(getContext(),
                        R.drawable.ic_launcher_foreground), "controls");
        return openTempActivity;
    }
}
