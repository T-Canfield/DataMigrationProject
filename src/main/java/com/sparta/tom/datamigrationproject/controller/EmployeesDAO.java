package com.sparta.tom.datamigrationproject.controller;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class EmployeesDAO {
    int i=0;
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
    private final String entryExistsQuery="SELECT * FROM employees WHERE employeeId=?";
    private Connection connectToDatabase() {
        try {
            properties.load(new FileReader("resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty("username"), properties.getProperty("password"));
            // connection = DriverManager.getConnection(URL, System.getenv("javadbusername"), System.getenv("javadbpassword"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
    public void createTable(String firstRow){
        String[] columnNames= firstRow.split(",");
        String columnsFormattedForSQL="";

        for (String column:
             columnNames) {
            System.out.println(column);
        }
        for (String column:
             columnNames) {
            columnsFormattedForSQL+=('"'+column+'"' + " VARCHAR(30),");
        }
        columnsFormattedForSQL=columnsFormattedForSQL.substring(0, (columnsFormattedForSQL.length()-1));
        System.out.println(columnsFormattedForSQL);
        try (PreparedStatement statement= connectToDatabase().prepareStatement(createNewTable);
        PreparedStatement statement1 = connectToDatabase().prepareStatement(dropTable)){
            statement1.executeUpdate();
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addEntry(EmployeesDTO employee) {
       if (entryExists(employee)) {
         //   i++;
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
                e.printStackTrace();
            }
        }
    }
    public boolean entryExists(EmployeesDTO employee){

        try (PreparedStatement statement = connectToDatabase().prepareStatement(entryExistsQuery)){
            statement.setInt(1,employee.employeeID);
            ResultSet resultSet= statement.executeQuery();
            if (resultSet.next()==false){return false;}
        } catch (SQLException e) {
            e.printStackTrace();}

        return true;
    }

}
