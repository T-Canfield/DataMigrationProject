package com.sparta.tom.datamigrationproject.model;

import com.sparta.tom.datamigrationproject.controller.EmployeesDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter  {





    public void addEntryToFailedList(List<EmployeesDTO> employees){
        try (FileWriter fileWriter=new FileWriter("resources/FailedAdditions.csv");
             BufferedWriter writer = new BufferedWriter(fileWriter)){
            for (EmployeesDTO employee:
                 employees) {
        writer.append(employee.getEmployeeID()+",");
        writer.append(employee.getTitle()+",");
        writer.append(employee.getFirstName()+",");
        writer.append(employee.getMiddleInitial()+",");
        writer.append(employee.getLastName()+",");
        writer.append(employee.getGender()+",");
        writer.append(employee.getEmail()+",");
        writer.append(employee.getDateOfBirth()+",");
        writer.append(employee.getDateOfJoining()+",");
        writer.append((employee.getSalary())+"\n");}
    } catch (IOException e) {
        e.printStackTrace();
    }

    }
}
