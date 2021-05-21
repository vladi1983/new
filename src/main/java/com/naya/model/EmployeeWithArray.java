package com.naya.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class EmployeeWithArray {
    private String name;
    private int[] salary = new int[12];
}
