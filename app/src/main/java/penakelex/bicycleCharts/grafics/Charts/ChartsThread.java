package penakelex.bicycleCharts.grafics.Charts;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.ArrayList;

public class ChartsThread extends Thread {
    private final SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    private final Paint paint = new Paint();
    private float x, y;
    //private final ArrayList<String> functions;

    public ChartsThread(
            //ArrayList<String> functions,
            SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        //this.functions = functions;
    }

    public void requestStop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    startCharts(canvas);
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    private void startCharts(Canvas canvas) {
        makeAxes(canvas);

    }

    private void makeAxes(Canvas canvas) {
        paint.setColor(Color.GREEN);
        canvas.drawLine((float) canvas.getWidth() / 2, 0, (float) canvas.getWidth() / 2, canvas.getHeight(), paint);
        canvas.drawLine(0, (float) canvas.getHeight() / 2, canvas.getWidth(), (float) canvas.getHeight() / 2, paint);
    }

    private void drawGraph(String function, Canvas canvas) {

    }
}
