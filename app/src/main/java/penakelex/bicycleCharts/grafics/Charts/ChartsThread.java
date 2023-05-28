package penakelex.bicycleCharts.grafics.Charts;

import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import penakelex.bicycleCharts.grafics.Database.FunctionsTable.FunctionsEntity;
import penakelex.bicycleCharts.grafics.ViewModel.Functions.FunctionsViewModel;

public class ChartsThread extends Thread {
    private final SurfaceHolder surfaceHolder;
    private final Paint paint = new Paint();
    private final double plusStep;
    private final ArrayList<String> functions;
    private final Area listCoordinates = new Area();
    private final FunctionsViewModel viewModel;

    public ChartsThread(ArrayList<String> functions, double plusStep, SurfaceHolder surfaceHolder, ViewModelStoreOwner owner, Application application) {
        this.surfaceHolder = surfaceHolder;
        this.plusStep = plusStep;
        this.functions = functions;
        this.viewModel = new ViewModelProvider(owner).get(FunctionsViewModel.class);
        viewModel.initiate(application);
    }

    @Override
    public void run() {
        viewModel.deleteOldVersion(functions);
        Canvas canvas = surfaceHolder.lockCanvas();
        setWhiteBackground(canvas);
        makeAxes(canvas);
        makeGrid(canvas);
        makeSingleCuts(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
        canvas = surfaceHolder.lockCanvas();
        startCharts(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
        updateAreasValues();
    }

    private void updateAreasValues() {
        String areas = String.format("%s ", listCoordinates.getLeftRectanglesRuleArea(functions)) +
                String.format("%s ", listCoordinates.getMiddleRectanglesRuleArea(functions)) +
                String.format("%s ", listCoordinates.getRightRectanglesRuleArea(functions)) +
                String.format("%s", listCoordinates.getTrapezoidRuleArea(functions));
        viewModel.add(new FunctionsEntity(functions, areas));
    }

    private void startCharts(Canvas canvas) {
        setWhiteBackground(canvas);
        makeAxes(canvas);
        makeGrid(canvas);
        makeSingleCuts(canvas);
        makeGraphs(canvas);
        makeHatching(canvas);
    }

    private void makeHatching(Canvas canvas) {
        ArrayList<Double> xArea = listCoordinates.getXArea(canvas);
        float halfWidth = (float) canvas.getWidth() / 2, halfHeight = (float) canvas.getHeight() / 2;
        Expression firstExpression = new Expression(functions.get(0)), secondExpression = new Expression(functions.get(1));
        ColorRGB colorRGB = new ColorRGB();
        paint.setColor(Color.rgb(colorRGB.getR(), colorRGB.getG(), colorRGB.getB()));
        for (double x : xArea) {
            canvas.drawLine((float) (halfWidth + x * 30 * ((double) 1 / plusStep)), (float) (-firstExpression.getValue(x) * 30 * ((double) 1 / plusStep) + halfHeight),
                    (float) (halfWidth + x * 30 * ((double) 1 / plusStep)), (float) (-secondExpression.getValue(x) * 30 * ((double) 1 / plusStep) + halfHeight), paint);
        }
    }

    private void makeGraphs(Canvas canvas) {
        for (String function : functions) {
            drawGraph(function, canvas);
        }
        nameAxes(canvas);
    }

    private void nameAxes(Canvas canvas) {
        paint.setColor(Color.BLUE);
        canvas.drawText("x", canvas.getWidth() - 10, (float) canvas.getHeight() / 2 + 20, paint);
        canvas.drawText("y", (float) canvas.getWidth() / 2 - 20, 10, paint);
    }

    private void makeGrid(Canvas canvas) {
        verticalLines(canvas);
        horizontalLines(canvas);
    }

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

    private void makeSingleCuts(Canvas canvas) {
        drawOrigin(canvas);
        rightX(canvas);
        leftX(canvas);
        topY(canvas);
        bottomY(canvas);
    }

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

    private void drawOrigin(Canvas canvas) {
        paint.setColor(Color.BLUE);
        canvas.drawText("0", (float) canvas.getWidth() / 2 - 12, (float) canvas.getHeight() / 2 + 15, paint);
    }

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

    private void setWhiteBackground(Canvas canvas) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    }

    private void makeAxes(Canvas canvas) {
        paint.setColor(Color.GREEN);
        float width = canvas.getWidth(), height = canvas.getHeight();
        canvas.drawLine(width / 2, 0, width / 2, height, paint);
        canvas.drawLine(0, height / 2, width, height / 2, paint);
        canvas.drawLine(width, height / 2, width - 24, height / 2 - 12, paint);
        canvas.drawLine(width, height / 2, width - 24, height / 2 + 12, paint);
        canvas.drawLine(width / 2, 0, width / 2 - 12, 24, paint);
        canvas.drawLine(width / 2, 0, width / 2 + 12, 24, paint);
    }

    private void drawGraph(String function, Canvas canvas) {
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        Expression expression = new Expression(function);
        ColorRGB colorRGB = new ColorRGB();
        paint.setColor(Color.rgb(colorRGB.getR(), colorRGB.getG(), colorRGB.getB()));
        int halfWidth = canvas.getWidth() / 2;
        for (double i = 0; i < canvas.getWidth(); i += 0.1) {
            coordinates.add(new Coordinates((float) i, (float) (-expression.getValue(i - halfWidth) * 30 * ((double) 1 / plusStep) + canvas.getHeight() / 2)));
        }
        listCoordinates.addCoordinates(coordinates);
        for (int i = 0; i < coordinates.size(); i++) {
            try {
                canvas.drawLine((float) (canvas.getWidth() / 2 + (coordinates.get(i).getX() - halfWidth) * 30 * ((double) 1 / plusStep)), coordinates.get(i).getY(), (float) (canvas.getWidth() / 2 + (coordinates.get(i + 1).getX() - halfWidth) * 30 * ((double) 1 / plusStep)), coordinates.get(i + 1).getY(), paint);
            } catch (Exception exception) {
                break;
            }
        }
    }
}

class Area {
    private final ArrayList<ArrayList<Coordinates>> listCoordinates;
    private ArrayList<Double> xArea;

    public Area() {
        this.listCoordinates = new ArrayList<>();
    }

    public void addCoordinates(ArrayList<Coordinates> coordinates) {
        listCoordinates.add(coordinates);
    }

    public ArrayList<Double> getXArea(Canvas canvas) {
        ArrayList<Coordinates> firstCoordinates = listCoordinates.get(0), secondCoordinates = listCoordinates.get(1);
        xArea = new ArrayList<>();
        int start = -1, end = -1;
        for (int i = 0; i < firstCoordinates.size(); i++) {
            if (Math.abs(firstCoordinates.get(i).getY() - secondCoordinates.get(i).getY()) <= 0.001) {
                if (start == -1) start = i;
                else {
                    end = i;
                    break;
                }
            }
            if (start != -1) {
                xArea.add((double) (firstCoordinates.get(i).getX() - canvas.getWidth() / 2));
            }
        }
        return (ArrayList<Double>) xArea.clone();
    }

    public double getLeftRectanglesRuleArea(ArrayList<String> functions) {
        double area = 0, h = 0.00005, start = xArea.get(0), end = xArea.get(xArea.size() - 1);
        Expression firstExpression = new Expression(functions.get(0)), secondExpression = new Expression(functions.get(1));
        for (double x = start; x < end; x += h) {
            area += firstExpression.getValue(x) - secondExpression.getValue(x);
        }
        return Math.abs(area) * h;
    }

    public double getRightRectanglesRuleArea(ArrayList<String> functions) {
        double area = 0, h = 0.0001, start = xArea.get(0), end = xArea.get(xArea.size() - 1);
        Expression firstExpression = new Expression(functions.get(0)), secondExpression = new Expression(functions.get(1));
        for (double x = start; x < end; x += h) {
            area += firstExpression.getValue(x + h) - secondExpression.getValue(x + h);
        }
        return Math.abs(area) * h;
    }

    public double getMiddleRectanglesRuleArea(ArrayList<String> functions) {
        double area = 0, h = 0.00005, start = xArea.get(0), end = xArea.get(xArea.size() - 1);
        Expression firstExpression = new Expression(functions.get(0)), secondExpression = new Expression(functions.get(1));
        for (double x = start; x < end; x += h) {
            area += firstExpression.getValue(x + h / 2) - secondExpression.getValue(x + h / 2);
        }
        return Math.abs(area) * h;
    }

    public double getTrapezoidRuleArea(ArrayList<String> functions) {
        Expression firstExpression = new Expression(functions.get(0)), secondExpression = new Expression(functions.get(1));
        double area, h = 0.00005, start = xArea.get(0), end = xArea.get(xArea.size() - 1);
        area = (firstExpression.getValue(start) - secondExpression.getValue(start) + firstExpression.getValue(end) + secondExpression.getValue(end)) / 2.;
        for (double x = start + h; x < end; x += h) {
            area += firstExpression.getValue(x) - secondExpression.getValue(x);
        }
        return Math.abs(area) * h;
    }

    public double getMonteCarloRuleArea(ArrayList<String> functions) {
        //TODO: Сделать этот метод, но уже после вывода остальной информации на экран.
        return 0.;
    }
}

class Coordinates {
    private final float x, y;

    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

class ColorRGB {
    private final int R, G, B;

    public ColorRGB() {
        R = (int) (Math.random() * 255);
        G = (int) (Math.random() * 255);
        B = (int) (Math.random() * 255);
    }

    public int getR() {
        return R;
    }

    public int getG() {
        return G;
    }

    public int getB() {
        return B;
    }
}
