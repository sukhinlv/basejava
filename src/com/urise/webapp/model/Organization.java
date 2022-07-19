package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Organization {
    private String title;
    private String link;
    private ArrayList<Period> periods;

    public Organization(String title, String link, ArrayList<Period> periods) {
        setTitle(title);
        setLink(link);
        setPeriods(periods);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = requireNonNull(link, "Link must not be null");
    }

    public ArrayList<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(ArrayList<Period> periods) {
        this.periods = requireNonNull(periods, "Periods must not be null");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = requireNonNull(title, "Organization title  must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(title, that.title) && Objects.equals(link, that.link) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, link, periods);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", periods=" + periods +
                '}';
    }
}