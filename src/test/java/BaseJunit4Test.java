/**
 * Created by panda on 17/7/20.
 */

import org.junit.Assert;
import org.junit.Test;

public class BaseJunit4Test {

    @Test
    public void evaluatesExpression() {
        Calculator calculator = new Calculator();
        int sum = calculator.evaluate("1+2+3");
        Assert.assertEquals(6, sum);
    }
}