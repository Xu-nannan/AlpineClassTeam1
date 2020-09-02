package com.alpine.team1.AlpineClassTeam1.CarStatus;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alpine.team1.AlpineClassTeam1.R;

import com.alpine.team1.tcpsocketservice.IOnSocketReceivedListener;
import com.alpine.team1.tcpsocketservice.ISocketBinder;

public class Fragment2 extends Fragment implements ServiceConnection, CompoundButton.OnCheckedChangeListener{

    private Switch aSwitch_righton;
    private Switch aSwitch_window;
    private Switch aSwitch_rightdown;
    private Switch aSwitch_box;
    private Switch aSwitch_leftdown;
    private Switch aSwitch_lefton;
    private Switch aSwitch_door;

    private ImageView imageView_door;
    private ImageView imageView_windows;
    private ImageView imageView_doorlock;
    private ImageView imageView_trunk;

    ISocketBinder binder;
    StateViewModel viewModel;
    int abc;

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
        View view = inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(StateViewModel.class);
        viewModel.getMutableLiveData().observe(this, new Observer<State>() {
            @Override
            public void onChanged(State state) {
                aSwitch_lefton.setChecked(state.isChemeng1());
                aSwitch_leftdown.setChecked(state.isChemeng2());
                aSwitch_righton.setChecked(state.isChemeng3());
                aSwitch_rightdown.setChecked(state.isChemeng4());
                aSwitch_box.setChecked(state.isHoubeixiang());
                aSwitch_door.setChecked(state.isChemensuo());
                aSwitch_window.setChecked(state.isChechuang());

                Log.d(TAG, 1+String.valueOf(state.isChemeng1()));
                Log.d(TAG, 2+String.valueOf(state.isChemeng2()));
                Log.d(TAG, 3+String.valueOf(state.isChemeng3()));
                Log.d(TAG, 4+String.valueOf(state.isChemeng4()));

                int i;
                if (state.isChemeng1()==true & state.isChemeng2()==false & state.isChemeng3()==false & state.isChemeng4()==false) {
                    i = 0;
                    imageView_door.setImageResource(R.drawable.ic_door_open1);
                    Log.d(TAG, String.valueOf(i));
                } if (state.isChemeng1()==true & state.isChemeng2()==true & state.isChemeng3()==false & state.isChemeng4()==false){
                    i = 1;
                    imageView_door.setImageResource(R.drawable.ic_door_open2);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==true & state.isChemeng2()==true & state.isChemeng3()==true & state.isChemeng4()==false) {
                    i = 2;
                    imageView_door.setImageResource(R.drawable.ic_door_open3);
                    Log.d(TAG, String.valueOf(i));
                } if (state.isChemeng1()==false & state.isChemeng2()==false & state.isChemeng3()==true &state.isChemeng4()==false){
                    i = 3;
                    imageView_door.setImageResource(R.drawable.ic_door_open4);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==false & state.isChemeng2()==false & state.isChemeng3()==true & state.isChemeng4()==true){
                    i = 4;
                    imageView_door.setImageResource(R.drawable.ic_door_open5);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==false & state.isChemeng2()==true & state.isChemeng3()==true & state.isChemeng4()==true){
                    i = 5;
                    imageView_door.setImageResource(R.drawable.ic_door_open6);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==true & state.isChemeng2()==false & state.isChemeng3()==true & state.isChemeng4()==true){
                    i = 6;
                    imageView_door.setImageResource(R.drawable.ic_door_open7);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==true & state.isChemeng2()==false & state.isChemeng3()==false & state.isChemeng4()==true){
                    i = 7;
                    imageView_door.setImageResource(R.drawable.ic_door_open8);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==true & state.isChemeng2()==false & state.isChemeng3()==true & state.isChemeng4()==false){
                    i = 8;
                    imageView_door.setImageResource(R.drawable.ic_door_open9);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==true & state.isChemeng2()==true & state.isChemeng3()==false & state.isChemeng4()==true){
                    i = 9;
                    imageView_door.setImageResource(R.drawable.ic_door_open10);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==false & state.isChemeng2()==true & state.isChemeng3()==true & state.isChemeng4()==false){
                    i = 10;
                    imageView_door.setImageResource(R.drawable.ic_door_open11);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==false & state.isChemeng2()==true & state.isChemeng3()==false & state.isChemeng4()==false){
                    i = 11;
                    imageView_door.setImageResource(R.drawable.ic_door_open12);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==false & state.isChemeng2()==false & state.isChemeng3()==false & state.isChemeng4()==true){
                    i = 12;
                    imageView_door.setImageResource(R.drawable.ic_door_open13);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==false & state.isChemeng2()==true & state.isChemeng3()==false & state.isChemeng4()==true){
                    i = 13;
                    imageView_door.setImageResource(R.drawable.ic_door_open14);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==true & state.isChemeng2()==true & state.isChemeng3()==true & state.isChemeng4()==true){
                    i = 14;
                    imageView_door.setImageResource(R.drawable.ic_door_open15);
                    Log.d(TAG, String.valueOf(i));
                }if (state.isChemeng1()==false & state.isChemeng2()==false & state.isChemeng3()==false & state.isChemeng4()==false){
                    i=15;
                    Log.d(TAG, String.valueOf(i));
                    imageView_door.setImageResource(R.drawable.ic_door_close);
                }

//                switch (i){
//                    case 0:
//                        imageView_door.setImageResource(R.drawable.ic_door_open1);
//                        break;
//                    case 1:
//                        imageView_door.setImageResource(R.drawable.ic_door_open2);
//                        break;
//                    case 2:
//                        imageView_door.setImageResource(R.drawable.ic_door_open3);
//                        break;
//                    case 3:
//                        imageView_door.setImageResource(R.drawable.ic_door_open4);
//                        break;
//                    case 4:
//                        imageView_door.setImageResource(R.drawable.ic_door_open5);
//                        break;
//                    case 5:
//                        imageView_door.setImageResource(R.drawable.ic_door_open6);
//                        break;
//                    case 6:
//                        imageView_door.setImageResource(R.drawable.ic_door_open7);
//                        break;
//                    case 7:
//                        imageView_door.setImageResource(R.drawable.ic_door_open8);
//                        break;
//                    case 8:
//                        imageView_door.setImageResource(R.drawable.ic_door_open9);
//                        break;
//                    case 9:
//                        imageView_door.setImageResource(R.drawable.ic_door_open10);
//                        break;
//                    case 10:
//                        imageView_door.setImageResource(R.drawable.ic_door_open11);
//                        break;
//                    case 11:
//                        imageView_door.setImageResource(R.drawable.ic_door_open12);
//                        break;
//                    case 12:
//                        imageView_door.setImageResource(R.drawable.ic_door_open13);
//                        break;
//                    case 13:
//                        imageView_door.setImageResource(R.drawable.ic_door_open14);
//                        break;
//                    case 14:
//                        imageView_door.setImageResource(R.drawable.ic_door_open15);
//                        break;
//                    default:
//                        imageView_door.setImageResource(R.drawable.ic_door_close);
//                        break;
//                }

                if (state.isChemensuo()) imageView_doorlock.setImageResource(R.drawable.ic_door_unlock);
                else imageView_doorlock.setImageResource(R.drawable.ic_door_lock);

                if (state.isHoubeixiang()) imageView_trunk.setImageResource(R.drawable.ic_trunk_open);
                else imageView_trunk.setImageResource(R.drawable.ic_trunk_close);

                if (state.isChechuang()) imageView_windows.setImageResource(R.drawable.ic_window_open);
                else imageView_windows.setImageResource(R.drawable.ic_windows_close);

            }
        });

        aSwitch_lefton = getActivity().findViewById(R.id.sw_lefton);
        aSwitch_leftdown = getActivity().findViewById(R.id.sw_leftdown);
        aSwitch_righton = getActivity().findViewById(R.id.sw_righton);
        aSwitch_rightdown = getActivity().findViewById(R.id.sw_rightdown);
        aSwitch_door = getActivity().findViewById(R.id.sw_door);
        aSwitch_window = getActivity().findViewById(R.id.sw_window);
        aSwitch_box = getActivity().findViewById(R.id.sw_box);

        imageView_trunk = getActivity().findViewById(R.id.imageView_trunk);
        imageView_doorlock = getActivity().findViewById(R.id.imageView_doorlock);
        imageView_door = getActivity().findViewById(R.id.imageView_door);
        imageView_windows = getActivity().findViewById(R.id.imageView_windows);

        aSwitch_lefton.setOnCheckedChangeListener(this);
        aSwitch_leftdown.setOnCheckedChangeListener(this);
        aSwitch_righton.setOnCheckedChangeListener(this);
        aSwitch_rightdown.setOnCheckedChangeListener(this);
        aSwitch_door.setOnCheckedChangeListener(this);
        aSwitch_window.setOnCheckedChangeListener(this);
        aSwitch_box.setOnCheckedChangeListener(this);
        }

    @Override
    public void onStart() {
        super.onStart();
        Intent i = new Intent();
        i.setComponent(new ComponentName("com.alpine.team1.tcpsocketservice",
                "com.alpine.team1.tcpsocketservice.SocketService"));
        getActivity().bindService(i,this, Context.BIND_AUTO_CREATE);
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
        getActivity().unbindService(this);
    }

    private static final String TAG = "fragment2";
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
            case R.id.sw_lefton:
                viewModel.setChemen1(b);
                break;
            case R.id.sw_leftdown:
                viewModel.setChemen2(b);
                break;
            case R.id.sw_righton:
                viewModel.setChemen3(b);
                break;
            case R.id.sw_rightdown:
                viewModel.setChemen4(b);
                break;
            case R.id.sw_box:
                viewModel.setHoubeixing(b);
                break;
            case R.id.sw_door:
                viewModel.setChemensuo(b);
                break;
            case R.id.sw_window:
                viewModel.setChechuang(b);
                break;
        }
    }
}
