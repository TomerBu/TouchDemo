package tomerbu.edu.touchdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new HatView(this, null));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println(String.format(Locale.ENGLISH, "X: %f", event.getX()));
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                System.out.println("Down");
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("Moved");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("Up");
                break;
        }
        return true;
    }
}
