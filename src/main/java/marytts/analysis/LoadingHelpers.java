package marytts.analysis;

import java.io.File;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.FileInputStream;


/**
 * 
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class LoadingHelpers
{
    public static double[][] loadBinary(String filename, int frame_dim)
        throws IOException
    {
        return loadBinary(new File(filename), frame_dim);
    }
    
    public static double[][] loadBinary(File file, int frame_dim)
        throws IOException
    {
        
        // Compute array size !
        long fl = file.length() / Double.SIZE;
        assert ((fl % frame_dim) == 0);
        int nb_frames = ((int) fl) / frame_dim;
        
        // Loading data (assume double in the files)
        double[][] data = new double[nb_frames][frame_dim];

        // Get doubles
        int nb_doubles = nb_frames * frame_dim;
        DataInputStream stream = new DataInputStream(new FileInputStream(file));
        for (int i=0; i<nb_doubles; i++)
        {
            data[i/frame_dim][i%frame_dim] = stream.readDouble();
        }
        return data; 
        
    }

    public static double[][] loadFloatBinary(String filename, int frame_dim)
        throws IOException
    {
        return loadFloatBinary(new File(filename), frame_dim);
    }

    
    public static double[][] loadFloatBinary(File file, int frame_dim)
        throws IOException
    {
        
        // Compute array size !
        long fl = file.length() / Float.SIZE;
        assert ((fl % frame_dim) == 0);
        int nb_frames = ((int) fl) / frame_dim;
        
        // Loading data (assume double in the files)
        double[][] data = new double[nb_frames][frame_dim];

        // Get doubles
        int nb_doubles = nb_frames * frame_dim;
        DataInputStream stream = new DataInputStream(new FileInputStream(file));
        for (int i=0; i<nb_doubles; i++)
        {
            data[i/frame_dim][i%frame_dim] = stream.readFloat();
        }
        return data; 
        
    }

    
}
