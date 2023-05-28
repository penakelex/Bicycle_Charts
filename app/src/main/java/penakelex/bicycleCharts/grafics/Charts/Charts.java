package penakelex.bicycleCharts.grafics.Charts;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Charts extends SurfaceView implements SurfaceHolder.Callback {
    private ChartsThread chartsThread;

    public Charts(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("-x^2+3*x+7");
        arrayList.add("2*x+1");
        chartsThread = new ChartsThread(arrayList, 2, getHolder());
        chartsThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                chartsThread.join();
                retry = false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
