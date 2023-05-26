package penakelex.bicycleCharts.grafics.Charts;

public class Expression {
    private final String function;
    private String variable;

    public Expression(String function) {
        this.function = function.replace(" ", "").replace("\n", "");
    }

    public double getValue(double variableValue) {
        Calculator calculator = new Calculator(getExpression(variableValue));
        return calculator.calculate();
    }

    private String getExpression(double x) {
        StringBuilder expression = new StringBuilder();
        for (int i = 0; i < function.length(); i++) {
            if (variable == null) {
                int ID = (int) function.charAt(i);
                if (((ID >= 65 && ID <= 90) || (ID >= 97 && ID <= 122))) {
                    expression.append(x);
                    this.variable = String.valueOf(function.charAt(i));
                    continue;
                }
            } else {
                if (variable.equals(String.valueOf(function.charAt(i)))) {
                    expression.append(x);
                    continue;
                }
            }
            expression.append(function.charAt(i));
        }
        return expression.toString();
    }
}