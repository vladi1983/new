package com.naya.util;

import com.naya.model.Company;
import com.naya.model.Employee;
import com.naya.model.EmployeeWithArray;
import com.naya.model.StatusEmployees;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Labs {

    //todo: Write method which receives list of employees and returns sum of their salaries
    public int getSumSalaryOfEmployees(List<Employee> employees) {
        return employees.stream().mapToInt(Employee::getSalary).sum();
    }

    //todo: Write method which will calculate salaries of all employees per year (salary in array int)
    public Map<String, Integer> getMapEmployeeVsSalary(List<EmployeeWithArray> list) {
        Map<String, Integer> map = new HashMap<>();
        list.forEach(x -> map.put(x.getName(), Arrays.stream(x.getSalary()).sum()));
        return map;
    }

    //todo: write method which will receive a file and return number of words
    @SneakyThrows
    public int getCountOfWords(String nameFile) {
        return (int) Files.lines(Paths.get(nameFile))
                .flatMap(s -> Stream.of(s.split("[ ,.!?\r\n]")))
                .filter(s -> !s.isEmpty())
                .count();
    }

    //todo: write method which will receive a file and return average length of the word
    @SneakyThrows
    public int getAverageCountOfWords(String nameFile) {
        return (int) Files.lines(Paths.get(nameFile))
                .mapToInt(String::length)
                .average()
                .getAsDouble();
    }

    //todo: write method which will receive list of employees and will return
    // List of their names(UPPERCASED) sorted by length
    public List<String> getNameOfEmployeesSortedByLength(List<Employee> list) {
        return list.stream()
                .map(Employee::getFirstName)
                .map(String::toUpperCase)
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());
    }

    //todo: write method which will receive list of employees and will return List of their name sorted by
    // salary of employee, starting from most expensive employee
    public List<String> getNameSortedByMaxSalary(List<Employee> list) {
        return list.stream()
                .sorted(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder()))
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
    }

    //todo: write method which will return list of employees and will calculate
    // sum of man salaries against sum of woman salaries
    public boolean isManEmployeesMoreExpensive(List<Employee> list) {
        int girl = list.stream()
                .filter(x -> x.getGender().equals("girl"))
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
        int man = list.stream()
                .filter(x -> x.getGender().equals("man"))
                .map(Employee::getSalary)
                .mapToInt(Integer::intValue)
                .sum();
        return man > girl;
    }

    //todo: write method which will receive List of Employees and will return map<CompanyName,List<Employee>>
    // Yes each employee has property: String companyName
    public Map<String, List<Employee>> getMapOfNameCompanyVsEmployees(List<Employee> list) {
        return list.stream()
                .collect(groupingBy(Employee::getNameCompany,
                        mapping(Function.identity(), Collectors.toList())));
    }

    //todo: write method which receive map from previous method and returns Map<CompanyName, NumberOfWorkers>
    // Test it: Print name of each company against number of workers
    public Map<String, Integer> getMapOfNameCompanyVsNumberOfWorkers(Map<String, List<Employee>> map) {
        Map<String, Integer> newMap = new HashMap<>();
        map.forEach((x, y) -> newMap.put(x, y.size()));
        return newMap;
    }

    //todo: there are three categories of employees(Juniors - salary <14000,middle - salary<21000, seniors > 21000)
    // Create appropriate enum
    // Write method which will receive list of companies and will categorize it by setting companyProfile
    // property according to the most employee's seniorities
    public void companyProfile(List<Company> listCompanies) {
        listCompanies.forEach(company -> company.setStatusEmployees(sortedByStatusEmployees(company)));
    }

    private StatusEmployees sortedByStatusEmployees(Company company) {
        Map<StatusEmployees, Long> map = countByStatusEmployee(company);
        return map.entrySet().stream()
                .max((Map.Entry.comparingByValue()))
                .get().getKey();

    }

    private Map<StatusEmployees, Long> countByStatusEmployee(Company company) {
        return company.getEmployees().stream()
                .collect(Collectors
                        .groupingBy(employee -> StatusEmployees.findBySalary(employee.getSalary()),
                                Collectors.counting()));
    }

    // todo: You class Purchase("name", Product) Class Product("name",price)
    //  Write method which will build Map<String,Integer> where each name will mapped
    //  to total money spent by this specific customer name

    // todo: Write method which will return Map<CustomerName, Integer> - how many purchases did he made>

    // todo: Write method which will receive List of employees and will return Map<String,Integer> - name against salary
    //  Test what will happen if there are more than one employee with the same name

    // todo: write method which will return a Stream of primary numbers Develop
    //  service which will return a list of prime numbers
    //  public List<Integer> getPrimeNumbers(int amount)

    // todo: you have Employee class, you have product class(name,price), you have sale class(employee, product).
    //  Write method which will receive list of sales and will return best employee(max total income to company)
    //  What if there are more than one best employee?

    //todo: write method which will receive a text and will return the most popular words
    public void returnMostPopularWords(List<String> list) {
//        list.stream()
//                .filter(word -> !word.trim().isEmpty())
//                .map(String::toLowerCase)
//                .collect(groupingBy(s -> s, counting())).entrySet().stream()
//                .collect(groupingBy(Map.Entry::getValue,
//                        mapping(Map.Entry::getKey, toList()))).entrySet().stream()
//                .max(Comparator.comparingLong(Map.Entry::getKey))
//                .ifPresent(e -> {
//                    System.out.println(e.getKey() + "-" + e.getValue());
//                });

//        Map<String, Long> collect = list.stream()
//                .filter(word -> !word.trim().isEmpty())
//                .map(String::toLowerCase)
//                .collect(groupingBy(s -> s, counting()));
//        Collection<Long> values = collect.values();
//        System.out.println("values = " + values);
//        list.entrySet().stream() // {monkey=3, dog=2, giraffe=3}
//                .collect(groupingBy(Map.Entry::getValue, mapping(Map.Entry::getKey, toList()))).entrySet().stream() // reverse map: {3=[monkey, giraffe], 2=[dog]}
//                .max(Comparator.comparingLong(Map.Entry::getKey)) // maxCnt and all words with it: 3=[monkey, giraffe]
//                .ifPresent(e -> {
//                    System.out.println("Words that appear " + e.getKey() + " times: " + e.getValue());
//                });
    }

    public static void printBlackFridaySorted(int startYear, int endYear) {

        LocalDate startDate = LocalDate.of(startYear, 1, 13);
        LocalDate endDate = LocalDate.of(endYear, 1, 13);

        Map<Integer, Long> map = Stream.iterate(startDate, localDate -> localDate.plusMonths(1))
                .filter(localDate -> localDate.getDayOfWeek() == DayOfWeek.FRIDAY)
                .takeWhile(localDate -> localDate.isBefore(endDate))
                .collect(groupingBy(LocalDate::getYear, counting()));

        map.entrySet().stream().sorted((o1, o2) -> (int) (o2.getValue() - o1.getValue()))
                .forEach(System.out::println);
//        IntStream.range(startYear,endYear)


    }

    //todo: write method which will receive list of employees and
    // will return string with their names separated by comma:
    // ArrayList<Employee>empl = new ArrayList<>();
    // empl.add(new Employee("Name"));
    // ..................................................
    // Output: String which contains text: "Hirsh, Avishay,Hadas"
}
