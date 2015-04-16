package marytts.analysis;

import java.io.File;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.FileInputStream;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/**
 * 
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class LoadingHelpers
{
    ByteOrder endianness;

    public LoadingHelpers()
    {
        this(ByteOrder.LITTLE_ENDIAN);
    }
    
    public LoadingHelpers(ByteOrder endianness)
    {
        this.endianness = endianness;
    }
    
    public  double[][] loadBinary(String filename, int frame_dim)
        throws IOException
    {
        return loadBinary(new File(filename), frame_dim);
    }
    
    public  double[][] loadBinary(File file, int frame_dim)
        throws IOException
    {
        // Compute array size !
        long fl = file.length() / (Double.SIZE/Byte.SIZE);
        assert ((fl % frame_dim) == 0);
        int nb_frames = ((int) fl) / frame_dim;
        
        // Loading data (assume double in the files)
        double[][] data = new double[nb_frames][frame_dim];

        // Get doubles
        int nb_doubles = nb_frames * frame_dim;
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        
        ByteBuffer buffer = ByteBuffer.allocate((int) fl * (Double.SIZE/Byte.SIZE));
        buffer.order(endianness);
        fc.read(buffer);
        buffer.flip();
        for (int i=0; i<nb_frames; i++)
        {
            for (int j=0; j<frame_dim; j++)
            {
                data[i][j] = (double) buffer.getDouble();
            }
        }
        return data;    
        
    }

    public  double[][] loadFloatBinary(String filename, int frame_dim)
        throws IOException
    {
        return loadFloatBinary(new File(filename), frame_dim);
    }

    
    public  double[][] loadFloatBinary(File file, int frame_dim)
        throws IOException
    {
        // Compute array size !
        long fl = file.length() / (Float.SIZE/Byte.SIZE);
        assert ((fl % frame_dim) == 0);
        int nb_frames = ((int) fl) / frame_dim;
        
        // Loading data (assume double in the files)
        double[][] data = new double[nb_frames][frame_dim];

        // Get doubles
        int nb_doubles = nb_frames * frame_dim;
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        
        ByteBuffer buffer = ByteBuffer.allocate((int) fl * (Float.SIZE/Byte.SIZE));
        buffer.order(endianness);
        fc.read(buffer);
        buffer.flip();
        for (int i=0; i<nb_frames; i++)
        {
            for (int j=0; j<frame_dim; j++)
            {
                data[i][j] = (double) buffer.getFloat();
            }
        }
        return data;    
    }
    

    
}
