package marytts.analysis.distances.acoustic;

import java.util.ArrayList;
import marytts.analysis.core.Distance;

/**
 *
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class RMS extends Distance
{
    public RMS(double[][] src, double[][] tgt, int dim)
    {
        super(src, tgt, dim);
    }

    /**
     *
     * TODO: get into the log domain ?!
     */
    public Double distancePerFrame(int idx_frame_src, int idx_frame_tgt)
    {
        Double dist = 0.0;
        for (int i=0; i<dim; i++)
        {
            dist += (src[idx_frame_src][i] - tgt[idx_frame_tgt][i]) * (src[idx_frame_src][i] - tgt[idx_frame_tgt][i]);
        }

        return dist;
    }

    public Double distancePerUtterance()
    {
        // Compute distance
        int T = path.size();
        Double dist = 0.0;
        for (int t=0; t<T; t++)
        {
            int[] tmp = path.get(t);
            dist += distancePerFrame(tmp[0], tmp[1]) / T;
        }

        return Math.sqrt(dist);
    }
}
