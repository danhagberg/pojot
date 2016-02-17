package net.digitaltunami.pojot.testsubject;

/**
 *
 */
public class BadToStringClass {
    public String string1;
    public String string2;

    @Override
    public String toString() {
        return new StringBuilder("BadToStringClass: ")
                .append("Length of string1=")
                .append(string1.length())
                .append(" Length of string2=")
                .append(string2.length())
                .toString();
    }
}
