package penakelex.bicycleCharts.grafics.Charts;

import android.app.Application;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;

import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;
import penakelex.bicycleCharts.grafics.ViewModel.Functions.FunctionsViewModel;
/** ChartsThread
 *      Поток для графиков
 * */
public class ChartsThread extends Thread {
    private final SurfaceHolder surfaceHolder; //Управление поверхностью
    private final Paint paint = new Paint(); //Перо
    private final double plusStep; //Единичный отрезок
    private final ArrayList<String> functions; //Список с функциями
    private final Area listCoordinates = new Area(); //Экземпляр класса Area для поиска площади
    private final FunctionsViewModel viewModel; //ViewModel для функций

    /**
     * ChartsThread - процедура
     *      Конструктор класса ChartsThread, в котором инициализируется viewModel
     * Вход:
     *      ArrayList<String> functions - список с функциями,
     *      double plusStep - единичный шаг,
     *      SurfaceHolder surfaceHolder - управление поверхностью,
     *      ViewModelStoreOwner owner - владелец для ViewModel,
     *      Application application - экземпляр класса Application
     */
    public ChartsThread(ArrayList<String> functions, double plusStep, SurfaceHolder surfaceHolder, ViewModelStoreOwner owner, Application application) {
        this.surfaceHolder = surfaceHolder;
        this.plusStep = plusStep;
        this.functions = functions;
        this.viewModel = new ViewModelProvider(owner).get(FunctionsViewModel.class);
        viewModel.initiate(application);
    }

    /**
     * run - процедура
     *      Запуск потока
     */
    @Override
    public void run() {
        //Удаление предыдущих функций
        viewModel.deleteAll();
        //Создание canvas
        Canvas canvas = surfaceHolder.lockCanvas();
        //Стартовая координатная плоскость
        startingCoordinatePlane(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
        //Создание графиков функций и вычисление площади
        startCharts(canvas);
        updateAreasValues();
    }

    /** updateAreasValues - процедура
     *      Добавление в базу данных функций и площадей
     * */
    private void updateAreasValues() {
        viewModel.add(new FunctionsEntity(functions, String.format("%s ", listCoordinates.getLeftRectanglesRuleArea(functions)) +
                String.format("%s ", listCoordinates.getMiddleRectanglesRuleArea(functions)) +
                String.format("%s ", listCoordinates.getRightRectanglesRuleArea(functions)) +
                String.format("%s", listCoordinates.getTrapezoidRuleArea(functions))));
    }

    /** startingCoordinatePlane - процедура
     *      Создание начальной координатной плоскости
     * */
    private void startingCoordinatePlane(Canvas canvas) {
        setWhiteBackground(canvas);
        makeAxes(canvas);
        makeGrid(canvas);
        makeSingleCuts(canvas);
    }

    /** startCharts - процедура
     *      Отрисовка графиков и штриховка области их пересечения
     *  Вход:
     *      Canvas canvas
     * */
    private void startCharts(Canvas canvas) {
        canvas = surfaceHolder.lockCanvas();
        startingCoordinatePlane(canvas);
        makeGraphs(canvas);
        makeHatching(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    /** makeHatching - процедура
     *      Штриховка области пересечения функций
     *  Вход:
     *      Canvas canvas
     * */
    private void makeHatching(Canvas canvas) {
        //Получение области по оси абцисс
        ArrayList<Double> xArea = listCoordinates.getXArea(canvas);
        float halfWidth = (float) canvas.getWidth() / 2, halfHeight = (float) canvas.getHeight() / 2;
        Expression firstExpression = new Expression(functions.get(0)), secondExpression = new Expression(functions.get(1));
        ColorRGB colorRGB = new ColorRGB();
        paint.setColor(Color.rgb(colorRGB.getR(), colorRGB.getG(), colorRGB.getB()));
        //Штриховка области
        for (double x : xArea) {
            canvas.drawLine((float) (halfWidth + x * 30 * ((double) 1 / plusStep)), (float) (-firstExpression.getValue(x) * 30 * ((double) 1 / plusStep) + halfHeight),
                    (float) (halfWidth + x * 30 * ((double) 1 / plusStep)), (float) (-secondExpression.getValue(x) * 30 * ((double) 1 / plusStep) + halfHeight), paint);
        }
    }

    /** makeGraphs - процедура
     *      Последовательная отрисовка графиков функций и название осей
     *  Вход:
     *      Canvas canvas
     * */
    private void makeGraphs(Canvas canvas) {
        //Последовательная отрисовка графиков функций
        for (String function : functions) {
            drawGraph(function, canvas);
        }
        //Название осей
        nameAxes(canvas);
    }

    /** nameAxes - процедура
     *      Название осей
     *  Вход:
     *      Canvas canvas
     * */
    private void nameAxes(Canvas canvas) {
        paint.setColor(Color.BLUE);
        canvas.drawText("x", canvas.getWidth() - 10, (float) canvas.getHeight() / 2 + 20, paint);
        canvas.drawText("y", (float) canvas.getWidth() / 2 - 20, 10, paint);
    }

    /** makeGrid - процедура
     *      Создание сетки для удобства ориентировки по графику
     *  Вход:
     *      Canvas canvas
     * */
    private void makeGrid(Canvas canvas) {
        verticalLines(canvas);
        horizontalLines(canvas);
    }

    /** horizontalLines - процедура
     *      Отрисовка горизонтальных линий сетки
     *  Вход:
     *      Canvas canvas
     * */
    private void horizontalLines(Canvas canvas) {
        float topY = (float) canvas.getHeight() / 2 - 30, bottomY = (float) canvas.getHeight() / 2 + 30, width = canvas.getWidth();
        paint.setColor(Color.rgb(181, 255, 115));
        while (topY > 30) {
            canvas.drawLine(0, topY, width - 24, topY, paint);
            topY -= 30;
        }
        while (bottomY < canvas.getHeight()) {
            canvas.drawLine(0, bottomY, width - 24, bottomY, paint);
            bottomY += 30;
        }
    }

    /** verticalLines - процедура
     *      Отрисовка вертикальных линий сетки
     *  Вход:
     *      Canvas canvas
     * */
    private void verticalLines(Canvas canvas) {
        float rightX = (float) canvas.getWidth() / 2 + 30, leftX = (float) canvas.getWidth() / 2 - 30, height = canvas.getHeight();
        paint.setColor(Color.rgb(181, 255, 115));
        while (rightX < canvas.getWidth() - 24) {
            canvas.drawLine(rightX, 24, rightX, height, paint);
            rightX += 30;
        }
        while (leftX >= 0) {
            canvas.drawLine(leftX, 24, leftX, height, paint);
            leftX -= 30;
        }
    }

    /** makeSingleCuts - процедура
     *      Отрисовка единичных отрезков
     *  Вход:
     *      Canvas canvas
     * */
    private void makeSingleCuts(Canvas canvas) {
        drawOrigin(canvas);
        rightX(canvas);
        leftX(canvas);
        topY(canvas);
        bottomY(canvas);
    }

    /** bottomY - процедура
     *      Отрисовка единичных отрезков на отрицательной области оси ординат
     *  Вход:
     *      Canvas canvas
     * */
    private void bottomY(Canvas canvas) {
        float x = (float) canvas.getWidth() / 2, y = (float) canvas.getHeight() / 2 + 30;
        double step = -plusStep;
        while (y < canvas.getHeight()) {
            paint.setColor(Color.GREEN);
            canvas.drawLine(x - 10, y, x + 10, y, paint);
            paint.setColor(Color.BLUE);
            canvas.drawText(String.valueOf(step), x - 30, y, paint);
            step -= plusStep;
            y += 30;
        }
    }

    /** drawOrigin - процедура
     *      Подпись нуля в начале координат
     *  Вход:
     *      Canvas canvas
     * */
    private void drawOrigin(Canvas canvas) {
        paint.setColor(Color.BLUE);
        canvas.drawText("0", (float) canvas.getWidth() / 2 - 12, (float) canvas.getHeight() / 2 + 15, paint);
    }

    /** topY - процедура
     *      Отрисовка единичных отрезков на положительной области оси ординат
     *  Вход:
     *      Canvas canvas
     * */
    private void topY(Canvas canvas) {
        float x = (float) canvas.getWidth() / 2, y = (float) canvas.getHeight() / 2 - 30;
        double step = plusStep;
        while (y > 30) {
            paint.setColor(Color.GREEN);
            canvas.drawLine(x - 10, y, x + 10, y, paint);
            paint.setColor(Color.BLUE);
            canvas.drawText(String.valueOf(step), x - 30, y, paint);
            step += plusStep;
            y -= 30;
        }
    }

    /** leftX - процедура
     *      Отрисовка единичных отрезков на отрицательной области оси абцисс
     *  Вход:
     *      Canvas canvas
     * */
    private void leftX(Canvas canvas) {
        float x = (float) canvas.getWidth() / 2 - 30, y = (float) canvas.getHeight() / 2;
        double step = -plusStep;
        while (x >= 0) {
            paint.setColor(Color.GREEN);
            canvas.drawLine(x, y + 10, x, y - 10, paint);
            paint.setColor(Color.BLUE);
            canvas.drawText(String.valueOf(step), x, y + 30, paint);
            x -= 30;
            step -= plusStep;
        }
    }

    /** rightX - процедура
     *      Отрисовка единичных отрезков на положительной области оси абцисс
     *  Вход:
     *      Canvas canvas
     * */
    private void rightX(Canvas canvas) {
        float x = (float) canvas.getWidth() / 2 + 30, y = (float) canvas.getHeight() / 2;
        double step = plusStep;
        while (x < canvas.getWidth() - 24) {
            paint.setColor(Color.GREEN);
            canvas.drawLine(x, y + 10, x, y - 10, paint);
            paint.setColor(Color.BLUE);
            canvas.drawText(String.valueOf(step), x, y + 30, paint);
            x += 30;
            step += plusStep;
        }
    }

    /** setWhiteBackground - процедура
     *      Отрисовка белого фона
     *  Вход:
     *      Canvas canvas
     * */
    private void setWhiteBackground(Canvas canvas) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    }

    /** makeAxes - процедура
     *      Отрисовка осей
     *  Вход:
     *      Canvas canvas
     * */
    private void makeAxes(Canvas canvas) {
        paint.setColor(Color.GREEN);
        float width = canvas.getWidth(), height = canvas.getHeight();
        //Отрисовка осей
        canvas.drawLine(width / 2, 0, width / 2, height, paint);
        canvas.drawLine(0, height / 2, width, height / 2, paint);
        //Отрисовка направления осей
        //По оси абцисс
        canvas.drawLine(width, height / 2, width - 24, height / 2 - 12, paint);
        canvas.drawLine(width, height / 2, width - 24, height / 2 + 12, paint);
        //По оси ординат
        canvas.drawLine(width / 2, 0, width / 2 - 12, 24, paint);
        canvas.drawLine(width / 2, 0, width / 2 + 12, 24, paint);
    }

    /** drawGraph - процедура
     *      Отрисовка графика функции
     *  Вход:
     *      String function - функция,
     *      Canvas canvas
     * */
    private void drawGraph(String function, Canvas canvas) {
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        Expression expression = new Expression(function);
        ColorRGB colorRGB = new ColorRGB();
        paint.setColor(Color.rgb(colorRGB.getR(), colorRGB.getG(), colorRGB.getB()));
        int halfWidth = canvas.getWidth() / 2;
        //Запись координат
        for (double i = 0; i < canvas.getWidth(); i += 0.1) {
            coordinates.add(new Coordinates((float) i, (float) (-expression.getValue(i - halfWidth) * 30 * ((double) 1 / plusStep) + canvas.getHeight() / 2)));
        }
        listCoordinates.addCoordinates(coordinates);
        //Отрисовка графика функции по координатам
        for (int i = 0; i < coordinates.size(); i++) {
            try {
                canvas.drawLine((float) (canvas.getWidth() / 2 + (coordinates.get(i).getX() - halfWidth) * 30 * ((double) 1 / plusStep)), coordinates.get(i).getY(), (float) (canvas.getWidth() / 2 + (coordinates.get(i + 1).getX() - halfWidth) * 30 * ((double) 1 / plusStep)), coordinates.get(i + 1).getY(), paint);
            } catch (Exception exception) {
                break;
            }
        }
    }
}
/** Area
 *      Класс для поиска площади
 * */
class Area {
    private final ArrayList<ArrayList<Coordinates>> listCoordinates; //Список со списками с координатами точек графиков функций
    private ArrayList<Double> xArea; //Список с областью пересечения графиков функций по оси абцисс

    /** Area
     *      Конструктор класса Area
     * */
    public Area() {
        this.listCoordinates = new ArrayList<>();
    }

    /** addCoordinates - процедура
     *      Добавление списка координат точек графика функции
     *  Вход:
     *      ArrayList<Coordinates> coordinates - список с координатами точек графика функций
     * */
    public void addCoordinates(ArrayList<Coordinates> coordinates) {
        listCoordinates.add(coordinates);
    }

    /** getXArea - функция
     *      Получение области перечения графиков функций по оси абцисс
     *  Вход:
     *      Canvas canvas
     *  Выход:
     *      ArrayList<Double> xArea - область пересечения графиков функций по оси абцисс
     * */
    public ArrayList<Double> getXArea(Canvas canvas) {
        ArrayList<Coordinates> firstCoordinates = listCoordinates.get(0), secondCoordinates = listCoordinates.get(1);
        xArea = new ArrayList<>();
        //Базовая инициализация левой точки пересечения
        int start = -1;
        for (int i = 0; i < firstCoordinates.size(); i++) {
            if (Math.abs(firstCoordinates.get(i).getY() - secondCoordinates.get(i).getY()) <= 0.001) {
                if (start == -1) start = i;
                else {
                    break;
                }
            }
            //Добавление координат в список, если первая точка пересечения достигнута
            if (start != -1) {
                xArea.add((double) (firstCoordinates.get(i).getX() - canvas.getWidth() / 2));
            }
        }
        return (ArrayList<Double>) xArea.clone();
    }

    /** getLeftRectanglesRuleArea - функция
     *      Получение площади пересечения графиков функций методом левых прямоугольников
     *  Вход:
     *      ArrayList<String> functions - список с функциями
     *  Выход:
     *      double area - площадь
     * */
    public double getLeftRectanglesRuleArea(ArrayList<String> functions) {
        double area = 0, h = 0.00005, start = xArea.get(0), end = xArea.get(xArea.size() - 1);
        Expression firstExpression = new Expression(functions.get(0)), secondExpression = new Expression(functions.get(1));
        for (double x = start; x < end; x += h) {
            area += firstExpression.getValue(x) - secondExpression.getValue(x);
        }
        return Math.abs(area) * h;
    }

    /** getRightRectanglesRuleArea - функция
     *      Получение площади пересечения графиков функций методом правых прямоугольников
     *  Вход:
     *      ArrayList<String> functions - список с функциями
     *  Выход:
     *      double area - площадь
     * */
    public double getRightRectanglesRuleArea(ArrayList<String> functions) {
        double area = 0, h = 0.0001, start = xArea.get(0), end = xArea.get(xArea.size() - 1);
        Expression firstExpression = new Expression(functions.get(0)), secondExpression = new Expression(functions.get(1));
        for (double x = start; x < end; x += h) {
            area += firstExpression.getValue(x + h) - secondExpression.getValue(x + h);
        }
        return Math.abs(area) * h;
    }

    /** getMiddleRectanglesRuleArea - функция
     *      Получение площади пересечения графиков функций методом средних прямоугольников
     *  Вход:
     *      ArrayList<String> functions - список с функциями
     *  Выход:
     *      double area - площадь
     * */
    public double getMiddleRectanglesRuleArea(ArrayList<String> functions) {
        double area = 0, h = 0.00005, start = xArea.get(0), end = xArea.get(xArea.size() - 1);
        Expression firstExpression = new Expression(functions.get(0)), secondExpression = new Expression(functions.get(1));
        for (double x = start; x < end; x += h) {
            area += firstExpression.getValue(x + h / 2) - secondExpression.getValue(x + h / 2);
        }
        return Math.abs(area) * h;
    }

    /** getTrapezoidRuleArea - функция
     *      Получение площади пересечения графиков функций методом трапеций
     *  Вход:
     *      ArrayList<String> functions - список с функциями
     *  Выход:
     *      double area - площадь
     * */
    public double getTrapezoidRuleArea(ArrayList<String> functions) {
        Expression firstExpression = new Expression(functions.get(0)), secondExpression = new Expression(functions.get(1));
        double area, h = 0.00005, start = xArea.get(0), end = xArea.get(xArea.size() - 1);
        area = (firstExpression.getValue(start) - secondExpression.getValue(start) + firstExpression.getValue(end) + secondExpression.getValue(end)) / 2.;
        for (double x = start + h; x < end; x += h) {
            area += firstExpression.getValue(x) - secondExpression.getValue(x);
        }
        return Math.abs(area) * h;
    }
}
/** Coordinates
 *      Класс для координат
 * */
class Coordinates {
    private final float x, y; //Координаты по оси абцисс и по оси ординат соответственно

    /** Coordinates
     *      Конструктор класса Coordinates
     *  Вход:
     *      float x - координаты по оси абцисс,
     *      float y - координаты по оси ординат
     * */
    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /** getX - геттер
     *      Получение координат по оси абцисс
     *  Выход:
     *      float x
     * */
    public float getX() {
        return x;
    }

    /** getY - геттер
     *      Получение координат по оси ординат
     *  Выход:
     *      float y
     * */
    public float getY() {
        return y;
    }
}
/** ColorRGB
 *      Класс для генерации цвета по RGB
 * */
class ColorRGB {
    private final int R, G, B; //Код для красного, зелёного и синего цветов соответственно

    /** ColorRGB
     *      Конструктор класса ColorRGB, в котором генерируются коды для основных цветов
     * */
    public ColorRGB() {
        R = (int) (Math.random() * 255);
        G = (int) (Math.random() * 255);
        B = (int) (Math.random() * 255);
    }

    /** getR - геттер
     *      Получение кода красного цвета
     *  Выход:
     *      int R - код красного цвета
     * */
    public int getR() {
        return R;
    }

    /** getG - геттер
     *      Получение кода зелёного цвета
     *  Выход:
     *      int G - код зелёного цвета
     * */
    public int getG() {
        return G;
    }

    /** getB - геттер
     *      Получение кода синего цвета
     *  Выход:
     *      ing B - код синего цвета
     * */
    public int getB() {
        return B;
    }
}
