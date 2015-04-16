package marytts.analysis;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class CheckDistances
{
    @Test
    public void checkLevenstein()
    {
        String src = "once";
        String tgt = "onete";
        Levenstein l = new Levenstein(src, tgt);
        Double d = l.distancePerUtterance();
        System.out.println(l.alignment);
        System.out.println("\nd = " + d);
        ArrayList<int[]> p = l.alignment.getPath();
        for (int i=p.size()-1; i>=0; i--)
        {
            int[] tmp = p.get(i);
            System.out.print(src.charAt(tmp[0]) + "\t");
        }
        System.out.println("");
        for (int i=p.size()-1; i>=0; i--)
        {
            int[] tmp = p.get(i);
            System.out.print(tgt.charAt(tmp[1]) + "\t");
        }
        System.out.println("");
        assert(d == 2.0);
    }
}
