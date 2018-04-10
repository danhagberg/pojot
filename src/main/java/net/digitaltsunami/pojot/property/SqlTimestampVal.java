package net.digitaltsunami.pojot.property;


import java.sql.Timestamp;
import java.time.Instant;

public class SqlTimestampVal implements PropertyValue {
    @Override
    public Timestamp getValue() {
        return new Timestamp(Instant.now().toEpochMilli());
    }

    @Override
    public Timestamp getSmallValue() {
        return new Timestamp(Instant.now().minusSeconds(200_000L).toEpochMilli());
    }

    @Override
    public Timestamp getLargeValue() {
        return new Timestamp(Instant.now().plusSeconds(200_000L).toEpochMilli());
    }

    /**
     * Return a null value for the type.
     *
     * @return null unconditionally
     */
    @Override
    public Object getDefaultValue() {
        return null;
    }
}
