/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content;
import java.util.*;
import java.io.*;
import javafx.scene.control.Alert;
import salesfx.SalesFX;

/**
 *  Group Member Names: (maximum of two people per group)
 *  Group Member Student Numbers:
 *  Final Project:
 *  Date:
 * 
 *  Class to handle the business logic for writing to and reading from the Employee.dat file
 */
public class EmployeeFile {
    
    private static final File employeeFile = new File("src\\Employee.dat");
    private static Scanner fileScanner;
    private static ArrayList<Employee> employees = new ArrayList();
    
    
    /**
     * Reads
     * @return the list of employees read from the file
     * @throws IOException if the file is not found
     */
    public static ArrayList<Employee> readFromFile() throws IOException {
        
       fileScanner = new Scanner(employeeFile);
       
       if(employeeFile.exists()) {
           while(fileScanner.hasNextLine()) {
               
               StringBuilder employeeInfoStringBuilder = new StringBuilder().append(fileScanner.nextLine());
               employeeInfoStringBuilder.deleteCharAt(0);
               employeeInfoStringBuilder.deleteCharAt(employeeInfoStringBuilder.length() - 1);
               
               StringTokenizer tokenizer = new StringTokenizer(employeeInfoStringBuilder.toString(), ",");
               
               while(tokenizer.hasMoreTokens()) {
                   
                   int id = Integer.parseInt(tokenizer.nextToken().trim());
                   String name = tokenizer.nextToken().trim();
                   String city = tokenizer.nextToken().trim();
                   String position = tokenizer.nextToken().trim();
                   
                   employees.add(new Employee(id, name, city, position));
                   break;
               }
               
           }
           return employees;
       }
       return null;
    }
    
    /**
     * Writes all employees in the employee list to the Employee.dat file
     * 
     */
    public static void writeToFile() {
        FileWriter fileWriter;
        try {
            if(employeeFile.exists()) {
                //delete the old Employees.dat file and create a new one
                employeeFile.delete();
                employeeFile.createNewFile();
                
                fileWriter = new FileWriter(employeeFile);
                
                for(Employee employee: SalesFX.employeeList) {
                    if(employee != null) {
                        fileWriter.write(employee.toString());
                    }
                    
                }
                fileWriter.close();
            }
        }catch(IOException ex) {
            Alert fileloadError = new Alert(Alert.AlertType.ERROR);
            fileloadError.setHeaderText("Cannot write to file");
            fileloadError.setContentText("An error occured when trying to write data to the file.");
            fileloadError.show();
            ex.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
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
