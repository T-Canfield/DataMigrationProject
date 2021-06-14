package com.sparta.tom.datamigrationproject.util;

import java.util.Scanner;

public class UserInput {
    Scanner userInput = new Scanner(System.in);
    String fileName="";
    public String getFileType() {
        System.out.println("What file would you like to use:\n" +
                "1. Small\n2. Large");
        int selection = userInput.nextInt();
        if (selection==1){fileName="resources/employees.csv";}
        else if (selection==2){fileName="resources/EmployeeRecordsLarge.csv";}
        else{
            System.out.println("Please enter an option on the list");
            getFileType();
        }
        return fileName;
    }
}
