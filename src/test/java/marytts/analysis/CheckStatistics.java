package marytts.analysis;

import java.util.ArrayList;


import org.testng.Assert;
import org.testng.annotations.*;


/**
 *
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */
public class CheckStatistics {
    Double values[] = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
    Statistics s;
    public CheckStatistics() {
        s = new Statistics(values);
    }

    @Test
    public void checkMean() {
        Double m = s.mean();
        System.out.println("m = " + m);
        Assert.assertEquals(m, 5.5);
    }


    // @Test
    // public void checkStdDev()
    // {
    //     Double sigma = s.stddev();
    //     System.out.println("sigma = " + sigma);
    //     Assert.assertEquals(sigma, 5.5);
    // }

    // @Test
    // public void checkVariance()
    // {
    //     Double sigma = s.variance();
    //     System.out.println("variance = " + sigma);
    //     Assert.assertEquals(sigma, 5.5);
    // }

    @Test
    public void checkMedian() {
        Double m = s.median();
        System.out.println("median = " + m);
        Assert.assertEquals(m, 6.0);
    }

    @Test
    public void checkQuartiles() {
        Double[] q = s.quartiles();
        System.out.println("Quartiles");
        System.out.println("\t- 25% = " + q[0]);
        System.out.println("\t- 50% = " + q[1]);
        System.out.println("\t- 75% = " + q[2]);

        Assert.assertEquals(q[0], 3.0);
        Assert.assertEquals(q[1], 6.0);
        Assert.assertEquals(q[2], 8.0);
        Assert.assertEquals(q[1], s.median());
    }


    // @Test
    // public void checkConfintBootstrap()
    //     throws Exception
    // {
    //     Double sigma = s.confintBootstrap(0.05, 200);
    //     System.out.println("confintboot = " + sigma);
    //     Assert.assertEquals(sigma, 5.5);
    // }

    // @Test
    // public void checkConfint()
    // {
    //     Double sigma = s.confint(0.05);
    //     System.out.println("confint = " + sigma);
    //     Assert.assertEquals(sigma, 5.5);
    // }
}
