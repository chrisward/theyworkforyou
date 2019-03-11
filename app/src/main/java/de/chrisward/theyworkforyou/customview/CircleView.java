package de.chrisward.theyworkforyou.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
    private int circleColor = Color.WHITE;
    private Paint paint;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @SuppressWarnings("unused")
    public void setCircleColor(int color) {
        this.circleColor = color;
        invalidate();
    }

    @SuppressWarnings("unused")
    public int getCircleColor() {
        return circleColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int contentWidth = width - (paddingLeft + paddingRight);
        int contentHeight = height - (paddingTop + paddingBottom);

        int radius = Math.min(contentWidth, contentHeight) / 2;
        int cx = paddingLeft + (contentWidth / 2);
        int cy = paddingTop + (contentHeight / 2);

        paint.setColor(circleColor);
        canvas.drawCircle(cx, cy, radius, paint);
    }
}
