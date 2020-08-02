package calculator;

import java.text.DecimalFormat;

public class Calculator {

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';


    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        if (statement == null)
            return null;

        return solution(statement);
    }

    private String solution(String statement) {
        char[] chars = statement.toCharArray();
        StringBuilder builder = new StringBuilder();
        Double x = null;
        double y;
        char operand = '0';

        boolean valid = validate(chars);
        if (!valid)
            return null;

        for (int i = 0; i < chars.length; i++) {
            if (isDigit(chars[i])) {
                builder.append(chars[i]);
            } else {
                x = Double.parseDouble(builder.toString());
                builder = new StringBuilder();
                operand = chars[i];
            }
        }
        y = Double.parseDouble(builder.toString());

        Double result = function(x, y, operand);
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(result);
    }

    private boolean validate(char[] chars) {
        if (chars.length == 0)
            return false;

        for (int i = 0; i < chars.length; i++) {
            char index = chars[i];
            if (!isDigit(index) && !isOperand(index) && !isDot(index))
                return false;

            if (isOperand(index)) {
                if (i == 0 && index != SUBTRACTION)
                    return false;
                if (i == chars.length - 1)
                    return false;
                if (isOperand(chars[i - 1]) || isOperand(chars[i + 1]) || isDot(chars[i - 1]) || isDot(chars[i + 1]))
                    return false;
            }

            if (isDot(index)) {
                if (i == 0)
                    return false;
                if (i == chars.length - 1)
                    return false;
                if (isOperand(chars[i - 1]) || isOperand(chars[i + 1]) || isDot(chars[i - 1]) || isDot(chars[i + 1]))
                    return false;
            }
        }
        return true;
    }

    private boolean isDot(char index) {
        return index == '.';
    }

    private boolean isOperand(char c) {
        return c == ADDITION || c == SUBTRACTION || c == MULTIPLICATION || c == DIVISION;
    }

    private boolean isDigit(char number) {
        String num = String.valueOf(number);
        try {
            Double.parseDouble(num);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    private Double function(Double x, Double y, char operand) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("Invalid operation!");
        }
        switch (operand) {
            case ADDITION:
                return x + y;
            case SUBTRACTION:
                return x - y;
            case MULTIPLICATION:
                return x * y;
            case DIVISION:
                return x / y;
            default:
                throw new IllegalArgumentException("Invalid operation!");
        }
    }
}
