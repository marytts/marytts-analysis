package marytts.analysis;

import java.util.ArrayList;

/**
 * 
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class VoicingError extends Distance
{
    Double unvoiced_value;
    public VoicingError(double[][] src, double[][] tgt, Double unvoiced_value)
    {
        super(src, tgt, 1);
        
        this.unvoiced_value = unvoiced_value;
        
        // Basic checking
        assert (src[0].length == 1);
        assert (tgt[0].length == 1);
    }
    
    /**
     *
     * TODO: get into the log domain ?!
     */
    public Double distancePerFrame(int idx_frame_src, int idx_frame_tgt)
    {
        // Both voiced
        if ((src[idx_frame_src][0] != unvoiced_value) &&
            (tgt[idx_frame_tgt][0] != unvoiced_value))
        {
            return 0.0;
        }
        // Both unvoiced
        else if ((src[idx_frame_src][0] == unvoiced_value) &&
                 (tgt[idx_frame_tgt][0] == unvoiced_value))
        {
            return 0.0;
        }
        // Different
        return 1.0;
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
        return dist * 100;
    }
}
