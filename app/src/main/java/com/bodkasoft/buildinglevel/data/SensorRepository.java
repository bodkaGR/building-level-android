package com.bodkasoft.buildinglevel.data;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorRepository {
    private final SensorManager sensorManager;
    private final Sensor accelerometer;
    private SensorCallback callback;

    public SensorRepository(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void startListening(SensorCallback callback) {
        this.callback = callback;
        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    public void stopListening() {
        sensorManager.unregisterListener(listener);
    }

    private final SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (callback != null) {
                float axisX = event.values[0];
                float axisY = event.values[1];

                callback.onAngleChanged(axisX, axisY);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    public interface SensorCallback {
        void onAngleChanged(float x, float y);
    }
}
