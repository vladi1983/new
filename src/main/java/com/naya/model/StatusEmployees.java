package com.naya.model;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum StatusEmployees {
    JUNIOR(0, 14_000),
    MIDDLE(14_000, 21_000),
    SENIORS(21_000, Integer.MAX_VALUE);

    private final int min;
    private final int max;

    public static StatusEmployees findBySalary(int salary) {
        return Arrays.stream(values())
                .filter(s -> s.min > salary)
                .filter(s -> s.max < salary)
                .findAny()
                .get();
    }

}
