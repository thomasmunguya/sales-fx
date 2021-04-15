/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 *Class to handle the business logic for writing to and reading from the Employee.dat file
 */
public class EmployeeFile {
    private static final String workingDirectory = System.getProperty("user.dir");
    private static final File employeeFile = new File(workingDirectory + "\\src\\Employee.dat");
    private static Scanner fileScanner;
    private static ArrayList<Employee> employees = new ArrayList();
    
    public static ArrayList<Employee> readFromFile() throws IOException {
       fileScanner = new Scanner(employeeFile);
       if(employeeFile.exists()) {
           while(fileScanner.hasNextLine()) {
               StringBuilder employeeInfoStringBuilder = new StringBuilder().append(fileScanner.nextLine());
               employeeInfoStringBuilder.deleteCharAt(0);
               employeeInfoStringBuilder.deleteCharAt(employeeInfoStringBuilder.length() - 1);
               StringTokenizer tokenizer = new StringTokenizer(employeeInfoStringBuilder.toString(), ", ");
               while(tokenizer.hasMoreTokens()) {
                   int id = Integer.parseInt(tokenizer.nextToken());
                   String name = tokenizer.nextToken();
                   String city = tokenizer.nextToken();
                   String position = tokenizer.nextToken();
                   employees.add(new Employee(id, name, city, position));
                   break;
               }
               
           }
           return employees;
       }
       return null;
    }
    
    public static void writeToFile(Employee employee) {
        
    }
    
    public static void main(String[] args) {
       System.out.println(workingDirectory);
        try {
            readFromFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        employees.stream().map((e) -> {
            System.out.println("Id: " + e.getId());
            return e;
        }).map((e) -> {
            System.out.println("Name: " + e.getName());
            return e;
        }).map((e) -> {
            System.out.println("City: " + e.getCity());
            return e;
        }).forEachOrdered((e) -> {
            System.out.println("Position: " + e.getPosition());
        });
    }
}
