package org.bear.bookstore.web.vo;

import java.util.Date;

public class Course {

    private Integer id;
    private String name;
    private Date date;

    public Course() {
    }

    public Course(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }
}