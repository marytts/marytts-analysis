package marytts.analysis.distances.acoustic;

import java.util.ArrayList;

/**
 *
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class CepstralDistorsion extends RMS
{
    public CepstralDistorsion(double[][] src, double[][] tgt, int dim)
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
        for (int i=1; i<dim; i++)
        {
            dist += (src[idx_frame_src][i] - tgt[idx_frame_tgt][i]) * (src[idx_frame_src][i] - tgt[idx_frame_tgt][i]);
        }
        return 2 * dist * 10 / Math.log(10);
    }
}
