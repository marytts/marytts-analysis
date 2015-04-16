package marytts.analysis;

import java.util.ArrayList;

/**
 * 
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public abstract class Distance implements DistanceInterface
{
    protected double[][] src;
    protected double[][] tgt;
    protected int dim;
    protected ArrayList<int[]> path;
    public Alignment alignment;

    protected Distance() {
    }
    protected Distance(double[][] src, double[][] tgt, int dim)
    {
        this.src = src;
        this.tgt = tgt;
        this.dim = dim;
        
        // Alignment
        alignment = new Alignment(this, src.length, tgt.length);
        path = alignment.getPath();
    }
    
    /**
     *
     * TODO: get into the log domain ?!
     */
    public abstract Double distancePerFrame(int idx_frame_src, int idx_frame_tgt);
    
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
        
        return dist;
    }

}
