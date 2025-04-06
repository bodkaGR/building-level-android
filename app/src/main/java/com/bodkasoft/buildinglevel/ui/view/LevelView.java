package com.bodkasoft.buildinglevel.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class LevelView extends View {
    private int angle = 0;
    private final Paint paint = new Paint();

    public LevelView(Context context) {
        super(context);
    }

    public void setAngle(int angle) {
        this.angle = angle;
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();

        float centerX = width / 2;
        float centerY = height / 2;

        canvas.drawColor(Color.BLACK);
        paint.setColor((angle == 0) ? Color.GREEN: Color.BLUE);
        paint.setStrokeWidth(10f);

        canvas.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(angle);

        canvas.drawLine(-width, 0, width, 0, paint);
        canvas.restore();
    }
}
