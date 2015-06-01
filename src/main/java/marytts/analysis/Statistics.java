package marytts.analysis;

import weka.core.FastVector;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// // Bootstrap class
// import weka.core.Instances;
// import weka.core.Instance;
// import weka.core.SparseInstance;
// import weka.core.Attribute;
// import weka.filters.supervised.instance.Resample;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * 
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public class Statistics
{
    private Double mean;
    private Double stddev;
    private Double[] values;
    
    public Statistics(Double[] values)
    {
        // Sort the array it will be easier for the remaining stuff [FIXME: check if there is no side effect]
        this.values = values;
        Arrays.sort(this.values);
        this.mean = null;
        this.stddev = null;
    }
    
    public Double mean () {
        if (mean != null)
            return this.mean;
        
        this.mean = 0.0;
        for (int i=0; i<values.length; i++)
        {
            this.mean += values[i] / values.length;
        }

        return this.mean;
    }

    public Double median() {
        return values[values.length/2];
    }

    public Double[] quartiles() {
        Double[] quartiles = new Double[3];
        quartiles[0] = values[values.length/4];
        quartiles[1] = values[values.length/2];
        quartiles[2] = values[3*values.length/4];

        return quartiles;
    }

    public Double stddev () {
        if (stddev != null)
            return stddev;
        
        if (this.mean == null)
            mean();
        
        Double mean2 = 0.0;
        for (int i=0; i<values.length; i++)
        {
            mean2 += values[i]*values[i] / values.length;
        }

        this.stddev = Math.sqrt(mean2 - (this.mean * this.mean));
        return this.stddev;
    }

    public Double variance()
    {
        if (stddev == null)
            stddev();

        return this.stddev*this.stddev;
   } 

    // public Double confintBootstrap(Double pvalue, double factor_resampling)
    //     throws Exception
    // {
    //     assert (factor_resampling >= 1.0);
        
    //     // Do not want to use bootstrap
    //     if (factor_resampling == 1.0)
    //         return confint(pvalue);

    //     // Create new dataset
    //     FastVector atts = new FastVector();
    //     // ArrayList<Attribute> atts = new ArrayList<Attribute>();
    //     List<Instance> instances = new ArrayList<Instance>();
    //     Attribute current = new Attribute("Attribute" + 0, 0);
           
    //     for(int obj = 0; obj < values.length; obj++)
    //     {
    //         instances.add(new SparseInstance(1));
    //         instances.get(obj).setValue(current, values[obj]);
    //     }
    //     // atts.add(current);
    //     atts.addElement(current);
        
    //     Instances data = new Instances("Dataset", atts, instances.size());
        
    //     // Fill in data objects
    //     for(Instance inst : instances)
    //         data.add(inst);

    //     // Configure the bootstrapper
    //     Resample sampler = new Resample();
    //     sampler.setInputFormat(data);
    //     sampler.setRandomSeed((int)System.currentTimeMillis());
    //     sampler.setSampleSizePercent(factor_resampling * 100.0);
    //     // String Fliteroptions="-B 1.0";
    //     // sampler.setOptions(weka.core.Utils.splitOptions(Fliteroptions));

    //     // Bootstrap
    //     data = Resample.useFilter(data, sampler);
        
        
    //     // Then gather and finish !
    //     Double[] resampled_values = new Double[data.numInstances()];
    //     for (int v=0; v<data.numInstances(); v++)
    //     {   
    //         resampled_values[v] = data.instance(v).value(0);
    //     }
    //     // Confidence interval computed
    //     SummaryStatistics stats = new SummaryStatistics();
    //     for (Double val : resampled_values) {
    //         stats.addValue(val);
    //     }

    //     return calcMeanCI(stats, 1-pvalue);
    // }

    
    public Double confint(Double pvalue)
    {
        SummaryStatistics stats = new SummaryStatistics();
        
        for (Double val : values) {
            stats.addValue(val);
        }

        return calcMeanCI(stats, 1-pvalue);
    }
    
    private Double calcMeanCI(SummaryStatistics stats, double level) {
        try {
            // Create T Distribution with N-1 degrees of freedom
            TDistribution tDist = new TDistribution(stats.getN() - 1);
            // Calculate critical value
            double critVal = tDist.inverseCumulativeProbability(1.0 - (1 - level) / 2);
            // Calculate confidence interval
            return critVal * stats.getStandardDeviation() / Math.sqrt(stats.getN());
        } catch (MathIllegalArgumentException e) {
            return Double.NaN;
        }
    }
}
