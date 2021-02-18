package no.niklas.oeving2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Book {
    private long id;
    private String title;
    private ArrayList<Long> authorIds = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Long> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(ArrayList<Long> authorIds) {
        this.authorIds = authorIds;
    }
}