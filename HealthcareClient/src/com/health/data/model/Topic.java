package com.health.data.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "topic")
public class Topic {
    
    private int id;
    private String title;
    private String body;
    private Account author;

    public Topic(int id, String title, String body, Account author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public Topic() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }
    
    
    
}
