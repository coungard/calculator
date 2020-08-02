package calculator;

public class Test {

    private static final String eval1 = "2+3";
    private static final String eval2 = "5-7";
    private static final String eval3 = "2+3*4";
    private static final String eval4 = "10/(2-7+3)*4";
    private static final String eval5 = "-10+23";

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println(calculator.evaluate(eval1));
        System.out.println(calculator.evaluate(eval2));
        System.out.println(calculator.evaluate(eval3));
        System.out.println(calculator.evaluate(eval4));
        System.out.println(calculator.evaluate(eval5));
    }
}
