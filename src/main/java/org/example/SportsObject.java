package org.example;

public class SportsObject {
    private final String name;
    private final String subject ;
    private final String address;
    private final Date date;

    public SportsObject(String name, String subject, String address, Date date) {
        this.name = name;
        this.subject = subject;
        this.address = address;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }
}
