package io.critsu.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Attribute {

    private String attribute;
    private String value;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("attribute", attribute)
                .append("value", value)
                .toString();
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
