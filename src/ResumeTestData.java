import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        var resume = createTestResume();

        System.out.println("���: " + resume.getFullName()+ "\n");

        printContacts(resume);

        for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
            switch (entry.getKey()) {
                case PERSONAL, OBJECTIVE -> printTextSection(entry.getKey().getTitle(), (TextSection) entry.getValue());
                case ACHIEVEMENTS, QUALIFICATIONS ->
                        printListSection(entry.getKey().getTitle(), (ListSection) entry.getValue());
                case EXPERIENCE, EDUCATION ->
                        printOrgSection(entry.getKey().getTitle(), (OrganizationSection) entry.getValue());
            }
        }
    }

    private static void printOrgSection(String title, OrganizationSection value) {
        System.out.println("\n" + title.toUpperCase());
        for (var data : value.getData()) {
            printOrganization(data);
        }
    }

    private static void printOrganization(Organization data) {
        System.out.println("\n" + data.getTitle());
        printNotBlank(data.getLink());
        for (var period : data.getPeriods()) {
            printNotBlank(period.getTitle());
            printNotBlank(period.getDesc());
            // TODO don`t show days, make correct output for endDate null values
            System.out.println(period.getStartDate() + " - " + period.getEndDate());
        }
    }

    private static void printListSection(String title, ListSection value) {
        System.out.println("\n" + title.toUpperCase());
        for (var data : value.getData()) {
            System.out.println(data);
        }
    }

    private static void printTextSection(String title, TextSection value) {
        System.out.println("\n" + title.toUpperCase());
        System.out.println(value.getData());
    }

    private static void printContacts(Resume resume) {
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            System.out.println(entry.getKey().getTitle() + " : " + entry.getValue());
        }
    }

    private static void printNotBlank(String s) {
        if (!s.isBlank()) {
            System.out.println(s);
        }
    }

    private static Resume createTestResume() {
        Resume resume = new Resume("Kislin Grigory");

        Map<ContactType, String> contacts = new HashMap<>();
        contacts.put(ContactType.CELLPHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "skype:grigory.kislin");
        contacts.put(ContactType.MAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/");
        resume.setContacts(contacts);

        var sections = resume.getSections();

        sections.put(SectionType.OBJECTIVE, new TextSection("������� ���������� � �������������� �������� " +
                "�� Java Web � Enterprise �����������"));

        sections.put(SectionType.PERSONAL, new TextSection("������������� ����� ���, ������� ������, ������������, " +
                "��������������. ������ ���� � �����������."));

        // ACHIEVEMENT
        var achievements = new ArrayList<String>();
        achievements.add("����������� ������� � �������� ���������� Java �������� ��� ��������� ����������: ���������� " +
                "�������� �� ����� Spring Cloud/������������, ������� ����������� ����������� ����������� �� Spring Boot, " +
                "������� � ������� ��� �� Play-2, �������������� Spring Boot + Vaadin ������ ��� ����������� DIY ����");
        achievements.add("� 2013 ����: ���������� �������� \"���������� Web ����������\",\"Java Enterprise\", " +
                "\"�������������� maven. ���������������. XML (JAXB/StAX). ��� ������� (JAX-RS/SOAP). ���������" +
                " �������������� (JMS/AKKA)\". ����������� ������ ���������� � ������� ��������. ����� 3500 �����������.");
        achievements.add("���������� ������������� �������������� ��� ������ ��������� ���������� ��������� Wrike. " +
                "���������� � Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievements.add("����������� �������� ���������� � ����������� ���������� ERP ������� River BPM. ���������� " +
                "� 1�, Bonita BPM, CMIS, LDAP. ���������� ���������� ���������� ���������� �� �����: " +
                "Scala/Play/Anorm/JQuery. ���������� SSO �������������� � ����������� ��������� ERP �������, " +
                "���������� CIFS/SMB java �������.");
        achievements.add("���������� c ���� Rich Internet Application ���������� �� ����� ���������� JPA, Spring, " +
                "Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock ��� ���������������� ���������.");
        achievements.add("�������� JavaEE ���������� ��� ����������������� �������������� �����-��������� �������� " +
                "(SOA-base �����������, JAX-WS, JMS, AS Glassfish). ���� ���������� �������� � ���������� � ��������� " +
                "����� ������� ����������� Nagios. ���������� ������ ������� ��� ����������������� � ����������� " +
                "������� �� JMX (Jython/ Django).");
        achievements.add("���������� ���������� �� ������ �������� ���� �������� ��������� ������� ������ (Cyberplat," +
                " Eport, Chronopay, ��������), ������c���(Erip, Osmp) � ���������.");
        sections.put(SectionType.ACHIEVEMENTS, new ListSection(achievements));

        // QUALIFICATIONS
        var qualifications = new ArrayList<String>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.add("DB: PostgreSQL(������������, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, " +
                "MS SQL, HSQLDB");
        qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, " +
                "Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, " +
                "Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.add("Python: Django.");
        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.add("����������: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, " +
                "DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1," +
                " OAuth2, JWT.");
        qualifications.add("�����������: Maven + plugin development, Gradle, ��������� Ngnix");
        qualifications.add("����������������� Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios," +
                " iReport, OpenCmis, Bonita, pgBouncer");
        qualifications.add("�������� ������ � ���� ���������� ��������� ���, SOA, �������� ���������������, " +
                "������������� ��������, UML, ��������������� ����������������");
        qualifications.add("������ �������, ���������� \"upper intermediate\"");
        sections.put(SectionType.QUALIFICATIONS, new ListSection(qualifications));

        // EXPERIENCE
        var organizationData = new ArrayList<Organization>();
        var periods = new ArrayList<Period>();
        periods.add(new Period("����� �������.",
                "��������, ����������� � ���������� Java ������ �������� � ����������.",
                LocalDate.of(2013, 10, 1),
                null));
        var organization = new Organization("Java Online Projects", "http://javaops.ru/", periods);
        organizationData.add(organization);

        periods = new ArrayList<>();
        periods.add(new Period("������� ����������� (backend)",
                "�������������� � ���������� ������ ��������� ���������� ��������� Wrike (Java 8 API, Maven, " +
                        "Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). ������������� ��������������," +
                        " ����������� �� OAuth1, OAuth2, JWT SSO.",
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1)));
        organization = new Organization("Wrike", "https://www.wrike.com/", periods);
        organizationData.add(organization);
        sections.put(SectionType.EXPERIENCE, new OrganizationSection(organizationData));

        // EDUCATION
        organizationData = new ArrayList<>();
        periods = new ArrayList<>();
        periods.add(new Period("'Functional Programming Principles in Scala' by Martin Odersky",
                "",
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1)));
        organization = new Organization("Coursera", "https://www.coursera.org/course/progfun", periods);
        organizationData.add(organization);

        periods = new ArrayList<>();
        periods.add(new Period("����������� (����������� �, �++)",
                "",
                LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1)));
        periods.add(new Period("������� (����������� Fortran, C)",
                "",
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1)));
        organization = new Organization("�����-������������� ������������ ����������������� ����������� " +
                "�������������� ����������, �������� � ������", "http://www.ifmo.ru/", periods);
        organizationData.add(organization);
        sections.put(SectionType.EDUCATION, new OrganizationSection(organizationData));

        return resume;
    }
}