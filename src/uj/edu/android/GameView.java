package uj.edu.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shybovycha on 25.01.15.
 */
public class GameView extends View {
    protected Hero hero;
    protected long prevFrame;

    public GameView(Context context) {
        super(context);

        hero = new Hero(context);

        postInvalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        hero.draw(canvas);

        postInvalidateDelayed(1000 / 60);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hero.setTarget((int) event.getX(), (int) event.getY());
        return true;
    }
}
