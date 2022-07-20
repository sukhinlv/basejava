package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Period {
    private String title;
    private String desc;
    private LocalDate startDate;
    private LocalDate endDate;

    public Period(String title, String desc, LocalDate startDate, LocalDate endDate) {
        this.title = requireNonNull(title, "Title must not be null");
        this.desc = desc;
        this.startDate = requireNonNull(startDate, "Start date must not be null");
        this.endDate = endDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = requireNonNull(startDate, "Start date must not be null");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = requireNonNull(title, "Title must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(title, period.title) && Objects.equals(desc, period.desc) &&
                Objects.equals(startDate, period.startDate) && Objects.equals(endDate, period.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, desc, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Period{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}