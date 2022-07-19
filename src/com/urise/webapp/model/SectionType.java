package com.urise.webapp.model;

public enum SectionType {
    PERSONAL("������ ��������"),
    OBJECTIVE("�������"),
    ACHIEVEMENTS("����������"),
    QUALIFICATIONS("������������"),
    EXPERIENCE("���� ������"),
    EDUCATION("�����������");

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}