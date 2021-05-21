package com.naya.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String nameCompany;
    private int salary;
}
