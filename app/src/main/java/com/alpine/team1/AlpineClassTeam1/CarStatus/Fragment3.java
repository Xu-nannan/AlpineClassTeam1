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
import android.widget.Button;
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

public class Fragment3 extends Fragment implements ServiceConnection, CompoundButton.OnCheckedChangeListener{
    private Switch aswitch_air;
    private Switch aswitch_stopcar;
    private Switch aswitch_safety;
    private Switch aswitch_rain;
    private ImageView imageView_BREAK;
    private ImageView imageView_BELT;
    private ImageView imageView_WIPER;
    private ImageView imageView_AIR;
    private ImageView imageView_AIR_COLD;
    private ImageView imageView_AIR_HOT;

    ISocketBinder binder;
    StateViewModel viewModel;

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
        View view = inflater.inflate(R.layout.fragment3,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        aswitch_air = getActivity().findViewById(R.id.switch_air);
        aswitch_stopcar = getActivity().findViewById(R.id.sw_stopcar);
        aswitch_safety = getActivity().findViewById(R.id.sw_safety);
        aswitch_rain = getActivity().findViewById(R.id.sw_rain);

        imageView_BREAK = getActivity().findViewById(R.id.imageView_BREAK);
        imageView_BELT = getActivity().findViewById(R.id.imageView_BELT);
        imageView_WIPER = getActivity().findViewById(R.id.imageView_WIPER);
        imageView_AIR = getActivity().findViewById(R.id.imageView_AIR);
//        imageView_AIR_COLD = getActivity().findViewById(R.id.imageView_AIR_COLD);
//        imageView_AIR_HOT = getActivity().findViewById(R.id.imageView_AIR_HOT);

        viewModel = new ViewModelProvider(this).get(StateViewModel.class);
        viewModel.getMutableLiveData().observe(this, new Observer<State>() {
            @Override
            public void onChanged(State state) {
                aswitch_stopcar.setChecked(state.isShousha());
                aswitch_safety.setChecked(state.isAnquandai());
                aswitch_rain.setChecked(state.isYushuaqi());
                aswitch_air.setChecked(state.isKongtiao());

                if (state.isShousha()) imageView_BREAK.setImageResource(R.drawable.ic_break_up);
                else imageView_BREAK.setImageResource(R.drawable.ic_break_down);

                if (state.isAnquandai()) imageView_BELT.setImageResource(R.drawable.ic_belt_down);
                else imageView_BELT.setImageResource(R.drawable.ic_belt_up);

                if (state.isYushuaqi()) imageView_WIPER.setImageResource(R.drawable.ic_wiper_open);
                else imageView_WIPER.setImageResource(R.drawable.ic_wiper_close);

                if (state.isKongtiao()) imageView_AIR.setImageResource(R.drawable.ic_air_open);
                else imageView_AIR.setImageResource(R.drawable.ic_air_close);
            }
        });
      //////////////////////////////////////////////////////////////////////////
        aswitch_stopcar.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        aswitch_safety.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        aswitch_rain.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        aswitch_air.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        //////////////////////////////////////////////////////
    }

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

    private static final String TAG = "Fragment3";
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
            case R.id.sw_stopcar:
                viewModel.setShousha(b);
                break;
            case R.id.sw_safety:
                viewModel.setAnquandai(b);
                break;
            case R.id.sw_rain:
                viewModel.setYushuaqi(b);
                break;
            case R.id.switch_air:
                viewModel.setKongtiao(b);
                break;
        }
    }
}
