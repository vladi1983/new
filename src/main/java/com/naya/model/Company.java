package com.naya.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company {
    @Singular
    private List<Employee> employees;
    private StatusEmployees statusEmployees;

    public Company(List<Employee> list) {
        this.employees = list;
    }
}
