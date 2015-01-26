package uj.edu.android;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shybovycha on 25.01.15.
 */
public class Hero {
    protected Bitmap image;
    protected Paint paint;
    protected Integer x, y;
    protected Integer targetX, targetY;
    protected Integer speed;

    public Hero(Context context) {
        loadImage(context);

        this.speed = 4;
    }

    public void setTarget(int newX, int newY) {
        this.targetX = newX - (this.image.getWidth() / 2);
        this.targetY = newY - (this.image.getHeight() / 2);
    }

    public void draw(Canvas canvas) {
        if (this.x == null) {
            this.x = (canvas.getWidth() / 2) - (this.image.getWidth() / 2);
            this.y = (canvas.getHeight() / 2) - (this.image.getHeight() / 2);

            this.targetX = this.x;
            this.targetY = this.y;
        }

        if (!isTargetReached()) {
            int dirX = this.targetX - this.x;
            int dirY = this.targetY - this.y;

            double len = Math.sqrt(Math.pow(dirX, 2) + Math.pow(dirY, 2));
            double k = len / this.speed;

            int dx = (int) Math.ceil(dirX / k);
            int dy = (int) Math.ceil(dirY / k);

            this.x += dx;
            this.y += dy;
        }

        canvas.drawBitmap(this.image, this.x,  this.y, this.paint);
    }

    public boolean isTargetReached() {
        return Math.sqrt(Math.pow((this.targetX - this.x), 2) + Math.pow((this.targetY - this.y), 2)) < Math.pow(10.0, 1.0);
    }

    protected void loadImage(Context context) {
        AssetManager assetManager = context.getAssets();

        InputStream istream;
        Bitmap bitmap = null;

        try {
            istream = assetManager.open("nyan.png");
            bitmap = BitmapFactory.decodeStream(istream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.image = bitmap;

        this.paint = new Paint(Paint.DITHER_FLAG | Paint.ANTI_ALIAS_FLAG);
    }
}
