package com.sparta.tom.datamigrationproject.controller;

import java.util.ArrayList;
import java.util.List;

public class BatchCreator {
    public static int i=0;

    static EmployeesDAO employeesDAO= new EmployeesDAO();
    public void createBatch(List<EmployeesDTO> employees){
        Object lock = new Object();
        List<EmployeesDTO> output;
        synchronized (this){
        output=new ArrayList<>();
        for (int j=0; j<1000;j++){
            if ((i+j)>=employees.size()){
                break;
            }
            else{output.add(employees.get((i+j)));}
        }i=i+1000;}
        employeesDAO.addBatch(output);
    }

}
