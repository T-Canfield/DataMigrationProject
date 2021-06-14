package com.sparta.tom.datamigrationproject.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<String> readCSV(String fileName){
        String row;
        List<String> resultList= new ArrayList<>();
        try(BufferedReader csvReader= new BufferedReader(new FileReader(fileName))) {

            while( (row= csvReader.readLine())!=null){
                resultList.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
