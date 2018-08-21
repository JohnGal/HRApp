package com.afse.academy.persistence.entities;

import com.afse.academy.persistence.util.JsonDateDeserializer;
import com.afse.academy.persistence.util.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = -7945907733742215456L;

    @Id
    @GeneratedValue(generator = "EMPLOYEE_SEQ")
    @SequenceGenerator(name = "EMPLOYEE_SEQ", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]*$", message = "For first name only letters are allowed, no spaces")
    @Size(min = 1, max = 30, message = "First name must be between 1 and 30 characters!")
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]*$", message = "For last name only letters are allowed, no spaces")
    @Size(min = 1, max = 30, message = "Last name must be between 1 and 30 characters!")
    @Column(name = "lastName")
    private String lastName;

    @NotNull(message = "All employees must belong to a department!")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Valid
    @Embedded
    @AttributeOverride(name = "streetNumber", column = @Column(name = "street_number"))
    private Address address;

    @NotNull
    @Size(max = 40, message = "Email cannot contain more than 40 characters!")
    @Email(message = "Wrong email format")
    private String email;

    @NotNull
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits long")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Digits(integer = 10, fraction = 2)
    @Positive(message = "Salary must be a positive number!")
    @NotNull
    private Double salary;


    @NotNull
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "join_date")
    private Date joinDate;

    @NotNull
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Column(name = "birthdate")
    private Date birthdate;

    @Version
    @Column(name = "DBVERSION", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void updateValues(Employee employee) {
        firstName = employee.firstName;
        lastName = employee.lastName;
        department = employee.department;
        address = employee.address;
        email = employee.email;
        phoneNumber = employee.phoneNumber;
        salary = employee.salary;
        joinDate = employee.joinDate;
        birthdate = employee.birthdate;
    }
}
