package net.digitaltunami.pojot.testsubject;

/**
 * Created by dhagberg on 2/1/16.
 */
public class BasicClass {
    private String privateString;
    protected String protectedString;
    public String publicString;

    private String stringWithNoGetter;

    public String getProtectedString() {
        return protectedString;
    }

    public void setProtectedString(String protectedString) {
        this.protectedString = protectedString;
    }

    public String getPublicString() {
        return publicString;
    }

    public void setPublicString(String publicString) {
        this.publicString = publicString;
    }

    public String getPrivateString() {
        return privateString;
    }

    public void setPrivateString(String privateString) {

        this.privateString = privateString;
    }
    public void setStringWithNoGetter(String stringWithNoGetter) {
        this.stringWithNoGetter = stringWithNoGetter;
    }

}
