package com.urise.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

import static java.util.Objects.requireNonNull;

public class Resume implements Comparable<Resume>, Serializable {

    @Serial
    private static final long serialVersionUID = 1919842806405440174L;
    // Unique identifier
    private final String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume(String uuid, String fullName) {
        this.uuid = requireNonNull(uuid, "Resume uuid must not be null");
        this.fullName = requireNonNull(fullName, "Full name must not be null");
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<ContactType, String> contacts) {
        requireNonNull(contacts, "Contacts must not be null");
        this.contacts.clear();
        this.contacts.putAll(contacts);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = requireNonNull(fullName, "Full name must not be null");
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public void setSections(Map<SectionType, AbstractSection> sections) {
        requireNonNull(sections, "Sections must not be null");
        this.sections.clear();
        this.sections.putAll(sections);
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.getUuid());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }
}