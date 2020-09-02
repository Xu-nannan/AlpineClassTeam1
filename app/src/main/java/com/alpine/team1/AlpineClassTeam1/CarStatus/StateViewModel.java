package com.alpine.team1.AlpineClassTeam1.CarStatus;

import android.os.RemoteException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alpine.team1.AlpineClassTeam1.CarStatus.State;
import com.alpine.team1.tcpsocketservice.ISocketBinder;
import com.google.gson.Gson;

public class StateViewModel extends ViewModel{
    private MutableLiveData<State> mutableLiveData = new MutableLiveData<>();
    private State state = new State();
    private ISocketBinder binder;

    public StateViewModel() {
        this.mutableLiveData.setValue(state);
    }

    public LiveData<State> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setBinder(ISocketBinder binder) {
        this.binder = binder;
    }

    public void setChemen1(boolean b){
        state.setChemeng1(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }

    public void setChemen2(boolean b){
        state.setChemeng2(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }

    public void setChemen3(boolean b){
        state.setChemeng3(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setChemen4(boolean b){
        state.setChemeng4(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setHoubeixing(boolean b){
        state.setHoubeixiang(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setKongtiao(boolean b){
        state.setKongtiao(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setChemensuo(boolean b){
        state.setChemensuo(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setChechuang(boolean b){
        state.setChechuang(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setShousha(boolean b){
        state.setShousha(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }

    public void setAnquandai(boolean b){
        state.setAnquandai(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setYushuaqi(boolean b){
        state.setYushuaqi(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setShengyudianliang(double shengyudianliang){
        state.setShengyudianliang(shengyudianliang);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setYixingshigongli(double yixingshigongli){
        state.setYixingshigongli(yixingshigongli);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setShengyuxingshigongli(double shengyuxingshigongli){
        state.setShengyuxingshigongli(shengyuxingshigongli);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }
    public void setCarstart(boolean b){
        state.setCarstart(b);
        sendLightStateToRemote();
        mutableLiveData.setValue(state);
    }

    private void sendLightStateToRemote() {
        Gson gson = new Gson();
        String json = gson.toJson(state, State.class);
        try {
            binder.sendText("State_DATA="+json);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void receivedRemoteData(String data){
//        if(data.equals("SW1_ON")){
//            light.setLightOn1(true);
//        }else if(data.equals("SW1_OFF")){
//            light.setLightOn1(false);
//        } else if (data.equals("SW2_ON")) {
//            light.setLightOn2(true);
//        } else if (data.equals("SW2_OFF")) {
//            light.setLightOn2(false);
//        }
        if(!data.startsWith("State_DATA"))return;
        String json = data.substring(data.indexOf("=")+1);
        Gson gson = new Gson();
        State l = gson.fromJson(json, State.class);
        state = l;
        mutableLiveData.postValue(state);
    }
}
