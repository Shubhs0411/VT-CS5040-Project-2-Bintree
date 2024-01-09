/**
 * A key-value pair class that implements Comparable interface.
 *
 * @param <K>
 *            Type of the key (must be Comparable)
 * @param <E>
 *            Type of the value
 * 
 * @author {Lavisha Goyal}
 * @author {Shubham Laxmikant Deshmukh}
 * @version {1.0}
 */

public class KVPair<K extends Comparable<K>, E>
    implements Comparable<KVPair<K, E>> {
    private K theKey;
    private E theVal;

    /**
     * Constructor for creating a KVPair.
     *
     * @param k
     *            The key
     * @param v
     *            The value
     */
    KVPair(K k, E v) {
        theKey = k;
        theVal = v;
    }


    /**
     * Compare this KVPair to another KVPair.
     *
     * @param it
     *            The KVPair to compare to
     * @return Negative if this is smaller, positive if this is larger, 0 if
     *         equal
     */
    public int compareTo(KVPair<K, E> it) {
        return theKey.compareTo(it.key());
    }


    /**
     * Compare this KVPair against a key.
     *
     * @param it
     *            The key to compare to
     * @return Negative if this is smaller, positive if this is larger, 0 if
     *         equal
     */
    public int compareTo(K it) {
        return theKey.compareTo(it);
    }


    /**
     * Get the key of this KVPair.
     *
     * @return The key
     */
    public K key() {
        return theKey;
    }


    /**
     * Get the value of this KVPair.
     *
     * @return The value
     */
    public E value() {
        return theVal;
    }
}
