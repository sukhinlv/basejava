package com.urise.webapp.model;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class TextSection extends AbstractSection {
    private String data;

    public TextSection(String data) {
        setData(data);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = requireNonNull(data, "Text section data must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "data='" + data + '\'' +
                '}';
    }
}