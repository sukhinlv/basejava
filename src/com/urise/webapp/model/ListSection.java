package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ListSection extends AbstractSection {
    private ArrayList<String> data;

    public ListSection(ArrayList<String> data) {
        this.data = requireNonNull(data, "List section data must not be null");
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = requireNonNull(data, "List section data must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "data=" + data +
                '}';
    }
}