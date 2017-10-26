package marytts.analysis.alignment;

import java.util.ArrayList;
import marytts.analysis.core.*;

/**
 * DTW alignement based on a specific distance class
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */
public class IDAlignment implements Alignment {
    private ArrayList<int[]> path; /*< the path (each item contains a couple (idx_source, idx_tgt)) */
    private int nb_frames;

    /* ===========================================================================================
    * ## Initialisation
    * =========================================================================================== */

    /**
     *  Constructor
     *
     *  @param nb_frames: the number of frames
     */
    public IDAlignment(int nb_frames) {
        this.nb_frames = nb_frames;

        path = null;

    }


    /**
     *  Compute the path from the matrix. If the matrix is not initialised, it calls fillMatrix
     *
     */
    public void computePath() {
        path = new ArrayList<int[]>();

        for (int i = 0; i < nb_frames; i++) {
            int[] tmp = new int[2];
            tmp[0] = i;
            tmp[1] = i;
            path.add(tmp);
        }
    }


    /**
     *  Get the DTW resulted path. If the path is not initialised, it calls computePath
     *
     */
    public ArrayList<int[]> getPath() {
        if (path == null) {
            computePath();
        }

        return path;
    }

    /* ===========================================================================================
     * ## DTW operators
     * =========================================================================================== */
    /**
     *  Classical toString method to print the visual representation of the matrix and the path
     *
     *  @return the generated string
     */
    public String toString() {

        String result_string = "";

        if (path == null) {
            result_string += "Path is null\n";
            return result_string;
        }

        result_string += "\n# ====================================\n";
        result_string += "# Path\n";
        result_string += "# ====================================\n";
        for (int i = 0; i < path.size(); i++) {
            int[] tmp = path.get(i);
            result_string += "(" + tmp[0] + ", " + tmp[1] + ")\n";
        }


        return result_string;
    }

}
