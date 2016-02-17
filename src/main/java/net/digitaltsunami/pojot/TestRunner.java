package net.digitaltsunami.pojot;

import java.util.List;

/**
 * Created on 2/5/16.
 */
public interface TestRunner<T> {
    List<String> runTests() throws TestAidException;
}
