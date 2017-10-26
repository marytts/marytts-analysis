package marytts.analysis.alignment;

import java.util.ArrayList;
import marytts.analysis.core.*;

/**
 * DTW alignement based on a specific distance class
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */
public class DTW implements Alignment {
    private DistanceInterface distance; /*< the distance interface to get the distance between frames */
    private int nb_frames_src; /*< size of the source utterance in number of frames */
    private int nb_frames_tgt; /*< size of the target utterance in number of frames */
    private Double[][] dtw_matrix; /*< the matrix to store the dyn. prog results */
    private ArrayList<int[]> path; /*< the path (each item contains a couple (idx_source, idx_tgt))

    /* ===========================================================================================
    * ## Initialisation
    * =========================================================================================== */

    /**
     *  Constructor
     *
     *  @param distance : the distance classe to compute the distance between two frames
     *  @param nb_frames_src : the number of frames of the source
     *  @param nb_frames_tgt : the number of frames of the target
     */
    public DTW(DistanceInterface distance, int nb_frames_src, int nb_frames_tgt) {
        this.distance = distance;
        this.nb_frames_src = nb_frames_src;
        this.nb_frames_tgt = nb_frames_tgt;

        // Matrix initialisation
        dtw_matrix = null;
        path = null;

    }


    /* ===========================================================================================
     * ## DTW operators
     * =========================================================================================== */

    /**
     *  Fill the DTW matrix
     *
     */
    public void fillMatrix() {
        dtw_matrix = new Double[nb_frames_src + 1][nb_frames_tgt + 1];

        // Initialisation
        dtw_matrix[0][0] = 0.0;
        for (int i = 1; i <= nb_frames_src; i++) {
            dtw_matrix[i][0] = dtw_matrix[i - 1][0] + distance.distancePerFrame(i - 1, 0);
        }
        for (int j = 1; j <= nb_frames_tgt; j++) {
            dtw_matrix[0][j] = dtw_matrix[0][j - 1] + distance.distancePerFrame(0, j - 1);
        }

        // Filling
        for (int i = 1; i <= nb_frames_src; i++) {
            for (int j = 1; j <= nb_frames_tgt; j++) {
                Double min = (dtw_matrix[i - 1][j] < dtw_matrix[i][j - 1]) ? dtw_matrix[i - 1][j] : dtw_matrix[i][j - 1];
                min = (min < dtw_matrix[i - 1][j - 1]) ? min : dtw_matrix[i - 1][j - 1];
                dtw_matrix[i][j] = distance.distancePerFrame(i - 1, j - 1) + min;
            }
        }
    }

    /**
     *  Compute the path from the matrix. If the matrix is not initialised, it calls fillMatrix
     *
     */
    public void computePath() {
        if (dtw_matrix == null) {
            fillMatrix();
        }

        path = new ArrayList<int[]>();
        int i = nb_frames_src;
        int j = nb_frames_tgt;

        int[] tmp = new int[2];
        tmp[0] = i - 1;
        tmp[1] = j - 1;
        path.add(tmp);
        while ((i > 1) && (j > 1)) {
            if (i == 1) {
                j = j - 1;
            } else if (j == 1) {
                i = i - 1;
            } else {
                Double min = (dtw_matrix[i - 1][j] < dtw_matrix[i][j - 1]) ? dtw_matrix[i - 1][j] : dtw_matrix[i][j - 1];
                min = (min < dtw_matrix[i - 1][j - 1]) ? min : dtw_matrix[i - 1][j - 1];
                if (dtw_matrix[i - 1][j] == min) {
                    i = i - 1;
                } else if (dtw_matrix[i][j - 1] == min) {
                    j = j - 1;
                } else {
                    i = i - 1;
                    j = j - 1;
                }
            }

            tmp = new int[2];
            tmp[0] = i - 1;
            tmp[1] = j - 1;
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
        if (dtw_matrix == null) {
            result_string += "Matrix is null\n";
        }

        if (path == null) {
            result_string += "Path is null\n";
            return result_string;
        }

        result_string += "# ====================================\n";
        result_string += "# Matrix\n";
        result_string += "# ====================================\n";
        for (int i = 0; i <= nb_frames_src; i++) {
            for (int j = 0; j <= nb_frames_tgt; j++) {
                result_string += dtw_matrix[i][j] + "\t";
            }
            result_string += "\n";
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
