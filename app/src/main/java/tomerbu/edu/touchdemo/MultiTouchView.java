package tomerbu.edu.touchdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

/**
 * Created by TomerBu on 07/05/2015.
 */
public class MultiTouchView extends View {
    private static final float RADIUS = 50;
    private static final String TAG = "TomerBu";
    private Paint mPaint;
    private HashMap<Integer, PointF> mActivePointers = new HashMap<>();
    private static final int[] COLORS = {Color.BLUE, Color.YELLOW, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED, Color.GRAY};

    //Constructors
    public MultiTouchView(Context context) {
        this(context, null);
    }

    public MultiTouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //the masked action, returns either ACTION_POINTER_DOWN Or ACTION_POINTER_UP, Or ACTION_MOVE (For all the pointers movement together).
        int actionMasked = event.getActionMasked();
        //The number of pointers
        int pointerCount = event.getPointerCount();
        int pointerIndex = event.getActionIndex();

        //getX(int idx) returns the x coordinate for a specific pointer
        //getY(int idx) returns the y coordinate for a specific pointer

        //A MotionEvent effectively stores information about each pointer in an array. The index of a pointer is its position within this array.

        //Each pointer also has an ID mapping that stays persistent across touch events to allow tracking an individual pointer across the entire gesture.
        int pointerId = event.getPointerId(pointerIndex);


        switch (actionMasked) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                PointF point = new PointF();
                float x = event.getX();
                float y = event.getY();
                point.set(x, y); //same as: //point.x = x; point.y = y;
                mActivePointers.put(pointerId, point);
                break;

            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int currentPointerId = event.getPointerId(i);
                    PointF pointf = mActivePointers.get(currentPointerId);
                    if (pointf != null) {
                        pointf.x = event.getX(i); //The point is updated in the HashMap/Dictionary by reference
                        pointf.y = event.getY(i);
                    }
                }


                //Logging the History:
                int historySize = event.getHistorySize();
                Log.d(TAG, "HistorySize: " + historySize);
                for (int h = 0; h < historySize; h++) {
                    for (int p = 0; p < event.getPointerCount(); p++) {
                        float historicalX = event.getHistoricalX(p, h);
                        float historicalY = event.getHistoricalY(p, h);
                        Log.d(TAG, "HistoricalX: " + historicalX);
                        Log.d(TAG, "HistoricalY: " + historicalY);
                    }
                }

                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                mActivePointers.remove(pointerId);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Integer key : mActivePointers.keySet()) {
            PointF pointF = mActivePointers.get(key);
            if (pointF != null) {
                mPaint.setColor(COLORS[key % 7]);
                canvas.drawCircle(pointF.x, pointF.y, RADIUS, mPaint);
            }
        }

    }
}
