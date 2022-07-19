package com.urise.webapp.model;

public enum ContactType {
    PHONE("���."),
    CELLPHONE("���.���."),
    HOMEPHONE("���.���."),
    SKYPE("Skype"),
    MAIL("�����"),
    LINKEDIN("������� LinkedIn"),
    GITHUB("������� Github"),
    STACKOVERFLOW("������� Stackoverflow"),
    HOMEPAGE("�������� ��������");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}