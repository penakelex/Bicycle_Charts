package penakelex.bicycleCharts.grafics.Charts;

import java.math.BigDecimal;

public class Calculator {
    private String expression;
    public Calculator(String expression) {
        this.expression = expression;
    }

    public String addBigDecimal(String a, String b) {
        double a1 = Double.parseDouble(a);
        double b1 = Double.parseDouble(b);
        BigDecimal a2 = BigDecimal.valueOf(a1);
        BigDecimal b2 = BigDecimal.valueOf(b1);
        BigDecimal c2 = a2.add(b2);
        return String.valueOf(c2);
    }

    public String reduceBigDecimal(String a, String b) {
        double a1 = Double.parseDouble(a);
        double b1 = Double.parseDouble(b);
        BigDecimal a2 = BigDecimal.valueOf(a1);
        BigDecimal b2 = BigDecimal.valueOf(b1);
        BigDecimal c2 = a2.subtract(b2);
        return String.valueOf(c2);
    }

    public String multipliedString(String a, String b) {
        double a1 = Double.parseDouble(a);
        double b1 = Double.parseDouble(b);
        BigDecimal a2 = BigDecimal.valueOf(a1);
        BigDecimal b2 = BigDecimal.valueOf(b1);
        BigDecimal c2 = a2.multiply(b2);
        return String.valueOf(c2);
    }

    public String divideString(String a, String b) {
        double a1 = Double.parseDouble(a);
        double b1 = Double.parseDouble(b);
        BigDecimal a2 = BigDecimal.valueOf(a1);
        BigDecimal b2 = BigDecimal.valueOf(b1);
        BigDecimal c2 = a2.divide(b2, a2.scale());
        return String.valueOf(c2);
    }

    public String result(String s) {
        String result = "";
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*'
                    || s.charAt(i) == '/') {
                p++;
            }
        }
        String[] k = new String[2 * p + 1];
        int k1 = 0;
        int first = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*'
                    || s.charAt(i) == '/') {
                k[k1] = s.substring(first, i);
                k1++;
                k[k1] = String.valueOf(s.charAt(i));
                k1++;
                first = i + 1;
            }
        }
        k[k1] = s.substring(first);
        int kp = p;
        while (kp > 0) {
            for (int i = 0; i < k.length; i++) {
                if (k[i].equals("*") || k[i].equals("/")) {
                    int l;
                    for (l = i - 1; l > -1; l--) {
                        if (!(k[l].equals("p")))
                            break;
                    }
                    int q;
                    for (q = i + 1; q < k.length; q++) {
                        if (!(k[l].equals("p")))
                            break;
                    }
                    if (k[i].equals("*")) {
                        k[i] = multipliedString(k[l], k[q]);
                        k[l] = "p";
                        k[q] = "p";
                        kp--;
                    } else {
                        k[i] = divideString(k[l], k[q]);
                        k[l] = "p";
                        k[q] = "p";
                        kp--;
                    }
                    break;
                }
            }
            for (int i = 0; i < 2 * p + 1; i++) {
                if (k[i].equals("+") || k[i].equals("-")) {
                    int l;
                    for (l = i - 1; l > -1; l--) {
                        if (!(k[l].equals("p")))
                            break;
                    }
                    int q;
                    for (q = i + 1; q < k.length; q++) {
                        if (!(k[q].equals("p")))
                            break;
                    }
                    if (k[i].equals("+")) {
                        k[i] = addBigDecimal(k[l], k[q]);
                        k[l] = "p";
                        k[q] = "p";
                        kp--;
                    } else {
                        k[i] = reduceBigDecimal(k[l], k[q]);
                        k[l] = "p";
                        k[q] = "p";
                        kp--;
                    }
                    break;
                }
            }
            for (String value : k) {
                if (!(value.equals("p"))) {
                    result = value;
                    break;
                }
            }
        }
        return result;
    }

    public double calculate() {
        while (true) {
            int first = 0;
            int last = 0;
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == '(')
                    first = i;
                if (expression.charAt(i) == ')') {
                    last = i;
                    break;
                }
            }
            if (last == 0) {
                return Double.parseDouble(result(expression));
            } else {
                String s1 = expression.substring(0, first);
                String s2 = expression.substring(first + 1, last);
                String s3 = expression.substring(last + 1);
                expression = s1 + result(s2) + s3;
            }
        }
    }
}