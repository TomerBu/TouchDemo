package tomerbu.edu.touchdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;

/**
 * Created by admin on 07/05/2015.
 */
public class MultiTouchView extends View {
    private static final float RADIUS = 50;
    private Paint mPaint;
    private HashMap<Integer,PointF> mActivePointers = new HashMap<>();
    private static  final int[] COLORS = {Color.BLUE,Color.YELLOW, Color.CYAN, Color.GREEN, Color.MAGENTA,Color.RED, Color.GRAY};
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
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);
        int actionMasked = event.getActionMasked();

        switch (actionMasked){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                PointF point = new PointF();
                float x = event.getX();
                float y = event.getY();
                point.set(x,y);
                mActivePointers.put(pointerId,point);
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
            if(pointF!=null){
                mPaint.setColor(COLORS[key%7]);
                canvas.drawCircle(pointF.x,pointF.y,RADIUS,mPaint);
            }
        }

    }
}