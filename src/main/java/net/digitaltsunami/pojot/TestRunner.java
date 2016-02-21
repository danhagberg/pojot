package net.digitaltsunami.pojot;

import java.util.List;

/**
 * Performs a test and returns returns the results as a list of error strings.
 */
public interface TestRunner<T> {
    /**
     * Run the set of tests and return the resulting errors as String values. If no errors are found, an
     * empty list will be returned.
     * @return List of String representing errors found during the test or empty if no errors found.
     * @throws TestAidException if the test could not be executed. This is an unchecked exception.
     */
    List<String> runTests() throws TestAidException;
}
