package com.finki.eimt.EmployeeManager.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    @Column
    public String name;
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

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
}
