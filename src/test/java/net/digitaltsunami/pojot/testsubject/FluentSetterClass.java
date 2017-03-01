package net.digitaltsunami.pojot.testsubject;

public class FluentSetterClass {
    private int privateInt;
    private Long privateLong;
    private String privateString;
    private String modifedSetterValue;
    private String noSetterForThisProperty;
    protected String protectedString;
    public String publicString;

    private String stringWithNoGetter;

    public String getProtectedString() {
        return protectedString;
    }

    public FluentSetterClass setProtectedString(String protectedString) {
        this.protectedString = protectedString;
        return this;
    }

    public String getPublicString() {
        return publicString;
    }

    public FluentSetterClass setPublicString(String publicString) {
        this.publicString = publicString;
        return this;
    }

    public String getPrivateString() {
        return privateString;
    }

    public FluentSetterClass setPrivateString(String privateString) {

        this.privateString = privateString;
        return this;
    }

    public FluentSetterClass setStringWithNoGetter(String stringWithNoGetter) {
        this.stringWithNoGetter = stringWithNoGetter;
        return this;
    }

    public int getPrivateInt() {
        return privateInt;
    }

    public FluentSetterClass setPrivateInt(int privateInt) {
        this.privateInt = privateInt;
        return this;
    }

    public Long getPrivateLong() {
        return privateLong;
    }

    public FluentSetterClass setPrivateLong(Long privateLong) {
        this.privateLong = privateLong;
        return this;
    }

    public String getModifedSetterValue() {
        return modifedSetterValue;
    }

    public FluentSetterClass setModifedSetterValue(String modifedSetterValue) {
        this.modifedSetterValue = modifedSetterValue + modifedSetterValue;
        return this;
    }

    public String getNoSetterForThisProperty() {
        return noSetterForThisProperty;
    }

    public FluentSetterClass setThatIsNotAPropertySetter() {
        // This should not be called.
        throw new IllegalStateException("Should not have been called. No property to set.");
    }
}
