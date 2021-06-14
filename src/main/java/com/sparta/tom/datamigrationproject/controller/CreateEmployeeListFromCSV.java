package com.sparta.tom.datamigrationproject.controller;

import com.sparta.tom.datamigrationproject.model.CSVReader;

import java.util.ArrayList;
import java.util.List;

import static com.sparta.tom.datamigrationproject.controller.DuplicateFinder.activeThreads;

public class CreateEmployeeListFromCSV {

    public static void createEmployeeList(String fileName) {
        List<String> resultList;
        resultList= CSVReader.readCSV(fileName);
        EmployeesDAO employees = new EmployeesDAO();
        int i = 1;
        List<EmployeesDTO> employeesList = new ArrayList<>();
        for (String row :
                resultList) {
            if (i == 1) {
                employees.createTable(row);
                i = 2;
            } else {
                String[] employeeDetails = row.split(",");
                int employeeID = Integer.parseInt(employeeDetails[0]);
                String title = employeeDetails[1];
                String firstName = employeeDetails[2];
                String middleInitial = employeeDetails[3];
                String lastName = employeeDetails[4];
                String gender = employeeDetails[5];
                String email = employeeDetails[6];
                String dateOfBirth = employeeDetails[7];
                String dateOfJoining = employeeDetails[8];
                int salary = Integer.parseInt(employeeDetails[9]);
                EmployeesDTO employee = new EmployeesDTO(employeeID, title, firstName
                        , middleInitial, lastName, gender
                        , email, dateOfBirth, dateOfJoining, salary);
                employeesList.add(employee);
            }
        }
        DuplicateFinder entryBundler = new DuplicateFinder();
        entryBundler.removeDuplicates(employeesList);


    }
}
