package com.cubtp.vo;

public class BookSubject {
    private Integer id;

    private String bookSubject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookSubject() {
        return bookSubject;
    }

    public void setBookSubject(String bookSubject) {
        this.bookSubject = bookSubject == null ? null : bookSubject.trim();
    }
}