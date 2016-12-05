package marytts.analysis.distances.acoustic;

import java.util.ArrayList;
import marytts.analysis.core.Alignment;
/**
 *
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class F0RMS extends RMS
{
    protected double ignored_value;

    public F0RMS(double[][] src, double[][] tgt, double ignored_value)
    {
        super(src, tgt, 1);
        this.ignored_value = ignored_value;
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
            dist = (src[idx_frame_src][0] - tgt[idx_frame_tgt][0]) * (src[idx_frame_src][0] - tgt[idx_frame_tgt][0]);
        }
        else if (src[idx_frame_src][0] != tgt[idx_frame_tgt][0])
        {
            dist = 100000.0; // FIXME: hardcoded huge value
        }

        return dist;
    }

    public Double distancePerUtterance()
    {
        // Compute distance
        ArrayList<int[]> path = alignment.getPath();
        int T = path.size();
        int nb_val = 0;
        Double dist = 0.0;
        for (int t=0; t<T; t++)
        {
            int[] tmp = path.get(t);
            if ((src[tmp[0]][0] != ignored_value) &&
                (tgt[tmp[1]][0] != ignored_value))
            {
                dist += distancePerFrame(tmp[0], tmp[1]);
                nb_val++;
            }


        }

        if (Double.isNaN(dist/nb_val))
        {
            throw new IllegalArgumentException("why ===> " + nb_val);
        }

        return Math.sqrt(dist/nb_val);
    }


    public Double distancePerUtterance(Alignment forced_alignment)
    {
        ArrayList<int[]> cur_path = forced_alignment.getPath();

        // Compute distance
        int T = cur_path.size();
        int nb_val = 0;
        Double dist = 0.0;
        for (int t=0; t<T; t++)
        {
            int[] tmp = cur_path.get(t);
            if ((src[tmp[0]][0] != ignored_value) &&
                (tgt[tmp[1]][0] != ignored_value))
            {
                dist += distancePerFrame(tmp[0], tmp[1]);
                nb_val++;
            }


        }

        if (Double.isNaN(dist/nb_val))
        {
            throw new IllegalArgumentException("why ===> " + nb_val);
        }

        return Math.sqrt(dist/nb_val);
    }
}
