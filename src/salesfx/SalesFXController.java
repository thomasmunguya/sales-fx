/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesfx;

import content.Employee;
import content.EmployeeFile;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author MTH
 */
public class SalesFXController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldCity;

    @FXML
    private TextField textFieldPosition;

    @FXML
    private Button buttonFirst;

    @FXML
    private Button buttonPrevious;

    @FXML
    private Button buttonNext;

    @FXML
    private Button buttonLast;

    @FXML
    private Button buttonModifier1;

    @FXML
    private Button buttonModifier2;

    @FXML
    private Button buttonModifier3;
    
    private ArrayList<Employee> employees;
    private static int currentIndex = 0;
    private final Alert invalidInput  = new Alert(Alert.AlertType.ERROR);
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadList();
        setUpEventHandlers();
        disableOrEnableNavButtons();
    }

    
   /**
    * Populates the text fields
    * @param id the id to populate the id text field with
    * @param name the name to populate the name text field with
    * @param city the city to populate the city text field with
    * @param Position the position to populate the position text field with
    */
    private void populateTextFields(String id, String name, String city, String position) {
        textFieldId.setText(id);
        textFieldName.setText(name);
        textFieldCity.setText(city);
        textFieldPosition.setText(position);
    }
    
    
    
    /**
     * Sets up event handler for buttons
     */
    public void setUpEventHandlers() {
        buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentIndex--;
                Employee employee = navigateEmployeesList(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
        });
        
        buttonFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentIndex = 0;
                Employee employee = navigateEmployeesList(currentIndex);
                textFieldId.setText(employee.getId() + "");
                textFieldName.setText(employee.getName());
                textFieldCity.setText(employee.getCity());
                textFieldPosition.setText(employee.getPosition());
            }
        });
        
        buttonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentIndex++;
                System.out.println(currentIndex);
                Employee employee = navigateEmployeesList(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
        });
        
        buttonLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentIndex = employees.size() - 1;
                Employee employee = navigateEmployeesList(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
        });
        
        buttonModifier2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(buttonModifier2.getText().equals("Cancel Update")) {
                    
                    buttonModifier1.setText("Modify Employee");
                    buttonModifier2.setText("Add Employee");
                    
                    Employee employee = navigateEmployeesList(currentIndex);
                    
                    populateTextFields(employee.getId() + "", employee.getName(), employee.getCity(), employee.getPosition());
                    
                    textFieldId.setEditable(false);
                    textFieldName.setEditable(false);
                    textFieldCity.setEditable(false);
                    textFieldPosition.setEditable(false);
                    
                    buttonModifier3.setDisable(false);
                    
                }
                
                else if(buttonModifier2.getText().equals("Add Employee")) {
                    
                    textFieldId.clear();
                    textFieldName.clear();
                    textFieldCity.clear();
                    textFieldPosition.clear();
                    
                    textFieldId.setEditable(true);
                    textFieldName.setEditable(true);
                    textFieldCity.setEditable(true);
                    textFieldPosition.setEditable(true);
                    
                    buttonModifier1.setDisable(true);
                    
                    buttonModifier2.setText("Confirm Add");
                    buttonModifier3.setText("Cancel Add");
                }
                
                else if(buttonModifier2.getText().equals("Confirm Add")) {
                    
                    if(!isValidInput());
           
                    else addEmployee(Integer.parseInt(textFieldId.getText()), textFieldName.getText()
                        , textFieldCity.getText(), textFieldPosition.getText());
                }
                    
                
            }
        });
        
        buttonModifier1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                if(buttonModifier1.getText().equals("Modify Employee")) {
                    
                    modifyEmployee();
                    
                }
                    
                else if (buttonModifier1.getText().equals("Update")) {
                    
                    if(!isValidInput());
                    
                    else
                        updateEmployee(Integer.parseInt(textFieldId.getText()), textFieldName.getText()
                        , textFieldCity.getText(), textFieldPosition.getText());
                }
                    
                    
            }
        });
        
        buttonModifier3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                if(buttonModifier3.getText().equals("Delete Employee")) {
                    deleteEmployee();
                }
                
                else if(buttonModifier3.getText().equals("Cancel Add")) {
                    
                    buttonModifier2.setText("Add Employee");
                    buttonModifier3.setText("Delete Employee");
                    
                    Employee employee = navigateEmployeesList(currentIndex);
                    
                    populateTextFields(employee.getId() + "", employee.getName(), employee.getCity(), employee.getPosition());
                    
                    textFieldId.setEditable(false);
                    textFieldName.setEditable(false);
                    textFieldCity.setEditable(false);
                    textFieldPosition.setEditable(false);
                    buttonModifier1.setDisable(false);
                }
                    
                    
            }
        });
    }
    
    /**
     * Loads the employees list with data from the Employee.dat file
     */
    private void loadList() {
        
        try {
            
            employees = EmployeeFile.readFromFile();
            
        } catch (IOException ex) {
            
            Alert fileloadError = new Alert(AlertType.ERROR);
            fileloadError.setHeaderText("Cannot load file");
            fileloadError.setContentText("Cannot load the Employee.dat file. Ensure that the file"
                    + "is in the correct directory");
            fileloadError.show();
            
        }
        
        populateTextFields(employees.get(0).getId() + "", employees.get(0).getName(),
                        employees.get(0).getCity(), employees.get(0).getPosition());
      
        
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
        //and disable the delete button
        buttonModifier3.setDisable(true);
        
        //disable the add employee button during a modification and 
        //change the modify employee button's name to update employee during a modification
        buttonModifier1.setText("Update");
        buttonModifier2.setText("Cancel Update");
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
        Alert confirmUpdate = new Alert(Alert.AlertType.CONFIRMATION);
        confirmUpdate.setHeaderText("Confirm Update");
        confirmUpdate.setContentText("Are you sure you want to perform an update of this employee's information?");
        Optional<ButtonType> choice = confirmUpdate.showAndWait();
        
        if(choice.get() == ButtonType.OK) {
            
            if(!isValidInput())
               return;
            
            employees.remove(currentIndex);
            employees.add(currentIndex, new Employee(id, name, city, position));
            
            Alert successfulUpdate = new Alert(Alert.AlertType.INFORMATION);
            successfulUpdate.setHeaderText("Update Successful");
            confirmUpdate.setContentText("The information was successfully updated.");
            confirmUpdate.show();
            buttonModifier2.setDisable(false);
            return;
            
        }
        //if the user cancels an update, revert the update employee button's name 
        //to modify employee, revert the text fields to their pre-update state disable them
        buttonModifier1.setText("Modify Employee");
    }
    
    public void addEmployee(int id, String name, String city, String position) {
        
        Alert confirmAdd = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAdd.setHeaderText("Confirm Add");
        confirmAdd.setContentText("Are you sure you want to add this employee's information?");
        Optional<ButtonType> choice = confirmAdd.showAndWait();
        
        if(choice.get() == ButtonType.OK) {
           if(!isValidInput()) {
               return;
           }
           
           employees.add(new Employee(id, name, city, position));
           currentIndex = employees.size() - 1;
           
           
           textFieldId.setText(id + "");
           textFieldName.setText(name);
           textFieldCity.setText(city);
           textFieldPosition.setText(position);
          
           Alert successfulAdd = new Alert(Alert.AlertType.INFORMATION);
           successfulAdd.setHeaderText("Addition Successful");
           successfulAdd.setContentText("The information was successfully added.");
           successfulAdd.show();
           
           buttonModifier1.setText("Modify Employee");
           buttonModifier2.setText("Add Employee");
         
           buttonModifier1.setDisable(false);
           buttonModifier3.setDisable(false);
           
           return;
            
        }
        
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
            invalidInput.setContentText("Invalid input. An ID can only contain numbers and not be empty");
            invalidInput.show();
            return false;
        } 
        
        if(!isValidInput(textFieldName)) {
            invalidInput.setContentText("Invalid input. Name cannot contain numbers or be empty");
            invalidInput.show();
            return false;
        }
                
        if(!isValidInput(textFieldCity)) {
            invalidInput.setContentText("Invalid input. City cannot contain numbers or be empty");
            invalidInput.show();
            return false;
        }
                
        if(!isValidInput(textFieldPosition)) {
           invalidInput.setContentText("Invalid input. Position cannot contain numbers or be empty");
           invalidInput.show();
           return false;
        }
        
        return true;
        
    }
    
    
    
    /**
     * Deletes an employee
     */
    public void deleteEmployee() {
        
        Alert confirmAdd = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAdd.setHeaderText("Confirm Delete");
        confirmAdd.setContentText("Are you sure you want to delete this employee's information?");
        Optional<ButtonType> choice = confirmAdd.showAndWait();
        
        if(choice.get() == ButtonType.OK) {
            //if the employees list is empty display a deletion error
            if(employees.isEmpty()) {
                System.out.println("The list is empty now");
                Alert deleteFailure = new Alert(Alert.AlertType.ERROR);
                deleteFailure.setHeaderText("Deletion Failed");
                deleteFailure.setContentText("There's nothing to delete");
                deleteFailure.show();
                return;
            }
            
            if(employees.size() == 1) {
                employees.remove(currentIndex);
                currentIndex--;
                populateTextFields("", "", "", "");
                return;
            }
            
            employees.remove(currentIndex);
            currentIndex--;
            if(currentIndex == -1) {
                currentIndex++;
                Employee employee = navigateEmployeesList(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());  
            }
                
            else {
                Employee employee = navigateEmployeesList(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
            
            Alert deleteSuccessful = new Alert(Alert.AlertType.INFORMATION);
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
            return textFieldId.getText().matches("\\d{0,11}([\\.]\\d{0,2})?")
                    && !textFieldId.getText().equals("") ;
       
        if(textField.getId().equals("textFieldName"))
            return textFieldName.getText().matches("[a-zA-Z]+")
                    && !textFieldName.getText().equals("");
        
        if(textField.getId().equals("textFieldCity"))
            return textFieldCity.getText().matches("[a-zA-Z]+")
                    && !textFieldCity.getText().equals("");
        
        if(textField.getId().equals("textFieldPosition"))
            return textFieldPosition.getText().matches("[a-zA-Z]+")
                    && !textFieldPosition.getText().equals("");
        
        return true;
    }
    
    
}
