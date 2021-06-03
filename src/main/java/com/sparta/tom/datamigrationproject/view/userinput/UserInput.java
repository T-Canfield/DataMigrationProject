package com.sparta.tom.datamigrationproject.view.userinput;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class UserInput {
    static Scanner userInput= new Scanner(System.in);
    public static String getUsername(){
        System.out.println("Enter your username:");
        String username = userInput.nextLine();
        return username;
    }
    public static String getPassword()throws IOException {
        Console console = System.console();
        char[] passwordAsArray = console.readPassword("Enter your password:");
        String password= passwordAsArray.toString();
        return password;
    }
}
