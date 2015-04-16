package marytts.analysis;

/**
 * Basic distance interface. It assumes that the instance of a distance is specific to
 * an utterance !
 *
 * @author <a href="mailto:slemaguer@coli.uni-saarland.de">Sébastien Le Maguer</a>
 */

public interface DistanceInterface
{
    /**
     *  Distance between two specific frames. This method is used in the Alignment class
     *
     *  @param idx_frame_src the index of the frame in the source utterance sequence
     *  @param idx_frame_tgt the index of the frame in the target utterance sequence
     *  @return the distance value
     */
    public Double distancePerFrame(int idx_frame_src, int idx_frame_tgt);

    /**
     *  Distance between the utterances. The utterances needs to be defined. The developper needs to
     *  insure that's the case when he is implementing this method.
     *
     *  @return the distance value
     */
    public Double distancePerUtterance();
}
