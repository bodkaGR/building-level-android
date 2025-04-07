package com.bodkasoft.buildinglevel.data.listener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.bodkasoft.buildinglevel.data.SensorCallback;

public class LevelSensorListener implements SensorEventListener {
    private final SensorCallback callback;

    public LevelSensorListener(SensorCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] rotationMatrix = getRotationMatrixFromVector(event.values);
        float[] remappedMatrix = remapCoordinateSystem(rotationMatrix);
        float[] orientationAngles = getOrientationAngles(remappedMatrix);
        float angleZ = -orientationAngles[2];

        if (callback != null) {
            callback.onAngleZChanged(angleZ);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    private float[] getRotationMatrixFromVector(float[] rotationVector) {
        float[] matrix = new float[16];
        SensorManager.getRotationMatrixFromVector(matrix, rotationVector);
        return matrix;
    }

    private float[] remapCoordinateSystem(float[] inputMatrix) {
        float[] remapped = new float[16];
        SensorManager.remapCoordinateSystem(
                inputMatrix,
                SensorManager.AXIS_X,
                SensorManager.AXIS_Z,
                remapped
        );
        return remapped;
    }

    private float[] getOrientationAngles(float[] rotationMatrix) {
        float[] orientations = new float[3];
        SensorManager.getOrientation(rotationMatrix, orientations);
        for (int i = 0; i < orientations.length; i++) {
            orientations[i] = (float) Math.toDegrees(orientations[i]);
        }
        return orientations;
    }
}
