package quantum_search_submax_lib;

/**
 * The instances of classes that implement this interface can be converted to 0 or 1
 */
public interface Relevant {
    /**
     * Returns boolean value depending on whether an instance of the class is suitable or not
     */
    boolean isRelevant();
}