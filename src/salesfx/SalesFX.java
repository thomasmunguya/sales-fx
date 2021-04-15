/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesfx;

import content.Employee;
import content.EmployeeFile;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
/**
 *
 * @author MTH
 */
public class SalesFX extends Application {
//    private VBox vBoxEmployeeInfo;
//
//    
//    private Label labelEmployeeInfo;
//
//    
//    private Label labelId;
//
//    
//    private  static final TextField textFieldId = new TextField();
//
//    
//    private Label labelName;
//
//    
//    private TextField textFieldName;
//
//    
//    private Label labelCity;
//
//    private TextField textFieldCity;
//
//    
//    private Label labelPosition;
//
//    
//    private TextField textFieldPosition;
//
//    
//    private HBox hBoxNavigationPane;
//
//    
//    private Button buttonFirst;
//
//    
//    private Button buttonPrevious;
//
//    
//    private Button buttonNext;
//
//   
//    private Button buttonLast;
//
//    
//    private VBox vBoxModifiersPane;
//
//    
//    private Button buttonModifyEmployee;
//
//    
//    private Button buttonAddEmployee;
//
//    
//    private Button buttonDeleteEmployee;
//
//   
//    private Button buttonSearch;
//
//    
//    private Label labelSalesFX;
//    
//    
//    private AnchorPane rootPane;
//    
//    private HBox hBoxBlueDecor;
//    
//    private ArrayList<Employee> employees;
//    private static int currentIndex = 0;
//    private final Alert invalidInput  = new Alert(AlertType.ERROR);

    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
//        textFieldId = new TextField();
//        setUpRootPane();
        AnchorPane rootPane = FXMLLoader.<AnchorPane>load(getClass().getResource("/salesfx/SalesFX.fxml"));
        Scene scene = new Scene(rootPane, 505, 400);
        System.out.println(Thread.currentThread().getName());
        primaryStage.setTitle("SalesFX");
        primaryStage.setScene(scene);
        primaryStage.show();
//        loadList();
//        disableOrEnableNavButtons();
//        setUpEventHandlers();
//        textFieldId.setText("101");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
//    private void setUpEmployeeInfoPane() {
//        
//        labelEmployeeInfo = new Label("Employee Information");
//        labelEmployeeInfo.setTextFill(Paint.valueOf("#fffefe"));
//        labelEmployeeInfo.setFont(new Font(20.0));
//        
//        labelId = new Label("ID");
//        
//        System.out.println(Thread.currentThread().getName());
////        textFieldId = new TextField();
////        textFieldId.setId("textFieldId");
////        textFieldId.setEditable(false);
//        
//        labelName = new Label("Name");
//        
//        textFieldName = new TextField();
//        textFieldName.setId("textFieldName");
//        textFieldName.setEditable(false);
//        
//        labelCity = new Label("City");
//        
//        textFieldCity = new TextField();
//        textFieldCity.setId("textFieldCity");
//        textFieldCity.setEditable(false);
//        
//        labelPosition = new Label("Position");
//        
//        textFieldPosition = new TextField();
//        textFieldPosition.setId("textFieldPosition");
//        textFieldPosition.setEditable(false);
//        
//        vBoxEmployeeInfo = new VBox();
//        vBoxEmployeeInfo.setLayoutX(22.0);
//        vBoxEmployeeInfo.setLayoutY(24.0);
//        vBoxEmployeeInfo.setSpacing(10.0);
//        
//        vBoxEmployeeInfo.getChildren().addAll(labelEmployeeInfo, labelId,  textFieldId, labelName,
//                textFieldName, labelCity, textFieldCity, labelPosition, textFieldPosition);
//    }
//    
//    
//    /**
//     * Sets up the navigationPane
//     */
//    private void setUpNavigationPane() {
//        
//        buttonFirst = new Button("First");
//        buttonFirst.setPrefHeight(22.0);
//        buttonFirst.setPrefWidth(90.0);
//        
//        buttonPrevious = new Button("Previous");
//        buttonPrevious.setPrefHeight(22.0);
//        buttonPrevious.setPrefWidth(90.0);
//        
//        buttonNext = new Button("Next");
//        buttonNext.setPrefHeight(22.0);
//        buttonNext.setPrefWidth(90.0);
//        
//        buttonLast = new Button("Last");
//        buttonLast.setPrefHeight(22.0);
//        buttonLast.setPrefWidth(90.0);
//        
//        buttonSearch = new Button("Search");
//        buttonSearch.setLayoutX(213.0);
//        buttonSearch.setLayoutY(364.0);
//        buttonSearch.setPrefHeight(22.0);
//        buttonSearch.setPrefWidth(90.0);
//        
//        hBoxNavigationPane = new HBox();
//        hBoxNavigationPane.setLayoutX(70.0);
//        hBoxNavigationPane.setLayoutY(328.0);
//        hBoxNavigationPane.setSpacing(5.0);
//        hBoxNavigationPane.getChildren().addAll(buttonFirst, buttonPrevious, buttonNext, buttonLast);
//        
//        
//    }
//    
//    /**
//     * Sets up the modifiers pane
//     */
//    private void setUpModifiersPane() {
//        buttonModifyEmployee = new Button("Modify Employee");
//        buttonModifyEmployee.setPrefHeight(64.0);
//        buttonModifyEmployee.setPrefWidth(114.0);
//        
//        buttonAddEmployee = new Button("Add Employee");
//        buttonAddEmployee.setPrefHeight(64.0);
//        buttonAddEmployee.setPrefWidth(114.0);
//        
//        buttonDeleteEmployee = new Button("Delete Employee");
//        buttonDeleteEmployee.setPrefHeight(64.0);
//        buttonDeleteEmployee.setPrefWidth(114.0);
//        
//        vBoxModifiersPane = new VBox();
//        vBoxModifiersPane.setLayoutX(366.0);
//        vBoxModifiersPane.setLayoutY(90.0);
//        vBoxModifiersPane.setSpacing(5.0);
//        vBoxModifiersPane.getChildren().addAll(buttonModifyEmployee, buttonAddEmployee, buttonDeleteEmployee);
//    }
//    
//    /**
//     * Sets up the root pane
//     */
//    private void setUpRootPane() {
//        labelSalesFX = new Label("SalesFX");
//        labelSalesFX.setLayoutX(334.0);
//        labelSalesFX.setLayoutY(14.0);
//        labelSalesFX.setTextFill(Paint.valueOf("#fcfcfc"));
//        
//        hBoxBlueDecor = new HBox();
//        
//        setUpEmployeeInfoPane();
//        setUpModifiersPane();
//        setUpNavigationPane();
//        
//        rootPane = new AnchorPane();
//        rootPane.getChildren().addAll(hBoxBlueDecor, labelSalesFX, vBoxEmployeeInfo, hBoxNavigationPane, buttonSearch, vBoxModifiersPane);
//        
//        setUpEmployeeInfoPane();
//    }
//    
//    /**
//     * Sets up event handler for buttons
//     */
//    public void setUpEventHandlers() {
//        buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                currentIndex--;
//                System.out.println(currentIndex);
//                Employee employee = navigateEmployeesList(currentIndex);
//                textFieldId.setText(employee.getId() + "");
//                textFieldName.setText(employee.getName());
//                textFieldCity.setText(employee.getCity());
//                textFieldPosition.setText(employee.getPosition());
//            }
//        });
//        
//        buttonFirst.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                currentIndex = 0;
//                Employee employee = navigateEmployeesList(currentIndex);
//                textFieldId.setText(employee.getId() + "");
//                textFieldName.setText(employee.getName());
//                textFieldCity.setText(employee.getCity());
//                textFieldPosition.setText(employee.getPosition());
//            }
//        });
//        
//        buttonNext.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                currentIndex++;
//                System.out.println(currentIndex);
//                Employee employee = navigateEmployeesList(currentIndex);
//                textFieldId.setText(employee.getId() + "");
//                textFieldName.setText(employee.getName());
//                textFieldCity.setText(employee.getCity());
//                textFieldPosition.setText(employee.getPosition());
//            }
//        });
//        
//        buttonLast.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                currentIndex = employees.size() - 1;
//                Employee employee = navigateEmployeesList(currentIndex);
//                textFieldId.setText(employee.getId() + "");
//                textFieldName.setText(employee.getName());
//                textFieldCity.setText(employee.getCity());
//                textFieldPosition.setText(employee.getPosition());
//            }
//        });
//        
//        buttonAddEmployee.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                addEmployee(Integer.parseInt(textFieldId.getText()), textFieldName.getText()
//                        , textFieldCity.getText(), textFieldPosition.getText());
//            }
//        });
//        
//        buttonModifyEmployee.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if(buttonModifyEmployee.getText().equals("Modify Employee"))
//                    modifyEmployee();
//                else if (buttonModifyEmployee.getText().equals("Update Employee"))
//                    updateEmployee(Integer.parseInt(textFieldId.getText()), textFieldName.getText()
//                        , textFieldCity.getText(), textFieldPosition.getText());
//            }
//        });
//        
//        buttonDeleteEmployee.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                deleteEmployee();
//            }
//        });
//    }
//    /**
//     * Loads the employees list with data from the Employee.dat file
//     */
//    private void loadList() {
//        try {
//            employees = EmployeeFile.readFromFile();
//        } catch (IOException ex) {
//            Logger.getLogger(SalesFX.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        textFieldId.setFont(new Font(12));
//        textFieldId.setText(employees.get(0).getId() + "");
//        
//        System.out.println(textFieldId.getText().equals("102"));
//        textFieldName.setText(employees.get(0).getName());
//        textFieldCity.setText(employees.get(0).getCity());
//        textFieldPosition.setText(employees.get(0).getPosition());
//      
//        
//    }
//    
//    /**
//     * 
//     * @param index the index of the employee in the list
//     */
//    private Employee navigateEmployeesList(int index) {
//        return employees.get(index);
//    }
//    
//    /**
//     * Disables and enables the navigation buttons depending on the index at which the user is in the employees list
//     */
//    private void disableOrEnableNavButtons() {
//        //if the index is 0 disable the first and previous navigation buttons
//        buttonPrevious.disabledProperty().addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable ov) {
//                if(currentIndex == 0) {
//                    buttonPrevious.setDisable(true);
//                    buttonFirst.setDisable(true);
//                }
//                else {
//                    buttonPrevious.setDisable(false);
//                    buttonFirst.setDisable(false);
//                }
//            }
//        });
//        
//        //if the index is the last index in the employee list disable the next and last navigation buttons
//        buttonPrevious.disabledProperty().addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable ov) {
//                if(currentIndex == employees.size() - 1) {
//                    buttonNext.setDisable(true);
//                    buttonLast.setDisable(true);
//                }
//                else {
//                    buttonNext.setDisable(false);
//                    buttonLast.setDisable(false);
//                }
//            }
//        });
//        
//    }
//    
//    /**
//     * Modifies the information of an employee
//     */
//    @FXML
//    public void modifyEmployee() {
//        //enable the employee information text fields during a modification
//        textFieldId.setEditable(true);
//        textFieldName.setEditable(true);
//        textFieldCity.setEditable(true);
//        textFieldPosition.setEditable(true);
//        
//        //disable the add employee button during a modification and 
//        //change the modify employee button's name to update employee during a modification
//        buttonAddEmployee.setDisable(true);
//        buttonModifyEmployee.setText("Update Employee");
//        
//    }
//    
//    
//    /**
//     * Updates the information of an employee
//     * @param id the new ID
//     * @param name the new name
//     * @param city the new city
//     * @param position the new position
//     */
//    @FXML
//    public void updateEmployee(int id, String name, String city, String position) {
//        Alert confirmUpdate = new Alert(AlertType.CONFIRMATION);
//        confirmUpdate.setHeaderText("Confirm Update");
//        confirmUpdate.setContentText("Are you sure you want to perform an update of this employee's information?");
//        Optional<ButtonType> choice = confirmUpdate.showAndWait();
//        
//        if(choice.get() == ButtonType.OK) {
//            
//            if(!isValidInput())
//               return;
//            
//            employees.remove(currentIndex);
//            employees.add(currentIndex, new Employee(id, name, city, position));
//            
//            Alert successfulUpdate = new Alert(AlertType.INFORMATION);
//            successfulUpdate.setHeaderText("Update Successful");
//            confirmUpdate.setContentText("The information was successfully updated.");
//            confirmUpdate.show();
//            buttonAddEmployee.setDisable(false);
//            return;
//            
//        }
//        //if the user cancels an update, revert the update employee button's name 
//        //to modify employee and re-enable the add employee button
//        buttonModifyEmployee.setText("Modify Employee");
//        buttonAddEmployee.setDisable(false);
//    }
//    
//    public void addEmployee(int id, String name, String city, String position) {
//        //disable the add employee, update employee and delete employee buttons during an addition
//        buttonAddEmployee.setDisable(true);
//        buttonModifyEmployee.setDisable(true);
//        buttonDeleteEmployee.setDisable(true);
//        
//        //clear the text field when adding a new employee
//        textFieldId.clear();
//        textFieldName.clear();
//        textFieldCity.clear();
//        textFieldPosition.clear();
//        
//        Alert confirmAdd = new Alert(AlertType.CONFIRMATION);
//        confirmAdd.setHeaderText("Confirm Update");
//        confirmAdd.setContentText("Are you sure you want to add this employee's information?");
//        Optional<ButtonType> choice = confirmAdd.showAndWait();
//        
//        if(choice.get() == ButtonType.OK) {
//           if(!isValidInput())
//               return;
//            
//           employees.remove(currentIndex);
//           employees.add(currentIndex, new Employee(id, name, city, position));
//            
//           Alert successfulAdd = new Alert(AlertType.INFORMATION);
//           successfulAdd.setHeaderText("Addition Successful");
//           successfulAdd.setContentText("The information was successfully added.");
//           successfulAdd.show();
//           buttonAddEmployee.setDisable(false);
//           return;
//            
//        }
//        //if the user cancels an addition, re-enable the add employee, update employee 
//        //and delete employee buttons
//        buttonAddEmployee.setDisable(false);
//        buttonModifyEmployee.setDisable(false);
//        buttonDeleteEmployee.setDisable(false);
//    }
//    
//    /**
//     * Validates the input for the employee information text fields and displays errors
//     * if the input is invalid
//     * This method relies on the overloaded isValidInput() method
//     * @return true if the input is valid or false if invalid
//     */
//    private boolean isValidInput() {
//  
//        invalidInput.setTitle("Invalid Input");
//        //validate the text fields before updating an employee
//        if(!isValidInput(textFieldId)) {
//            invalidInput.setContentText("Invalid input. An ID should contain only numbers.");
//            invalidInput.show();
//            return false;
//        } 
//        
//        if(!isValidInput(textFieldName)) {
//            invalidInput.setContentText("Invalid input. A name cannot contain numbers.");
//            invalidInput.show();
//            return false;
//        }
//                
//        if(!isValidInput(textFieldCity)) {
//            invalidInput.setContentText("Invalid input. A city name cannot contain numbers.");
//            invalidInput.show();
//            return false;
//        }
//                
//        if(!isValidInput(textFieldPosition)) {
//           invalidInput.setContentText("Invalid input. A position cannot contan numbers.");
//           invalidInput.show();
//           return false;
//        }
//        
//        return true;
//        
//    }
//    
//    
//    
//    /**
//     * Deletes an employee
//     */
//    public void deleteEmployee() {
//        Alert confirmAdd = new Alert(AlertType.CONFIRMATION);
//        confirmAdd.setHeaderText("Confirm Delete");
//        confirmAdd.setContentText("Are you sure you want to delete this employee's information?");
//        Optional<ButtonType> choice = confirmAdd.showAndWait();
//        
//        if(choice.get() == ButtonType.OK) {
//            employees.remove(currentIndex);
//            Alert deleteSuccessful = new Alert(AlertType.INFORMATION);
//            deleteSuccessful.setHeaderText("Deletion Successful");
//            deleteSuccessful.setContentText("The information was successfully deleted.");
//            deleteSuccessful.show();
//            
//        }
//    }
//    
//    /**
//     * Validates the user input from a text field
//     * @param textField the textField to validate input for
//     * @return 
//     */
//    private boolean isValidInput(TextField textField) {
//        
//        if(textField.getId().equals("textFieldId"))
//            return textField.getText().matches("\\d{0,11}([\\.]\\d{0,2})?");
//       
//        if(textField.getId().equals("textFieldName"))
//            return textField.getText().matches("[a-zA-Z]+");
//        
//        if(textField.getId().equals("textFieldCity"))
//            return textField.getText().matches("[a-zA-Z]+");
//        
//        if(textField.getId().equals("textFieldPosition"))
//            return textField.getText().matches("[a-zA-Z]+");
//        
//        return false;
//    }
//    
//    
   
    
}
