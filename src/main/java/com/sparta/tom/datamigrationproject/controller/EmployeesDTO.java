package com.sparta.tom.datamigrationproject.controller;

public class EmployeesDTO {
    int employeeID;
    String title;
    String firstName;
    String middleInitial;
    String lastName;
    String gender;
    String email;
    String dateOfBirth;
    String dateOfJoining;
    int salary;
    public EmployeesDTO(int employeeID, String title,String firstName, String middleInitial, String lastName,String gender,String email,String dateOfBirth, String dateOfJoining, int salary){
        this.employeeID=employeeID;
        this.title=title;
        this.firstName=firstName;
        this.middleInitial=middleInitial;
        this.lastName=lastName;
        this.gender=gender;
        this.email=email;
        this.dateOfBirth=dateOfBirth;
        this.dateOfJoining=dateOfJoining;
        this.salary=salary;
    }
}
