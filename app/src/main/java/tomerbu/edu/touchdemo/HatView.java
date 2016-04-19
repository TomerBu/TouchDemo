package tomerbu.edu.touchdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tomerbuzaglo on 19/04/2016.
 */
public class HatView extends View {
    //Fields
    Bitmap hatBitmap;
    float xPosHat = 0;
    float yPosHat = 0;

    //Constructors:
    public HatView(Context context) {
        this(context, null, 0);
    }

    public HatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        hatBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hat);
    }

    //OnDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Hat
        canvas.drawBitmap(hatBitmap, xPosHat, yPosHat, null);
    }

    //test collision
    private boolean collisionTest(MotionEvent event) {
        return event.getX() <= xPosHat + hatBitmap.getWidth() && event.getX() >= xPosHat
                && event.getY() <= yPosHat + hatBitmap.getHeight() && event.getY() >= xPosHat;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println(collisionTest(event) ? "Hit" : "");
        return true;
    }
}
