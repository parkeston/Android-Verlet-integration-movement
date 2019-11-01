package com.example.lab3_verlet.verlet;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.lab3_verlet.engine.IGameObject;
import com.example.lab3_verlet.settings.VisualSettings;

public class VerletStick implements IGameObject
{
    private VerletPoint p0,p1;
    private  double length;
    private Paint paint;
    private float dx,dy;

    public VerletStick(VerletPoint p0, VerletPoint p1)
    {
        this.p0 = p0;
        this.p1 = p1;
        length = getLength();

        paint = new Paint();
        paint.setColor(VisualSettings.STICKS_COLOR);
        paint.setStrokeWidth(VisualSettings.STICKS_WIDTH);
    }

    private double getLength()
    {
         dx = p1.getPosition().x - p0.getPosition().x;
         dy = p1.getPosition().y-p0.getPosition().y;

        return Math.sqrt(dx*dx+dy*dy);
    }

    @Override
    public void render(Canvas canvas)
    {
        if(VisualSettings.STICKS_VISIBILE)
            canvas.drawLine(p0.getPosition().x,p0.getPosition().y,p1.getPosition().x,p1.getPosition().y,paint);
    }

    @Override
    public void update()
    {
        double distance = getLength();
        double difference = length - distance;
        double percent = difference/distance/2; //percent of distance to move each point

        double offsetX = dx*percent;
        double offsetY = dy*percent;

        p0.getPosition().x-=offsetX;
        p0.getPosition().y-=offsetY;

        p1.getPosition().x+=offsetX;
        p1.getPosition().y+=offsetY;
    }

    @Override
    public PointF getPosition() {
        return null;
    }
}
