/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesfx;

import content.Employee;
import content.EmployeeFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Alert.AlertType;
/**
 *
 * @author MTH
 */
public class SalesFX extends Application {
     @FXML
    private VBox vBoxEmployeeInfo;

    @FXML
    private Label labelEmployeeInfo;

    @FXML
    private Label labelId;

    @FXML
    private TextField textFieldId;

    @FXML
    private Label labelName;

    @FXML
    private TextField textFieldName;

    @FXML
    private Label labelCity;

    @FXML
    private TextField textFieldCity;

    @FXML
    private Label labelPosition;

    @FXML
    private TextField textFieldPosition;

    @FXML
    private HBox hBoxNavigationButtons;

    @FXML
    private Button buttonFirst;

    @FXML
    private Button buttonPrevious;

    @FXML
    private Button buttonNext;

    @FXML
    private Button buttonLast;

    @FXML
    private VBox hBoxModifications;

    @FXML
    private Button buttonModifyEmployee;

    @FXML
    private Button buttonAddEmployee;

    @FXML
    private Button buttonDeleteEmployee;

    @FXML
    private Button buttonSearch;

    @FXML
    private Label labelSalesFX;
    
    private final static EmployeeFile EMPLOYEE_FILE = new EmployeeFile();
    private ArrayList<Employee> employees;
    private static int currentIndex = 0;
    private final Alert invalidInput  = new Alert(AlertType.ERROR);

    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    /**
     * Loads the employees list with data from the Employee.dat file
     */
    private void loadList() {
        
    }
    
    /**
     * 
     * @param index the index of the employee in the list
     */
    private Employee navigateEmployeesList(int index) {
        return employees.get(index);
    }
    
    /**
     * Disables and enables the navigation buttons depending on the index at which the user is in the employees list
     */
    private void disableOrEnableNavButtons() {
        //if the index is 0 disable the first and previous navigation buttons
        buttonPrevious.disabledProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                if(currentIndex == 0) {
                    buttonPrevious.setDisable(true);
                    buttonFirst.setDisable(true);
                }
                else {
                    buttonPrevious.setDisable(false);
                    buttonFirst.setDisable(false);
                }
            }
        });
        
        //if the index is the last index in the employee list disable the next and last navigation buttons
        buttonPrevious.disabledProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                if(currentIndex == employees.size() - 1) {
                    buttonNext.setDisable(true);
                    buttonLast.setDisable(true);
                }
                else {
                    buttonNext.setDisable(false);
                    buttonLast.setDisable(false);
                }
            }
        });
        
    }
    
    /**
     * Modifies the information of an employee
     */
    @FXML
    public void modifyEmployee() {
        //enable the employee information text fields during a modification
        textFieldId.setEditable(true);
        textFieldName.setEditable(true);
        textFieldCity.setEditable(true);
        textFieldPosition.setEditable(true);
        
        //disable the add employee button during a modification and 
        //change the modify employee button's name to update employee during a modification
        buttonAddEmployee.setDisable(true);
        buttonModifyEmployee.setText("Update Employee");
        
    }
    
    
    /**
     * Updates the information of an employee
     * @param id the new ID
     * @param name the new name
     * @param city the new city
     * @param position the new position
     */
    @FXML
    public void updateEmployee(int id, String name, String city, String position) {
        Alert confirmUpdate = new Alert(AlertType.CONFIRMATION);
        confirmUpdate.setHeaderText("Confirm Update");
        confirmUpdate.setContentText("Are you sure you want to perform an update of this employee's information?");
        Optional<ButtonType> choice = confirmUpdate.showAndWait();
        
        if(choice.get() == ButtonType.OK) {
           
            employees.remove(currentIndex);
            employees.add(currentIndex, new Employee(id, name, city, position));
            
            Alert successfulUpdate = new Alert(AlertType.INFORMATION);
            successfulUpdate.setHeaderText("Update Successful");
            confirmUpdate.setContentText("The information was successfully updated.");
            confirmUpdate.show();
            buttonAddEmployee.setDisable(false);
            return;
            
        }
        //if the user cancels an update, revert the update employee button's name 
        //to modify employee and re-enable the add employee button
        buttonModifyEmployee.setText("Modify Employee");
        buttonAddEmployee.setDisable(false);
    }
    
    public void addEmployee(int id, String name, String city, String position) {
        //disable the add employee, update employee and delete employee buttons during an addition
        buttonAddEmployee.setDisable(true);
        buttonModifyEmployee.setDisable(true);
        buttonDeleteEmployee.setDisable(true);
        
        
        Alert confirmAdd = new Alert(AlertType.CONFIRMATION);
        confirmAdd.setHeaderText("Confirm Update");
        confirmAdd.setContentText("Are you sure you want to add this employee's information?");
        Optional<ButtonType> choice = confirmAdd.showAndWait();
        
        if(choice.get() == ButtonType.OK) {
           if(!isValidInput());
           
           employees.remove(currentIndex);
            //code for validating input should come here
            employees.add(currentIndex, new Employee(id, name, city, position));
            
            Alert successfulAdd = new Alert(AlertType.INFORMATION);
            successfulAdd.setHeaderText("Addition Successful");
            successfulAdd.setContentText("The information was successfully added.");
            successfulAdd.show();
            buttonAddEmployee.setDisable(false);
            return;
            
        }
        //if the user cancels an addition, re-enable the add employee, update employee 
        //and delete employee buttons
        buttonAddEmployee.setDisable(false);
        buttonModifyEmployee.setDisable(false);
        buttonDeleteEmployee.setDisable(false);
    }
    
    /**
     * Validates the input for the employee information text fields and displays errors
     * if the input is invalid
     * This method relies on the overloaded isValidInput() method
     * @return true if the input is valid or false if invalid
     */
    private boolean isValidInput() {
  
        invalidInput.setTitle("Invalid Input");
        //validate the text fields before updating an employee
        if(!isValidInput(textFieldId)) {
            invalidInput.setContentText("Invalid input. An ID should contain only numbers.");
            invalidInput.show();
            return false;
        } 
        
        if(!isValidInput(textFieldName)) {
            invalidInput.setContentText("Invalid input. A name cannot contain numbers.");
            invalidInput.show();
            return false;
        }
                
        if(!isValidInput(textFieldCity)) {
            invalidInput.setContentText("Invalid input. A city name cannot contain numbers.");
            invalidInput.show();
            return false;
        }
                
        if(!isValidInput(textFieldPosition)) {
           invalidInput.setContentText("Invalid input. A position cannot contan numbers.");
           invalidInput.show();
           return false;
        }
        
        return true;
        
    }
    
    /**
     * Deletes an employee
     */
    public void deleteEmployee() {
        Alert confirmAdd = new Alert(AlertType.CONFIRMATION);
        confirmAdd.setHeaderText("Confirm Delete");
        confirmAdd.setContentText("Are you sure you want to delete this employee's information?");
        Optional<ButtonType> choice = confirmAdd.showAndWait();
        
        if(choice.get() == ButtonType.OK) {
            employees.remove(currentIndex);
            Alert deleteSuccessful = new Alert(AlertType.INFORMATION);
            deleteSuccessful.setHeaderText("Deletion Successful");
            deleteSuccessful.setContentText("The information was successfully deleted.");
            deleteSuccessful.show();
            
        }
    }
    
    /**
     * Validates the user input from a text field
     * @param textField the textField to validate input for
     * @return 
     */
    private boolean isValidInput(TextField textField) {
        
        if(textField.getId().equals("textFieldId"))
            return textField.getText().matches("\\d{0,11}([\\.]\\d{0,2})?");
       
        if(textField.getId().equals("textFieldName"))
            return textField.getText().matches("[a-zA-Z]+");
        
        if(textField.getId().equals("textFieldCity"))
            return textField.getText().matches("[a-zA-Z]+");
        
        if(textField.getId().equals("textFieldPosition"))
            return textField.getText().matches("[a-zA-Z]+");
        
        return false;
    }
    
    
    
   
    
}
