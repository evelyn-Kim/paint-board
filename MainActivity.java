package com.example.paintboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final static int LINE = 1, RECT = 2, CIRCLE = 3;
    static int kindofshape = LINE;

    private static class MyGraphicView extends View {
        int startx, starty, endx, endy;
        public MyGraphicView(Context mycontext) {
            super(mycontext);
        }
        @Override
        public boolean onTouchEvent(MotionEvent myevent) {
            switch (myevent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startx = (int) myevent.getX();
                    starty = (int) myevent.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    endx = (int) myevent.getX();
                    endy = (int) myevent.getY();
                    this.invalidate();
                    break;
            }
            return true;
        }
        protected void onDraw(Canvas mycanvas) {
            super.onDraw(mycanvas);
            Paint mypaint = new Paint();
            mypaint.setAntiAlias(true);
            mypaint.setStrokeWidth(8);
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setColor(Color.BLUE);

            switch (kindofshape) {
                case LINE:
                    mycanvas.drawLine(startx, starty, endx, endy, mypaint);
                    break;
                case RECT:
                    mycanvas.drawRect(startx, starty, endx, endy, mypaint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow((endx-startx), 2.0)+Math.pow((endy-starty), 2.0));
                    mycanvas.drawCircle(startx, starty, radius, mypaint);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    public boolean onCreateOptionsMenu(Menu mymenu) {
        super.onCreateOptionsMenu(mymenu);
        mymenu.add(0,1,0,"line");
        mymenu.add(0,2,0,"RECT");
        mymenu.add(0,3,0,"CIRCLE");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem myitem) {
        switch (myitem.getItemId()){
            case 1:
                kindofshape = LINE;
                return true;
            case 2:
                kindofshape = RECT;
                return true;
            case 3:
                kindofshape = CIRCLE;
                return true;
        }
        return super.onOptionsItemSelected(myitem);
    }

}