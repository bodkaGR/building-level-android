package com.bodkasoft.buildinglevel.domain;

public class CalculatePitch {
    public float calculatePitch(float x, float y) {
        return (float) Math.toDegrees(Math.atan2(x, y));
    }
}
