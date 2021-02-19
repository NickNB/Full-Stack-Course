package no.niklas.oeving2.model;

import java.util.ArrayList;

public class Author {
    private long id;
    private String name;
    private Address address;
    private ArrayList<Long> bookIds = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(ArrayList<Long> bookIds) {
        this.bookIds = bookIds;
    }
}
