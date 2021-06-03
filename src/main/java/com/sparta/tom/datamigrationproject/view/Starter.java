package com.sparta.tom.datamigrationproject.view;

import com.sparta.tom.datamigrationproject.controller.EmployeesDAO;
import com.sparta.tom.datamigrationproject.controller.EmployeesDTO;
import com.sparta.tom.datamigrationproject.model.CSVReader;
import java.util.List;

public class Starter {
    public static void start(){
        List<String> resultList;
        resultList=CSVReader.readCSV();
        EmployeesDAO employees= new EmployeesDAO();
        int i=1;
        for (String row:
             resultList) {
            if(i==1){
                employees.createTable(row);
                i=2;
            }
            else{
                String[] employeeDetails = row.split(",");
                int employeeID=Integer.parseInt(employeeDetails[0]);
                String title=employeeDetails[1];
                String firstName=employeeDetails[2];
                String middleInitial=employeeDetails[3];
                String lastName=employeeDetails[4];
                String gender=employeeDetails[5];
                String email=employeeDetails[6];
                String dateOfBirth=employeeDetails[7];
                String dateOfJoining=employeeDetails[8];
                int salary=Integer.parseInt(employeeDetails[9]);
                EmployeesDTO employee = new EmployeesDTO(employeeID,title,firstName
                        ,middleInitial,lastName,gender
                        ,email,dateOfBirth,dateOfJoining,salary);
                employees.addEntry(employee);
            }

        }



        }
    }

