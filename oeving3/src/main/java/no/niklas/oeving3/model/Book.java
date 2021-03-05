package no.niklas.oeving3.model;

import java.util.ArrayList;

public class Book {
    private long id;
    private String title;
    private ArrayList<BookAuthorConnection> connections = new ArrayList<>();

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

    public ArrayList<BookAuthorConnection> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<BookAuthorConnection> connections) {
        this.connections = connections;
    }
}