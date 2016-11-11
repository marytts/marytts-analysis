package marytts.analysis.core;

import java.util.ArrayList;

/**
 *
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */
public interface Alignment
{
    public void computePath();
    public ArrayList<int[]> getPath();
}

/* Alignment.java ends here */
