package marytts.analysis;

import java.util.ArrayList;

/**
 * 
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class CentRMS extends RMS
{   
    public CentRMS(double[][] src, double[][] tgt, int dim)
    {
        super(src, tgt, dim);
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
        for (int i=0; i<dim; i++)
        {
            dist += 1200 * (log2(src[idx_frame_src][i]) - log2(tgt[idx_frame_tgt][i])) *
                (log2(src[idx_frame_src][i]) - log2(tgt[idx_frame_tgt][i]));
        }
        return dist;
    }
}
