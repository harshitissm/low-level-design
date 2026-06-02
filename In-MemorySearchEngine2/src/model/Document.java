package model;

import java.util.Date;

public class Document {
    private String name;
    private String content;
    private String author;
    private Date publishDate;
    private Date lastUpdatedDate;
    private Date lastSearchedDate;

    public Document(String name, String content, String author) {
        this.name = name;
        this.content = content;
        this.author = author;
        this.publishDate = new Date();
        this.lastUpdatedDate = new Date();
        this.lastSearchedDate = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Date getLastSearchedDate() {
        return lastSearchedDate;
    }

    public void setLastSearchedDate(Date lastSearchedDate) {
        this.lastSearchedDate = lastSearchedDate;
    }
}