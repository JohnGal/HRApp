package com.afse.academy.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Email implements Serializable {

    private static final long serialVersionUID = 4044220632089919705L;

    @Id
    @GeneratedValue(generator = "EMAIL_SEQ")
    @SequenceGenerator(name = "EMAIL_SEQ", sequenceName = "EMAIL_SEQ", allocationSize = 1)
    private Long id;

    private String message;

    @Version
    @Column(name = "DBVERSION", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
