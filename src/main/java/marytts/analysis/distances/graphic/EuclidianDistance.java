package marytts.analysis.distances.graphic;

import java.util.ArrayList;
import marytts.analysis.distances.DTWBasedDistance;
import marytts.analysis.core.Alignment;

/**
 *
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */
public class EuclidianDistance extends DTWBasedDistance
{
    public EuclidianDistance(double[][] src, double[][] tgt, int dim)
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

        return Math.sqrt(dist);
    }

    public Double distancePerUtterance()
    {
        throw new UnsupportedOperationException();
    }

    public Double distancePerUtterance(Alignment forced_alignment)
    {
        throw new UnsupportedOperationException();
    }
}
