package com.bodkasoft.buildinglevel.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bodkasoft.buildinglevel.domain.CalculatePitch;

public class LevelViewModel extends ViewModel {
    private final MutableLiveData<Integer> pitchLiveData = new MutableLiveData<>();
    private final CalculatePitch calculatePitch = new CalculatePitch();

    public void onSensorData(float x, float y) {
        int pitch = Math.round(calculatePitch.calculatePitch(x, y));
        pitchLiveData.setValue(pitch);
    }

    public LiveData<Integer> getPitch() {
        return pitchLiveData;
    }
}
