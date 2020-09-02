package com.alpine.team1.AlpineClassTeam1.CarStatus;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.CompoundButton;

public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra("level",0);
        batterylistener.onListener(level + "");
        System.out.println(level + "%");
    }

    public batteryListener batterylistener;
    public interface batteryListener{
        public void onListener(String level);
//        void onServiceConnected(ComponentName componentName, IBinder iBinder);
//
//        void onServiceDisconnected(ComponentName componentName);
//
//        void onCheckedChanged(CompoundButton compoundButton, boolean b);
    }
    public void setBatteryListener(batteryListener batterylistener){
        this.batterylistener = batterylistener;
    }
}
