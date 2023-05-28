package penakelex.bicycleCharts.grafics.Charts;

import android.app.Application;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;

public class Charts extends SurfaceView implements SurfaceHolder.Callback {
    private ChartsThread chartsThread;
    private final ViewModelStoreOwner owner;
    private final Application application;
    private final int step;
    private final ArrayList<String> arrayList = new ArrayList<>();

    public Charts(Context context, ViewModelStoreOwner owner, Application application, String firstFunction, String secondFunction, int step) {
        super(context);
        this.owner = owner;
        this.application = application;
        this.step = step;
        arrayList.add(firstFunction);
        arrayList.add(secondFunction);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        chartsThread = new ChartsThread(arrayList, step, getHolder(), owner, application);
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
