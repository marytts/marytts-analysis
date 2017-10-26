package marytts.analysis.distances.string;

import java.util.ArrayList;
import marytts.analysis.core.DistanceInterface;
import marytts.analysis.alignment.DTW;

/**
 *
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class Levenstein implements DistanceInterface {
    String src;
    String tgt;
    ArrayList<int[]> path;
    public DTW alignment;

    public Levenstein(String src, String tgt) {
        this.src = src;
        this.tgt = tgt;
        alignment = null;
    }

    public Double distancePerFrame(int idx_frame_src, int idx_frame_tgt) {
        if (src.charAt(idx_frame_src) == tgt.charAt(idx_frame_tgt)) {
            return 0.0;
        }

        return 1.0;
    }

    public Double distancePerUtterance() {
        // Alignment
        alignment = new DTW(this, src.length(), tgt.length());
        path = alignment.getPath();

        // Compute distance
        Double dist = 0.0;
        for (int i = 0; i < path.size(); i++) {
            int[] tmp = path.get(i);
            dist += distancePerFrame(tmp[0], tmp[1]);
        }
        return dist;
    }

    public ArrayList<int[]> getPath() {
        return path;
    }
}
