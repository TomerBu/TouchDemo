package tomerbu.edu.touchdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class AndroView extends View {
    Bitmap headBitmap, bodyBitmap, hatBitmap;
    boolean isMoveHat=true, isMoveBody=true, isMoveHead=true;
    float xPosHead = 100 ,yPosHead = 0;
    float xPosBody = 100 ,yPosBody = 400;
    float xPosHat = 100 ,yPosHat = 1100;

    public AndroView(Context context) {
        this(context,null,0);
    }

    public AndroView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public AndroView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        headBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.head);

        bodyBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.body);
        hatBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.hat);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isMoveHead) {
            //Body
            canvas.drawBitmap(bodyBitmap,xPosBody,yPosBody,null);
            //Hat
            canvas.drawBitmap(hatBitmap, xPosHat, yPosHat, null);
            //Head
            canvas.drawBitmap(headBitmap, xPosHead, yPosHead, null);
        }
        else if(isMoveBody){
            //Head
            canvas.drawBitmap(headBitmap, xPosHead, yPosHead, null);
            //Hat
            canvas.drawBitmap(hatBitmap, xPosHat, yPosHat, null);
            //Body
            canvas.drawBitmap(bodyBitmap,xPosBody,yPosBody,null);
        }
        else if(isMoveHat){
            //Head
            canvas.drawBitmap(headBitmap, xPosHead, yPosHead, null);
            //Body
            canvas.drawBitmap(bodyBitmap,xPosBody,yPosBody,null);
            //Hat
            canvas.drawBitmap(hatBitmap, xPosHat, yPosHat, null);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                System.out.println("Down");
                testColission(event);
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("Move");
                testColission(event);
                if(isMoveHat)
                {
                    xPosHat = event.getX() - hatBitmap.getWidth()/2;
                    yPosHat = event.getY()- hatBitmap.getHeight()/2;
                }
                else if(isMoveBody){
                    xPosBody = event.getX()- bodyBitmap.getWidth()/2;;
                    yPosBody = event.getY()- bodyBitmap.getHeight()/2;;
                }
                else if(isMoveHead){
                    xPosHead = event.getX() - headBitmap.getWidth()/2;;
                    yPosHead = event.getY()- hatBitmap.getHeight()/2;
                }

                //redraw the view!
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isMoveHat =false;
                isMoveBody = false;
                isMoveHead = false;
                break;

        }
        return true;
    }

    private void testColission(MotionEvent event) {
        System.out.println(event.getX() + " , " +  event.getY());
        System.out.println("initial position " + xPosHead + " , " +yPosHead + "\n Width " + headBitmap.getWidth() + "\n" +" Height: "+ headBitmap.getHeight());
        if(event.getX() <= xPosHead + headBitmap.getWidth() && event.getX()>= xPosHead
                &&event.getY()<=yPosHead + headBitmap.getHeight() && event.getY()>=yPosHead){
            isMoveHead = true;

        }
        else if(event.getX() <= xPosBody + bodyBitmap.getWidth() && event.getX()>= xPosBody
                &&event.getY()<=yPosBody + bodyBitmap.getHeight() && event.getY()>=yPosBody){
            isMoveBody = true;
        }

        else if(event.getX() <= xPosHat + hatBitmap.getWidth() && event.getX()>= xPosHat
                &&event.getY()<=yPosHat + hatBitmap.getHeight() && event.getY()>=yPosHat){
            isMoveHat = true;
        }
    }


    private void collisionTest(MotionEvent event) {
        if(event.getX() <= xPosHead + headBitmap.getWidth() && event.getX()>= xPosHead
                &&event.getY()<=yPosHead + headBitmap.getHeight() && event.getY()>=yPosHead){
            isMoveHead = true;

        }
        else if(event.getX() <= xPosBody + bodyBitmap.getWidth() && event.getX()>= xPosBody
                &&event.getY()<=yPosBody + bodyBitmap.getHeight() && event.getY()>=yPosBody){
            isMoveBody = true;
        }

        else if(event.getX() <= xPosHat + hatBitmap.getWidth() && event.getX()>= xPosHat
                &&event.getY()<=yPosHat + hatBitmap.getHeight() && event.getY()>=yPosHat){
            isMoveHat = true;
        }
    }
}
