package marytts.analysis.distances;

import java.util.ArrayList;
import marytts.analysis.core.DistanceInterface;
import marytts.analysis.alignment.DTW;
import marytts.analysis.core.Alignment;

/**
 *
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */
public abstract class DTWBasedDistance implements DistanceInterface {
    protected double[][] src;
    protected double[][] tgt;
    protected int dim;
    public Alignment alignment;

    protected DTWBasedDistance() {
    }

    protected DTWBasedDistance(double[][] src, double[][] tgt, int dim) {
        this.src = src;
        this.tgt = tgt;
        this.dim = dim;

        // Alignment
        alignment = new DTW(this, src.length, tgt.length);
    }

    /**
     *
     * TODO: get into the log domain ?!
     */
    public abstract Double distancePerFrame(int idx_frame_src, int idx_frame_tgt);

    public Double distancePerUtterance() {
        ArrayList<int[]> path = alignment.getPath();
        // Compute distance
        int T = path.size();
        Double dist = 0.0;
        for (int t = 0; t < T; t++) {
            int[] tmp = path.get(t);
            dist += distancePerFrame(tmp[0], tmp[1]) / T;
        }

        return dist;
    }

}
