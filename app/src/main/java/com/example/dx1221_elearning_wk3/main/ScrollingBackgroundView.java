package com.example.dx1221_elearning_wk3.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.dx1221_elearning_wk3.R;

public class ScrollingBackgroundView extends View {
    private final Bitmap backgroundBitmap;
    private final Bitmap backgroundBitmap1;
    private float backgroundPosition;
    private final int screenWidth;

    public ScrollingBackgroundView(Context context, AttributeSet attrs) {
        super(context, attrs);

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ruzzelmainimg);
        backgroundBitmap = Bitmap.createScaledBitmap(bmp, screenWidth, screenHeight, true);
        backgroundBitmap1 = Bitmap.createScaledBitmap(bmp, screenWidth, screenHeight, true);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(backgroundBitmap, backgroundPosition, 0, null);
        canvas.drawBitmap(backgroundBitmap1, backgroundPosition + screenWidth, 0, null);

        backgroundPosition -= 5;
        if (backgroundPosition <= -screenWidth) {
            backgroundPosition = 0;
        }

        invalidate();
    }
}
