package com.bodkasoft.buildinglevel.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LevelViewModel extends ViewModel {
    private final MutableLiveData<Integer> pitchLiveData = new MutableLiveData<>();

    public void onSensorData(float axisZ) {
        pitchLiveData.setValue(Math.round(axisZ));
    }

    public LiveData<Integer> getPitch() {
        return pitchLiveData;
    }
}
