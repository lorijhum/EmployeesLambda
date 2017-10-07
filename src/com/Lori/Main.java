package com.Lori;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {
        Employee john = new Employee("John Smith", 21);
        Employee sam = new Employee("Sam Hunt", 22);
        Employee sally = new Employee("Sally Harris", 28);
        Employee mary = new Employee("Mary Washington", 30);
        Employee joe = new Employee("Joe Jackson", 45);
        Employee jason = new Employee("Jason Jones",48);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(sam);
        employees.add(sally);
        employees.add(mary);
        employees.add(joe);
        employees.add(jason);

        //this passes Employee to the function and the return type is String
        //need the curly braces even though it is only one line of code because it has a return
        //this function doesn't do anything yet, need the following apply logic

        Function<Employee, String> getLastName = (Employee employee)-> {
            return  employee.getName().substring(employee.getName().indexOf(' ') + 1);

        };

        //now a function to get the first name

        Function<Employee, String> getFirstName = (Employee employee)->{
            return employee.getName().substring(0, employee.getName().indexOf(' '));
        };

        String lastName = getLastName.apply(employees.get(2));
        System.out.println(lastName);
        String firstName = getFirstName.apply(employees.get(2));
        System.out.println(firstName);


        //change to lambda:
//        System.out.println("Employees over 30");
//        employees.forEach(employee -> {
//            if(employee.getAge() >= 30){
//                System.out.println(employee.getName());
//                System.out.println(employee.getAge());
//            }
//        });

//        for(Employee employee : employees){
//            if(employee.getAge() >= 30){
//                System.out.println(employee.getName());
//                System.out.println(employee.getAge());
//            }
//        }
        printEmployeesbyAge(employees, "\nEmployees over 30",employee -> employee.getAge() > 30);

        printEmployeesbyAge(employees,"\nEmployees 30 and under", employee -> employee.getAge() <= 30);
        //using an anonymous class rather than the lambda
        printEmployeesbyAge(employees, "\nEmployees under 25", new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() < 25;
            }
        });


        System.out.println("*********************************************");
        employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });

        //using inner Predicate
        //predicate tests a condition, in this case we are passing 10 and the lambda is asking if the number is greater than 15
        //this prints false
        IntPredicate greaterThan15 = i -> i > 15;
        IntPredicate lessThan100 = i -> i < 100;
        System.out.println(greaterThan15.test(10));

        //can also pass a condition, this will print true
        int a = 20;
        System.out.println(greaterThan15.test(a + 5));

        //chaining predicate
        System.out.println(greaterThan15.and(lessThan100).test(50));

//        Random random = new Random();
//        for(int i = 0; i<10; i++){
//            System.out.println(random.nextInt(1000));
//        }

        //now using Supplier and lambda:
        Random random = new Random();
        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
        for(int i=0; i<10; i++){
            System.out.println(randomSupplier.get());
        }
        
        //now printing employee last name with forEach
//        employees.forEach(employee -> {
//            String lastName = employee.getName().substring(employee.getName().indexOf(' ') + 1);
//            System.out.println(lastName);
//
//        });

        //getting random either first or last name using the method and functions
        Random random1 = new Random();
        for(Employee employee: employees){
            if(random1.nextBoolean()){
                System.out.println(getname(getFirstName, employee));
            }else{
                System.out.println(getname(getLastName, employee));
            }
        }

        //function to uppercase the name
        Function<Employee, String> upperCase = employee -> employee.getName().toUpperCase();
        Function<String, String> fName = name -> name.substring(0, name.indexOf(' '));
        //chaining the functions
        Function chainedFunction = upperCase.andThen(fName);
        System.out.println(chainedFunction.apply(employees.get(0)));

        //a function that accepts two parameters
        BiFunction<String, Employee, String> concatAge = (String name, Employee employee) -> {
            return name.concat(" " + employee.getAge());
        };

        String upperName = upperCase.apply(employees.get(0));
        System.out.println(concatAge.apply(upperName, employees.get(0)));

        //now the unary operator
        IntUnaryOperator incBy5 = i -> i+5;
        System.out.println(incBy5.applyAsInt(10));

        //example of Consumer
        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("hello java world");




    }
    //using Predicate

    private static void printEmployeesbyAge(List<Employee> employees, String ageText,
                                            Predicate<Employee> ageCondition){
        System.out.println(ageText);
        System.out.println("=================================================================");
        for(Employee employee : employees){
            if(ageCondition.test(employee)){
                System.out.println(employee.getName());
            }
        }

    }

    private static String getname(Function<Employee, String> getName, Employee employee){
        return getName.apply(employee);
    }
    }

