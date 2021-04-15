/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;

/**
 *
 * @author MTH
 */
public class SearchStage {
    
    
    private TextField txtSearch = new TextField();

    
    private Button btnSearch = new Button("Search");

    
    private RadioButton radCity = new RadioButton("City");

    
    private RadioButton radPosition = new RadioButton("Position");

    
    private Button btnGoBack = new Button("Go Back");

    
    private Label lblId = new Label("ID");

    
    private TextField txtId = new TextField();

    
    private Label lblPosition = new Label("Position");

    
    private TextField txtPosition = new TextField();

    
    private Label lblName = new Label("Name");

    
    private TextField txtName = new TextField();

    
    private Label lblCity = new Label("City");

    
    private TextField txtCity = new TextField();

    
    private Label lblSearchResultNum = new Label("1000 search results found");

    
    private HBox hBoxNavigationPane = new HBox();

    
    private Button btnFirst = new Button("First");

    
    private Button btnPrevious = new Button("Previous");

    
    private Button btnNext = new Button("Next");

    
    private Button btnLast = new Button("Last");
    
    private ToggleButton tgSearchFilters = new ToggleButton();
    
    private ArrayList<Employee> searchResults;
    private static ArrayList<Employee> employeeList  = new ArrayList();
    
     /**
     * Performs a search
     */
    private ArrayList<Employee> search(String query) {
        
        searchResults = new ArrayList();
        
        if(radCity.isSelected()) {
            ArrayList<Employee> searchResults = new ArrayList();
            
            for(Employee e: employeeList) {
                
                if(e.getCity().equals(query)) {
                    searchResults.add(e);
                }
            }
        
        }
        
        else if(radPosition.isSelected()) {
            ArrayList<Employee> searchResults = new ArrayList();
            
            for(Employee e: employeeList) {
                
                if(e.getPosition().equals(query)) {
                    searchResults.add(e);
                }
            }
        
        }
        
        lblSearchResultNum.setText(searchResults.size() + " search results found for " + query);
        return searchResults;
    }
    
    private void populateTextFields(String id, String name, String city, String position) {
        txtId.setText(id);
        txtName.setText(name);
        txtCity.setText(city);
        txtPosition.setText(position);
    }
    
    /**
     * Sets up event handler for buttons
     */
    public void setUpEventHandlers() {
        btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentIndex == 0) {
                    Alert outOfBounds = new Alert(Alert.AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list.");
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
                if(currentIndex == 0) {
                    Alert outOfBounds = new Alert(Alert.AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list.");
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
                if(currentIndex == employees.size() - 1) {
                    Alert outOfBounds = new Alert(Alert.AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list");
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
                if(currentIndex == employees.size() - 1) {
                    Alert outOfBounds = new Alert(Alert.AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list");
                    return;
                }
                currentIndex = employees.size() - 1;
                Employee employee = navigateEmployeesList(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
        });
        
    }
    
}
