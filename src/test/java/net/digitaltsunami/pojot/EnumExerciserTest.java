package net.digitaltsunami.pojot;

import net.digitaltunami.pojot.testsubject.TestEnum;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 *
 */
public class EnumExerciserTest {

    @Test
    public void testExercise() throws Exception {
        assertTrue(EnumExerciser.exercise(TestEnum.class) > 0L, "Failed to exercise enum");

    }

}