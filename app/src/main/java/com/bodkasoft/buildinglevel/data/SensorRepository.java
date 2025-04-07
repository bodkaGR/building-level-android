package com.bodkasoft.buildinglevel.data;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.bodkasoft.buildinglevel.data.listener.LevelSensorListener;

public class SensorRepository {
    private final SensorManager sensorManager;
    private final Sensor accelerometer;
    private SensorEventListener listener;

    public SensorRepository(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void startListening(SensorCallback callback) {
        listener = new LevelSensorListener(callback);
        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    public void stopListening() {
        if (listener != null) {
            sensorManager.unregisterListener(listener);
            listener = null;
        }
    }
}
