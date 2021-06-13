package com.sparta.tom.datamigrationproject.controller;

import com.sparta.tom.datamigrationproject.util.Timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicateFinder {
    EmployeesDAO employeesDAO= new EmployeesDAO();
    static int activeThreads=0;
    public void removeDuplicates(List<EmployeesDTO> employeesList) {
        Object lock = new Object();
        List<EmployeesDTO> batches = new ArrayList<>();

        while (employeesList.size() > 0) {


            //while (batches.size() < 1000) {
            for (int i = 0; i < batches.size() - 1; i++) {
                if (employeesList.get(0).getEmployeeID() == batches.get(i).getEmployeeID()) {
                    employeesDAO.addFailedEntryToList(employeesList.get(0));
                    employeesList.remove(0);
                    break;
                }
            }
            batches.add(employeesList.get(0));
            employeesList.remove(0);
            if (employeesList.size() == 0) {
                break;
            }
        }
        employeesDAO.writeFailedEntrysTocsv();
        BatchCreator batchCreator=new BatchCreator();
        for(int i=0;i<((batches.size()/1000)+1);i++) {
            activeThreads++;
            Thread thread = new Thread(() -> {
                batchCreator.createBatch(batches);
            });
            thread.start();
        }
        while (activeThreads>0){
            Thread.onSpinWait();
        }
        Timer.getEndTime();
    }



    }

