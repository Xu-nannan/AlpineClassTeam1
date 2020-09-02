package com.alpine.team1.AlpineClassTeam1.CarStatus;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alpine.team1.AlpineClassTeam1.R;
import com.alpine.team1.tcpsocketservice.IOnSocketReceivedListener;
import com.alpine.team1.tcpsocketservice.ISocketBinder;

public class Fragment1 extends Fragment implements BatteryReceiver.batteryListener,ServiceConnection, CompoundButton.OnCheckedChangeListener{

    private TextView textView_battery = null;
    private TextView textView_RemainKm;
    private TextView textView_TravelKm;
    private Switch aSwitch_startup;
    private ImageView imageView_startup;
    private int remainKm,travelKm;

    ISocketBinder binder;
    StateViewModel viewModel;

    private IntentFilter intentFilter = new IntentFilter();
    private BatteryReceiver batteryReceiver = new BatteryReceiver();

    IOnSocketReceivedListener receivedListener = new IOnSocketReceivedListener.Stub() {
        @Override
        public void onReceived(String data) throws RemoteException {

            viewModel.receivedRemoteData(data);
        }
        @Override
        public void onEvent(int e) throws RemoteException {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment1,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView_battery = getActivity().findViewById(R.id.textView_battery);
        textView_RemainKm = getActivity().findViewById(R.id.textView_RemainKm);
        textView_TravelKm = getActivity().findViewById(R.id.textView_TravelKm);
        aSwitch_startup = getActivity().findViewById(R.id.sw_start);
        imageView_startup = getActivity().findViewById(R.id.imageView_startup);

        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        getActivity().getApplicationContext().registerReceiver(batteryReceiver,intentFilter);
        batteryReceiver.setBatteryListener(this);

        viewModel = new ViewModelProvider(this).get(StateViewModel.class);
        viewModel.getMutableLiveData().observe(this, new Observer<State>() {
            @Override
            public void onChanged(State state) {
                aSwitch_startup.setChecked(state.isCarstart());
                if (state.isCarstart()) {
                    imageView_startup.setImageResource(R.drawable.ic_car_start);
                }
                else imageView_startup.setImageResource(R.drawable.ic_car_stop);
            }
        });
        aSwitch_startup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getApplicationContext().unregisterReceiver(batteryReceiver);
    }

    @Override
    public void onListener(String level) {
        textView_battery.setText(level + "%");
        travelKm = Integer.parseInt(level) * 6 ;
        remainKm = 600 - travelKm ;
        textView_TravelKm.setText(remainKm + "km");
        textView_RemainKm.setText(travelKm + "km");

        //更新电量图标
            ImageView imageView = getActivity().findViewById(R.id.imageView2);
            switch ((Integer.parseInt(level)+10)/20){
                case 0:
                    imageView.setImageResource(R.drawable.ic_battery6);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.ic_battery5);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.ic_battery4);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.ic_battery3);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.ic_battery2);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.ic_battery1);
                    break;
            }
    }
/////////////////////////////////////////////////////////////////////////
    @Override
    public void onStart() {
        super.onStart();
        Intent i = new Intent();
        i.setComponent(new ComponentName("com.alpine.team1.tcpsocketservice",
                "com.alpine.team1.tcpsocketservice.SocketService"));
        getActivity().bindService(i, (ServiceConnection) this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(binder!=null){
            try {
                binder.unregisterListener(receivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        getActivity().unbindService((ServiceConnection) this);
    }
//////////////////////////////////////////////////////////////////////////
    private static final String TAG = "fragment1";
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.d(TAG, "onServiceConnected: is called.");
        binder = ISocketBinder.Stub.asInterface(iBinder);
        viewModel.setBinder(binder);
        try {
            binder.registerListener(receivedListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(!compoundButton.isPressed())return;
        switch (compoundButton.getId()){
            case R.id.sw_start:
                viewModel.setCarstart(b);
                break;
        }
    }
}
