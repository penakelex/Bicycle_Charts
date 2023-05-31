package penakelex.bicycleCharts.grafics.Charts;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;
/** Charts
 *    Собственный SurfaceView
 * */
@SuppressLint("ViewConstructor")
public class Charts extends SurfaceView implements SurfaceHolder.Callback {
    private ChartsThread chartsThread; //Поток для графиков
    private final ViewModelStoreOwner owner; //Владелец для создания FunctionsViewModel
    private final Application application; //Экземляр класса Application для инициализации FunctionsViewModel
    private final double step; //Единичный отрезок
    private final ArrayList<String> arrayList = new ArrayList<>(); //Список с введёнными функциями

    /** Charts
     *    Конструктор класса Charts
     *  Вход:
     *      Context context - контекст приложения,
     *      ViewModelStoreOwner owner - владелец (активность),
     *      Application application - экземляр класса Application,
     *      String firstFunction - первая введенная функция,
     *      String secondFunction - вторая введенная функция,
     *      double step - единичный отрезок
     */
    public Charts(Context context, ViewModelStoreOwner owner, Application application, String firstFunction, String secondFunction, double step) {
        super(context);
        this.owner = owner;
        this.application = application;
        this.step = step;
        arrayList.add(firstFunction);
        arrayList.add(secondFunction);
        getHolder().addCallback(this);
    }

    /** surfaceCreated - процедура
     *      Запуск потока для графиков
     *  Вход:
     *      SurfaceHolder holder
     * */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        chartsThread = new ChartsThread(arrayList, step, getHolder(), owner, application);
        chartsThread.start();
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }

    /** surfaceDestroyed - процедура
     *      Ожидание завершения потока с графиками
     *  Вход:
     *      SurfaceHolder holder
     * */
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
