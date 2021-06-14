package com.sparta.tom.datamigrationproject.view;

import com.sparta.tom.datamigrationproject.controller.CreateEmployeeListFromCSV;
import com.sparta.tom.datamigrationproject.util.Timer;
import com.sparta.tom.datamigrationproject.util.UserInput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Starter {
    public static final Logger logger= LogManager.getLogger(Starter.class);
    static UserInput userInput = new UserInput();
    public static void start(){
        String fileName= userInput.getFileType();
        Timer.getStartTime();
        CreateEmployeeListFromCSV.createEmployeeList(fileName);
        }

        }


