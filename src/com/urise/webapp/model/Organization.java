package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Organization {
    private String title;
    private String website;
    private ArrayList<Period> periods;

    public Organization(String title, String website, ArrayList<Period> periods) {
        this.title = requireNonNull(title, "Organization title  must not be null");
        this.website = requireNonNull(website, "Website must not be null");
        this.periods = requireNonNull(periods, "Periods must not be null");
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = requireNonNull(website, "Website must not be null");
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
        return Objects.equals(title, that.title) && Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, website);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "title='" + title + '\'' +
                ", link='" + website + '\'' +
                ", periods=" + periods +
                '}';
    }

    public static class Period {
        private String title;
        private String desc;
        private LocalDate startDate;
        private LocalDate endDate;

        public Period(String title, String desc, LocalDate startDate, LocalDate endDate) {
            this.title = requireNonNull(title, "Title must not be null");
            this.desc = desc;
            this.startDate = requireNonNull(startDate, "Start date must not be null");
            this.endDate = requireNonNull(endDate, "End date must not be null");;
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
}