package net.digitaltsunami.pojot;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 *
 */
public class TestAidExceptionTest {
    @Test
    public void testMessageOnly(){
        final String testMsg = "Test Message";
        TestAidException tae = new TestAidException(testMsg);
        assertEquals(testMsg, tae.getMessage());
    }
    @Test
    public void testMessageWithThrowable(){
        final String testMsg = "Test Message";
        final Exception exception = new Exception();
        TestAidException tae = new TestAidException(testMsg, exception);
        assertEquals(testMsg, tae.getMessage());
        assertEquals(exception, tae.getCause());
    }

}