package penakelex.bicycleCharts.grafics.Charts;


import penakelex.bicycleCharts.grafics.Charts.Parser.Function.Util.Point;
import penakelex.bicycleCharts.grafics.Charts.Parser.Parser;

public class Expression {
    private final String function;
    private String variable;

    public Expression(String function) {
        this.function = function;
    }

    public double getValue(double variableValue) {
        Point point = new Point(getVariable(), variableValue);
        return Parser.eval(function, point).getValue();
    }

    public String getVariable() {
        if (variable == null) {
            for (int i = 0; i < function.length(); i++) {
                int ID = (int) function.charAt(i);
                if (((ID >= 65 && ID <= 90) || (ID >= 97 && ID <= 122))) {
                    this.variable = String.valueOf(function.charAt(i));
                    break;
                }
            }
            if (variable == null) {
                variable = "x";
            }
        }
        return this.variable;
    }
}