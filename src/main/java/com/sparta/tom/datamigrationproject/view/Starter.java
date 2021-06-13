package com.sparta.tom.datamigrationproject.view;

import com.sparta.tom.datamigrationproject.controller.CreateEmployeeListFromCSV;
import com.sparta.tom.datamigrationproject.util.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Starter {
    public static final Logger logger= LogManager.getLogger(Starter.class);
    public static void start(){
        Timer.getStartTime();
        CreateEmployeeListFromCSV.createEmployeeList();
        }

        }


