package com.afse.academy.persistence.entities;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;

@Entity
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = -8807761544660473872L;
    @Id
    @GeneratedValue(generator = "DEPARTMENT_SEQ")
    @SequenceGenerator(name = "DEPARTMENT_SEQ", sequenceName = "DEPARTMENT_SEQ", allocationSize = 1)
    private Long id;
    private String name;

    @Valid
    @Embedded
    @AttributeOverride(name = "streetNumber", column = @Column(name = "street_number"))
    private Address address;

    @Version
    @Column(name = "DBVERSION", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void updateValues(Department department) {
        name = department.name;
        address = department.address;
    }
}
