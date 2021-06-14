package com.sparta.tom.datamigrationproject.util;

public class Timer {
    public static double startTime;
    public static double endTime;
    public static void getStartTime(){
        startTime=System.nanoTime();
    }

    public static void getEndTime(){
        endTime=System.nanoTime();
        calculateTimeTaken();
    }

    public static void calculateTimeTaken(){
        System.out.println("Time taken to create and populate table was: "+ (endTime-startTime)/1000000000 + "Seconds");
    }
}
