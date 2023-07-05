package com.plus.sycra.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SycraDevicesViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<SycraDevice>> devices;

    // generate a getter for livedata
    //mutable Livedata object holding a list of SycraDevice objects
    public MutableLiveData<List<SycraDevice>> getDevices(){
        if(devices == null){
            devices = new MutableLiveData<>();
            loadDevices();
        }
        return devices;
    }

    private void loadDevices() {
        List<SycraDevice> dummyDevices = new ArrayList<>();
        dummyDevices.add(new SycraDevice("Sycra Device1", "1234-5678-90AB-DEF6"));
        dummyDevices.add(new SycraDevice("Sycra Device2", "2345-6789-0ABC-DEF1"));
        dummyDevices.add(new SycraDevice("Sycra Device3", "3456-7890-4BCD-EF12"));
        dummyDevices.add(new SycraDevice("Sycra Device4", "3456-7797-A0CD-EFK2"));
        dummyDevices.add(new SycraDevice("Sycra Device5", "3436-7790-A01D-EF12"));
        dummyDevices.add(new SycraDevice("Sycra Device6", "3456-7890-A09D-EF1K"));
        devices.postValue(dummyDevices);
    }

}