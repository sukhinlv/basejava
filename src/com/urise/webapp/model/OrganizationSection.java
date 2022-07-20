package com.urise.webapp.model;

import java.util.HashSet;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class OrganizationSection extends AbstractSection {
    private HashSet<Organization> data;

    public OrganizationSection(HashSet<Organization> data) {
        this.data = requireNonNull(data, "Organization section data must not be null");
    }

    public HashSet<Organization> getData() {
        return data;
    }

    public void setData(HashSet<Organization> data) {
        this.data = requireNonNull(data, "Organization section data must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "data=" + data +
                '}';
    }
}