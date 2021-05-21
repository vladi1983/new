package com.naya.util;

import com.naya.model.Company;
import com.naya.model.Employee;
import com.naya.model.EmployeeWithArray;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LabsTest {
    List<Employee> employees;
    List<EmployeeWithArray> employeeWithArrays;
    List<Company> companyList = new ArrayList<>();

    @Before
    public void initEmployees() {
        employees = List.of(
                Employee.builder()
                        .id(1)
                        .firstName("Daniel")
                        .lastName("Levi")
                        .gender("man")
                        .nameCompany("Naya")
                        .salary(24000)
                        .build(),
                Employee.builder()
                        .id(2)
                        .firstName("Anat")
                        .lastName("Berkovitch")
                        .gender("girl")
                        .nameCompany("Naya")
                        .salary(15000)
                        .build(),
                Employee.builder()
                        .id(3)
                        .firstName("Ivan")
                        .lastName("Ivanov")
                        .gender("man")
                        .nameCompany("Epam")
                        .salary(5500)
                        .build(),
                Employee.builder()
                        .id(4)
                        .firstName("Mental")
                        .lastName("Cohen")
                        .gender("girl")
                        .nameCompany("Intel")
                        .salary(30000)
                        .build()
        );

        employeeWithArrays = List.of(
                EmployeeWithArray.builder()
                        .name("Bob")
                        .salary(new int[]{10000, 20000, 30000, 40000, 50000,
                                6000, 7000, 8000, 9000, 10000, 11000, 12000})
                        .build(),
                EmployeeWithArray.builder()
                        .name("John")
                        .salary(new int[]{20000, 30000, 60000, 50000, 6000,
                                7000, 8000, 9000, 10000, 10000, 11000, 12000})
                        .build()
        );

             companyList.add(new Company(employees));
    }

    @Test
    public void getSumSalaryOfEmployees() {
        int sumSalaryOfEmployees = new Labs().getSumSalaryOfEmployees(employees);
        assertEquals(sumSalaryOfEmployees, 24000 + 15000 + 5500 + 30000);
    }

    @Test
    public void getMapEmployeeVsSalary() {
        Map<String, Integer> mapEmployeeVsSalary = new Labs().getMapEmployeeVsSalary(employeeWithArrays);
        Integer bob = mapEmployeeVsSalary.get("Bob");
        Integer john = mapEmployeeVsSalary.get("John");
        assertEquals((int) john,
                (20000 + 30000 + 60000 + 50000 + 6000 + 7000 + 8000 + 9000 + 10000 + 10000 + 11000 + 12000));
        assertEquals((int) bob,
                (10000 + 20000 + 30000 + 40000 + 50000 + 6000 + 7000 + 8000 + 9000 + 10000 + 11000 + 12000));
    }

    @Test
    public void getCountOfWords() {
        int countOfWords = new Labs()
                .getCountOfWords("src/main/resources/lib/CountOfWords.txt");
        assertEquals(countOfWords, 10);
    }

    @Test
    public void getAverageCountOfWords() {
        int averageCountOfWords = new Labs()
                .getAverageCountOfWords("src/main/resources/lib/AverageCountOfWords.txt");
        assertEquals(averageCountOfWords, 1 + (5 + 3 + 6 + 7 + 3 + 7) / 6);
    }

    @Test
    public void getNameOfEmployeesSortedByLength() {
        List<String> nameOfEmployeesSortedByLength = new Labs().getNameOfEmployeesSortedByLength(employees);
        assertEquals(nameOfEmployeesSortedByLength.get(0), "ANAT");
        assertEquals(nameOfEmployeesSortedByLength.get(1), "IVAN");
        assertEquals(nameOfEmployeesSortedByLength.get(2), "DANIEL");
        assertEquals(nameOfEmployeesSortedByLength.get(3), "MENTAL");
    }

    @Test
    public void getNameSortedByMaxSalary() {
        List<String> nameSortedByMaxSalary = new Labs().getNameSortedByMaxSalary(employees);
        assertEquals(nameSortedByMaxSalary.get(0), "Mental");
        assertEquals(nameSortedByMaxSalary.get(1), "Daniel");
        assertEquals(nameSortedByMaxSalary.get(2), "Anat");
        assertEquals(nameSortedByMaxSalary.get(3), "Ivan");
    }

    @Test
    public void isManEmployeesMoreExpensive() {
        boolean manEmployeesMoreExpensive = new Labs().isManEmployeesMoreExpensive(employees);
        assertEquals(manEmployeesMoreExpensive, 29500 > 45000);
    }

    @Test
    public void getMapOfNameCompanyVsEmployees() {
        Map<String, List<Employee>> mapOfEmployee = new Labs().getMapOfNameCompanyVsEmployees(employees);
        List<Employee> naya = mapOfEmployee.get("Naya");
        assertEquals(naya.size(), 2);
        List<Employee> intel = mapOfEmployee.get("Intel");
        assertEquals(intel.size(), 1);
        List<Employee> epam = mapOfEmployee.get("Epam");
        assertEquals(epam.size(), 1);
    }

    @Test
    public void getMapOfNameCompanyVsNumberOfWorkers() {
        Map<String, List<Employee>> mapOfNameCompanyVsEmloyees =
                new Labs().getMapOfNameCompanyVsEmployees(employees);
        Map<String, Integer> mapOfNameCompanyVsNumberOfWorkers =
                new Labs().getMapOfNameCompanyVsNumberOfWorkers(mapOfNameCompanyVsEmloyees);
        Integer naya = mapOfNameCompanyVsNumberOfWorkers.get("Naya");
        assertEquals((int) naya, 2);
        Integer intel = mapOfNameCompanyVsNumberOfWorkers.get("Intel");
        assertEquals((int) intel, 1);
        Integer epam = mapOfNameCompanyVsNumberOfWorkers.get("Epam");
        assertEquals((int) epam, 1);

    }

    @Test
    public void companyProfile() {
        new Labs().companyProfile(companyList);
    }

    @Test
    public void returnMostPopularWords() {
        new Labs().returnMostPopularWords(List.of("Hello, world, my name, yes, ok, ok, friend, mock, hello"));
    }


}