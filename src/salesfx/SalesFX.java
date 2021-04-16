/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salesfx;

import content.Employee;
import content.EmployeeFile;
import content.SearchStage;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;

/**
 *  Group Member Names: (maximum of two people per group)
 *  Group Member Student Numbers:
 *  Final Project:
 *  Date:
*/
public class SalesFX extends Application {
    
    private VBox vBoxEmployeeInfo = new VBox();

    
    private Label lblEmployeeInfo = new Label("Employee Information");

    
    private Label lblId = new Label("ID");

    
    private TextField txtId = new TextField();

    
    private Label lblName = new Label("Name");

    
    private TextField txtName = new TextField();

    
    private Label lblCity = new Label("City");

    private TextField txtCity = new TextField();

    
    private Label lblPosition = new Label("Position");

    
    private TextField txtPosition = new TextField();

    
    private HBox hBoxNavigationPane = new HBox();

    
    private Button btnFirst = new Button("First");

    
    private Button btnPrevious = new Button("Previous");

    
    private Button btnNext = new Button("Next");

   
    private Button btnLast = new Button("Last");

    
    private VBox vBoxModifiersPane = new VBox();

    
    private Button btnModifier1 = new Button("Modify Employee");

    
    private Button btnModifier2 = new Button("Add Employee");

    
    private Button btnModifier3 = new Button("Delete Employee");

   
    private Button btnSearch = new Button("Search");

    
    private Label lblSalesFX = new Label("SalesFX");
    
    
    private AnchorPane rootPane = new AnchorPane();
    
    private HBox hBoxBlueDecor = new HBox();
    
    private SearchStage searchStageInstance;
   
    public static ArrayList<Employee> employeeList;
    private static int currentIndex = 0;
    private final Alert invalidInput  = new Alert(AlertType.ERROR);

    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        lblId.setFont(new Font("arial", 14));
        lblName.setFont(new Font("arial", 14));
        lblCity.setFont(new Font("arial", 14));
        lblPosition.setFont(new Font("arial", 14));
        
        hBoxBlueDecor.setId("blueDecor");
        hBoxBlueDecor.setPrefHeight(64.0);
        hBoxBlueDecor.setPrefWidth(505);
        
        lblEmployeeInfo.setTextFill(Paint.valueOf("#fffefe"));
        lblEmployeeInfo.setFont(new Font(20.0));
        
        txtId.setId("txtId");
        txtId.setEditable(false);
        
        txtName.setId("txtName");
        txtName.setEditable(false);
        
        txtCity.setId("txtCity");
        txtCity.setEditable(false);
        
        txtPosition.setId("txtPosition");
        txtPosition.setEditable(false);
        
        btnFirst.setPrefHeight(22.0);
        btnFirst.setPrefWidth(90.0);
        btnFirst.getStyleClass().add("button");
        btnFirst.getStyleClass().add("navigationButton");
        
        btnPrevious.setPrefHeight(22.0);
        btnPrevious.setPrefWidth(90.0);
        btnPrevious.getStyleClass().add("button");
        btnPrevious.getStyleClass().add("navigationButton");
        
        btnNext.setPrefHeight(22.0);
        btnNext.setPrefWidth(90.0);
        btnNext.getStyleClass().add("button");
        btnNext.getStyleClass().add("navigationButton");
        
        btnLast.setPrefHeight(22.0);
        btnLast.setPrefWidth(90.0);
        btnLast.getStyleClass().add("button");
        btnLast.getStyleClass().add("navigationButton");
       
        btnSearch.setLayoutX(213.0);
        btnSearch.setLayoutY(364.0);
        btnSearch.setPrefHeight(22.0);
        btnSearch.setPrefWidth(90.0);
        btnSearch.getStyleClass().add("button");
        btnSearch.getStyleClass().add("navigationButton");
        
        btnModifier1.setPrefHeight(64.0);
        btnModifier1.setPrefWidth(114.0);
        btnModifier1.setId("modifierButton1");
        btnModifier1.getStyleClass().add("button");
   
        btnModifier2.setPrefHeight(64.0);
        btnModifier2.setPrefWidth(114.0);
        btnModifier2.setId("modifierButton2");
        btnModifier2.getStyleClass().add("button");
    
        btnModifier3.setPrefHeight(64.0);
        btnModifier3.setPrefWidth(114.0);  
        btnModifier3.setId("modifierButton3");
        btnModifier3.getStyleClass().add("button");
        
        lblSalesFX.setLayoutX(334.0);
        lblSalesFX.setLayoutY(14.0);
        lblSalesFX.setTextFill(Paint.valueOf("#fcfcfc"));
        lblSalesFX.setFont(new Font("Roboto", 44));
        
        //set up the panes and event handlers for buttons
        setUpRootPane();
        loadList();
        setUpEventHandlers();
        
        Scene mainScene = new Scene(rootPane, 505, 400);
        mainScene.getStylesheets().add("assets/css/styles.css");
        primaryStage.setTitle("SalesFX");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                EmployeeFile.writeToFile();
            }
            
        });
        
        
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchStageInstance = SearchStage.loadSearchStage();
                Scene searchScene =  new Scene(searchStageInstance.setUpSearchScene(), 505, 400);
                Stage searchStage = new Stage();
                searchScene.getStylesheets().add("assets/css/styles.css");
                searchStage.setScene(searchScene);
                searchStage.initModality(Modality.APPLICATION_MODAL);
                searchStage.show();
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    /**
     * Sets up the employee information pane
     */
    private void setUpEmployeeInfoPane() {
        
        vBoxEmployeeInfo.setLayoutX(22.0);
        vBoxEmployeeInfo.setLayoutY(24.0);
        vBoxEmployeeInfo.setSpacing(10.0);
        vBoxEmployeeInfo.getChildren().addAll(lblEmployeeInfo, lblId,  txtId, lblName,
                txtName, lblCity, txtCity, lblPosition, txtPosition);
    }
    
    
    /**
     * Sets up the navigation pane
     */
    private void setUpNavigationPane() {
        
        hBoxNavigationPane = new HBox();
        hBoxNavigationPane.setLayoutX(70.0);
        hBoxNavigationPane.setLayoutY(328.0);
        hBoxNavigationPane.setSpacing(5.0);
        hBoxNavigationPane.getChildren().addAll(btnFirst, btnPrevious, btnNext, btnLast);
        
        
    }
//    
    /**
     * Sets up the pane for the modifier buttons
     */
    private void setUpModifiersPane() {
        vBoxModifiersPane.setLayoutX(366.0);
        vBoxModifiersPane.setLayoutY(90.0);
        vBoxModifiersPane.setSpacing(5.0);
        vBoxModifiersPane.getChildren().addAll(btnModifier1, btnModifier2, btnModifier3);
    }
    
    /**
     * Sets up the root pane
     */
    private void setUpRootPane() {
        
        setUpEmployeeInfoPane();
        setUpModifiersPane();
        setUpNavigationPane();
        
        rootPane.getChildren().addAll(hBoxBlueDecor, lblSalesFX, vBoxEmployeeInfo, hBoxNavigationPane, btnSearch, vBoxModifiersPane);

    }
    
    /**
    * Populates the text fields
    * @param id the id to populate the id text field with
    * @param name the name to populate the name text field with
    * @param city the city to populate the city text field with
    * @param Position the position to populate the position text field with
    */
    private void populateTextFields(String id, String name, String city, String position) {
        txtId.setText(id);
        txtName.setText(name);
        txtCity.setText(city);
        txtPosition.setText(position);
    }
    
    
    
    /**
     * Sets up event handlers for buttons
     */
    private void setUpEventHandlers() {
        btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentIndex == 0 || employeeList.isEmpty()) {
                    Alert outOfBounds = new Alert(AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list.");
                    outOfBounds.show();
                    return;
                }
                currentIndex--;
                Employee employee = navigateEmployeesList(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
        });
        
        btnFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentIndex == 0 || employeeList.isEmpty()) {
                    Alert outOfBounds = new Alert(AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list.");
                    outOfBounds.show();
                    return;
                }
                currentIndex  = 0;
                Employee employee = navigateEmployeesList(currentIndex);
                txtId.setText(employee.getId() + "");
                txtName.setText(employee.getName());
                txtCity.setText(employee.getCity());
                txtPosition.setText(employee.getPosition());
            }
        });
        
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentIndex == employeeList.size() - 1 || employeeList.isEmpty()) {
                    Alert outOfBounds = new Alert(AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list");
                    outOfBounds.show();
                    return;
                }
                currentIndex++;
                System.out.println(currentIndex);
                Employee employee = navigateEmployeesList(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
        });
        
        btnLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
                if(currentIndex == employeeList.size() - 1 || employeeList.isEmpty()) {
                    Alert outOfBounds = new Alert(AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list");
                    outOfBounds.show();
                    return;
                }
                currentIndex = employeeList.size() - 1;
                Employee employee = navigateEmployeesList(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
        });
        
        btnModifier2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if(btnModifier2.getText().equals("Cancel Update")) {
                    
                    btnModifier1.setText("Modify Employee");
                    btnModifier2.setText("Add Employee");
                    
                    Employee employee = navigateEmployeesList(currentIndex);
                    
                    populateTextFields(employee.getId() + "", employee.getName(), employee.getCity(), employee.getPosition());
                    
                    txtId.setEditable(false);
                    txtName.setEditable(false);
                    txtCity.setEditable(false);
                    txtPosition.setEditable(false);
                    
                    btnModifier3.setDisable(false);
                    
                }
                
                else if(btnModifier2.getText().equals("Add Employee")) {
                    
                    txtId.clear();
                    txtName.clear();
                    txtCity.clear();
                    txtPosition.clear();
                    
                    txtId.setEditable(true);
                    txtName.setEditable(true);
                    txtCity.setEditable(true);
                    txtPosition.setEditable(true);
                    
                    btnModifier1.setDisable(true);
                    
                    btnModifier2.setText("Confirm Add");
                    btnModifier3.setText("Cancel Add");
                }
                
                else if(btnModifier2.getText().equals("Confirm Add")) {
                    
                    if(!isValidInput());
           
                    else addEmployee(Integer.parseInt(txtId.getText()), txtName.getText()
                        , txtCity.getText(), txtPosition.getText());
                }
                    
                
            }
        });
        
        btnModifier1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                if(btnModifier1.getText().equals("Modify Employee")) {
                    
                    modifyEmployee();
                    
                }
                    
                else if (btnModifier1.getText().equals("Update")) {
                    
                    if(!isValidInput());
                    
                    else
                        updateEmployee(Integer.parseInt(txtId.getText()), txtName.getText()
                        , txtCity.getText(), txtPosition.getText());
                }
                    
                    
            }
        });
        
        btnModifier3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                if(btnModifier3.getText().equals("Delete Employee")) {
                    deleteEmployee();
                }
                
                else if(btnModifier3.getText().equals("Cancel Add")) {
                    
                    btnModifier2.setText("Add Employee");
                    btnModifier3.setText("Delete Employee");
                    
                    Employee employee = navigateEmployeesList(currentIndex);
                    
                    populateTextFields(employee.getId() + "", employee.getName(), employee.getCity(), employee.getPosition());
                    
                    txtId.setEditable(false);
                    txtName.setEditable(false);
                    txtCity.setEditable(false);
                    txtPosition.setEditable(false);
                    btnModifier1.setDisable(false);
                }
                    
                    
            }
        });
    }
    
    /**
     * Loads the employeeList list with data from the Employee.dat file
     */
    private void loadList() {
        
        try {
            
            employeeList = EmployeeFile.readFromFile();
            
        } catch (IOException ex) {
            
            Alert fileloadError = new Alert(AlertType.ERROR);
            fileloadError.setHeaderText("Cannot load file");
            fileloadError.setContentText("Cannot load the Employee.dat file. Ensure that the file"
                    + "is in the correct directory");
            fileloadError.show();
            
        }
        
        populateTextFields(employeeList.get(0).getId() + "", employeeList.get(0).getName(),
                        employeeList.get(0).getCity(), employeeList.get(0).getPosition());
      
    }
  
    
    /**
     * Navigates the employee list
     * @param index the index of the employee in the list
     */
    private Employee navigateEmployeesList(int index) {
        return employeeList.get(index);
    }
   
    
    /**
     * Modifies the information of an employee
     */
    private void modifyEmployee() {
        
        //enable the employee information text fields during a modification
        txtId.setEditable(true);
        txtName.setEditable(true);
        txtCity.setEditable(true);
        txtPosition.setEditable(true);
        //and disable the delete button
        btnModifier3.setDisable(true);
        
        //disable the add employee button during a modification and 
        //change the modify employee button's name to update employee during a modification
        btnModifier1.setText("Update");
        btnModifier2.setText("Cancel Update");
    }
    
    
    /**
     * Updates the information of an employee
     * @param id the new ID
     * @param name the new name
     * @param city the new city
     * @param position the new position
     */
    private void updateEmployee(int id, String name, String city, String position) {
        Alert confirmUpdate = new Alert(Alert.AlertType.CONFIRMATION);
        confirmUpdate.setHeaderText("Confirm Update");
        confirmUpdate.setContentText("Are you sure you want to perform an update of this employee's information?");
        Optional<ButtonType> choice = confirmUpdate.showAndWait();
        
        if(choice.get() == ButtonType.OK) {
            
            if(!isValidInput())
               return;
            
            employeeList.remove(currentIndex);
            employeeList.add(currentIndex, new Employee(id, name, city, position));
            
            Alert successfulUpdate = new Alert(Alert.AlertType.INFORMATION);
            successfulUpdate.setHeaderText("Update Successful");
            confirmUpdate.setContentText("The information was successfully updated.");
            confirmUpdate.show();
            btnModifier2.setDisable(false);
            return;
            
        }
        //if the user cancels an update, revert the update employee button's name 
        //to modify employee, revert the text fields to their pre-update state disable them
        btnModifier1.setText("Modify Employee");
    }
    
    private void addEmployee(int id, String name, String city, String position) {
        
        Alert confirmAdd = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAdd.setHeaderText("Confirm Add");
        confirmAdd.setContentText("Are you sure you want to add this employee's information?");
        Optional<ButtonType> choice = confirmAdd.showAndWait();
        
        if(choice.get() == ButtonType.OK) {
           if(!isValidInput()) {
               return;
           }
           
           employeeList.add(new Employee(id, name, city, position));
           currentIndex = employeeList.size() - 1;
           
           
           txtId.setText(id + "");
           txtName.setText(name);
           txtCity.setText(city);
           txtPosition.setText(position);
          
           Alert successfulAdd = new Alert(Alert.AlertType.INFORMATION);
           successfulAdd.setHeaderText("Addition Successful");
           successfulAdd.setContentText("The information was successfully added.");
           successfulAdd.show();
           
           btnModifier1.setText("Modify Employee");
           btnModifier2.setText("Add Employee");
         
           btnModifier1.setDisable(false);
           btnModifier3.setDisable(false);
           
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
        if(!isValidInput(txtId)) {
            invalidInput.setContentText("Invalid input. An ID can only contain numbers and not be empty");
            invalidInput.show();
            return false;
        } 
        
        if(!isValidInput(txtName)) {
            invalidInput.setContentText("Invalid input. Name cannot contain numbers or be empty");
            invalidInput.show();
            return false;
        }
                
        if(!isValidInput(txtCity)) {
            invalidInput.setContentText("Invalid input. City cannot contain numbers or be empty");
            invalidInput.show();
            return false;
        }
                
        if(!isValidInput(txtPosition)) {
           invalidInput.setContentText("Invalid input. Position cannot contain numbers or be empty");
           invalidInput.show();
           return false;
        }
        
        return true;
        
    }
    
    
    
    /**
     * Deletes an employee
     */
    private void deleteEmployee() {
        
        //if the employeeList list is empty display a deletion error
        if(employeeList.isEmpty()) {
            Alert deleteFailure = new Alert(Alert.AlertType.ERROR);
            deleteFailure.setHeaderText("Deletion Failed");
            deleteFailure.setContentText("There's nothing to delete.");
            deleteFailure.show();
            return;
        }
      
        Alert confirmAdd = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAdd.setHeaderText("Confirm Delete");
        confirmAdd.setContentText("Are you sure you want to delete this employee's information?");
        Optional<ButtonType> choice = confirmAdd.showAndWait();
        
        if(choice.get() == ButtonType.OK) {
            
            if(employeeList.size() == 1) {
                employeeList.remove(currentIndex);
                populateTextFields("", "", "", "");
                return;
            }
            
            employeeList.remove(currentIndex);
            
            if(currentIndex == employeeList.size() - 1 && employeeList.size() != 1) {
                currentIndex--;
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
        
        if(textField.getId().equals("txtId"))
            return txtId.getText().matches("\\d{0,11}([\\.]\\d{0,2})?")
                    && !txtId.getText().equals("") ;
       
        if(textField.getId().equals("txtName"))
            return txtName.getText().matches("[a-zA-Z]+")
                    && !txtName.getText().equals("");
        
        if(textField.getId().equals("txtCity"))
            return txtCity.getText().matches("[a-zA-Z]+")
                    && !txtCity.getText().equals("");
        
        if(textField.getId().equals("txtPosition"))
            return txtPosition.getText().matches("[a-zA-Z]+")
                    && !txtPosition.getText().equals("");
        
        return true;
    }  
    
   
    
}
