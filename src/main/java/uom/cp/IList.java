package uom.cp;

/**
 * named <code>IList</code> to avoid confusion with default {@link java.util.List} implementation of java
 */
public interface IList {

    /**
     * Creates a node and inserts an integer into it in the list
     *
     * @param data an integer
     * @return <code>true / false</code> depending on successful insertion / failure (i.e., data already present)
     */
    boolean insert(int data);

    /**
     * Checks whether an integer is in the list or not
     *
     * @param data integer to be tested
     * @return <code>true</code> if data already in the list
     * <p>
     * <code>false</code> if data not in the list
     */
    boolean member(int data);

    /**
     * Delete the data from list
     *
     * @param data the provided integer
     * @return <code>true</code> upon successful deletion
     * <p>
     * <code>false</code> if data already in the list
     */
    boolean delete(int data);

    String toString();
}
