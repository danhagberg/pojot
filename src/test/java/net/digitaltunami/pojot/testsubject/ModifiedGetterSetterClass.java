package net.digitaltunami.pojot.testsubject;

/**
 *
 */
public class ModifiedGetterSetterClass {
    private int intValue1;

    public int getIntValue1() {
        return intValue1 + 2;
    }

    public void setIntValue1(int intValue1) {
        this.intValue1 = intValue1 + 3;
    }
}
