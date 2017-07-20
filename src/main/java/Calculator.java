/**
 * Created by panda on 17/7/20.
 */
public class Calculator {
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand : expression.split("\\+"))
            sum += Integer.valueOf(summand);
        return sum;
    }
}
