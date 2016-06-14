package marytts.analysis.distances.acoustic;

import java.util.ArrayList;

/**
 *
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class CentRMS extends F0RMS
{
    public CentRMS(double[][] src, double[][] tgt, double ignored_value)
    {
        super(src, tgt, ignored_value);
    }

    public Double log2(Double v)
    {
        return Math.log(v) / Math.log(2);
    }

    /**
     *
     * TODO: get into the log domain ?!
     */
    public Double distancePerFrame(int idx_frame_src, int idx_frame_tgt)
    {
        Double dist = 0.0;

        if ((src[idx_frame_src][0] != ignored_value) &
            (tgt[idx_frame_tgt][0] != ignored_value))
        {
            dist = 1200 * 1200 *
                (log2(src[idx_frame_src][0]) - log2(tgt[idx_frame_tgt][0])) *
                (log2(src[idx_frame_src][0]) - log2(tgt[idx_frame_tgt][0]));

        }
        else if (src[idx_frame_src][0] != tgt[idx_frame_tgt][0])
        {
            dist = 100000.0; // FIXME: hardcoded huge value
        }

        return dist;
    }
}
