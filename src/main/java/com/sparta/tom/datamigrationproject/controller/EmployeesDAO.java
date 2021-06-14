package com.sparta.tom.datamigrationproject.controller;

import com.sparta.tom.datamigrationproject.model.CSVWriter;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import static com.sparta.tom.datamigrationproject.controller.DuplicateFinder.activeThreads;
import static com.sparta.tom.datamigrationproject.view.Starter.logger;


public class EmployeesDAO {
    List<EmployeesDTO> failedAdditionsToTable= new ArrayList<>();
    private final String URL = "jdbc:mysql://localhost:3306/mylocal?serverTimerzone=GMT";
    private Connection connection;
    private static Properties properties = new Properties();
    private final String createNewTable="CREATE TABLE employees (employeeId INT PRIMARY KEY,\n" +
            "            namePrefix VARCHAR(5), firstName VARCHAR(30),\n" +
            "            middleInitial VARCHAR(1), lastName VARCHAR(30),\n" +
            "            gender VARCHAR(1), Email VARCHAR(50), dateOfBirth VARCHAR(10), \n" +
            "            dateOfJoining VARCHAR(10), salary INT);";
    private final String dropTable= "DROP TABLE IF EXISTS employees";
    private final String addEntryToTable="INSERT INTO employees VALUES(?,?,?,?,?,?,?,?,?,?)";
    private final String addBatchToTable="INSERT INTO employees VALUES";
    private final String entryExistsQuery="SELECT * FROM employees WHERE employeeId=?";
    private Connection connectToDatabase() {
        try {
            properties.load(new FileReader("resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }

        return connection;
    }
    public void createTable(String firstRow){
        try (PreparedStatement statement= connectToDatabase().prepareStatement(createNewTable);
        PreparedStatement statement1 = connectToDatabase().prepareStatement(dropTable)){
            statement1.executeUpdate();
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        catch (Exception e) {
            logger.error(e);
        }
    }
    public void addEntry(EmployeesDTO employee) {
       if (entryExists(employee)) {
           addFailedEntryToList(employee);
        } else {
            try(PreparedStatement statement = connectToDatabase().prepareStatement(addEntryToTable)){
                statement.setInt(1, employee.employeeID);
                statement.setInt(10, employee.salary);
                statement.setString(2, employee.title);
                statement.setString(3, employee.firstName);
                statement.setString(4, employee.middleInitial);
                statement.setString(5, employee.lastName);
                statement.setString(6, employee.gender);
                statement.setString(7, employee.email);
                statement.setString(8, employee.dateOfBirth);
                statement.setString(9, employee.dateOfJoining);
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
    public void addBatch(List<EmployeesDTO> employeeList){
        int i=0;
        EmployeesDTO employee;
            try (PreparedStatement sqlStatement = connectToDatabase().prepareStatement(addEntryToTable)){
                while (i<(employeeList.size())) {
                    employee =  employeeList.get(i++);
                        sqlStatement.setInt(1, employee.employeeID);
                        sqlStatement.setInt(10, employee.salary);
                        sqlStatement.setString(2, employee.title);
                        sqlStatement.setString(3, employee.firstName);
                        sqlStatement.setString(4, employee.middleInitial);
                        sqlStatement.setString(5, employee.lastName);
                        sqlStatement.setString(6, employee.gender);
                        sqlStatement.setString(7, employee.email);
                        sqlStatement.setString(8, employee.dateOfBirth);
                        sqlStatement.setString(9, employee.dateOfJoining);
                        sqlStatement.addBatch();
                        sqlStatement.clearParameters();

                }
                sqlStatement.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            activeThreads--;
        }


    public boolean entryExists(EmployeesDTO employee){

        try (PreparedStatement statement = connectToDatabase().prepareStatement(entryExistsQuery)){
            statement.setInt(1,employee.employeeID);
            ResultSet resultSet= statement.executeQuery();
            if (resultSet.next()==false){return false;}
        } catch (SQLException e) {
            logger.error(e);}

        return true;
    }
    public void writeFailedEntrysTocsv(){
        CSVWriter csvWriter= new CSVWriter();
        csvWriter.addEntryToFailedList(failedAdditionsToTable);
    }
    public void addFailedEntryToList(EmployeesDTO employee){
        failedAdditionsToTable.add(employee);
    }
}

