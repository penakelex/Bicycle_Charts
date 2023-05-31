package penakelex.bicycleCharts.grafics.Charts;


import penakelex.bicycleCharts.grafics.Charts.Parser.Function.Util.Point;
import penakelex.bicycleCharts.grafics.Charts.Parser.Parser;

/** Expression
 *      Класс для получения значения функции по переданному значению
 * */
public class Expression {
    private final String function; //Функция
    private String variable; //Переменная

    /** Expression
     *      Конструктор класса Expression
     *  Вход:
     *      String function - функция
     * */
    public Expression(String function) {
        this.function = function;
    }

    /** getValue - функция
     *      Получение значения для функции по переданному значению абциссы
     *  Вход:
     *      double variableValue - значение абциссы
     *  Выход:
     *      double result - значение функции
     * */
    public double getValue(double variableValue) {
        if (!getVariable().equals("")) {
            Point point = new Point(getVariable(), variableValue);
            return Parser.eval(function, point).getValue();
        } else {
            return Parser.simpleEval(function);
        }
    }

    /** getVariable - функция (геттер)
     *      Получение переменной, если она есть в функции
     *  Выход:
     *      String variable - переменная
     * */
    public String getVariable() {
        if (variable == null) {
            for (int i = 0; i < function.length(); i++) {
                int ID = (int) function.charAt(i);
                if (((ID >= 65 && ID <= 90) || (ID >= 97 && ID <= 122))) {
                    this.variable = String.valueOf(function.charAt(i));
                    break;
                }
            }
            if (variable == null) variable = "";
        }
        return this.variable;
    }
}