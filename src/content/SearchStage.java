/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import salesfx.SalesFX;

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

    
    private Label lblSearchResultNum = new Label();

    
    private HBox hBoxNavigation = new HBox();

    
    private Button btnFirst = new Button("First");

    
    private Button btnPrevious = new Button("Previous");

    
    private Button btnNext = new Button("Next");

    
    private Button btnLast = new Button("Last");
    
    private HBox hBoxSearchPane = new HBox();
    
    private ToggleGroup tgSearchFilters = new ToggleGroup();
    
    private VBox vBoxIdAndPos = new VBox();
    
    private VBox vBoxNameAndCity = new VBox();
    
    private HBox hBoxEmployeeInfo = new HBox();
    
    private Label lblSearchFilter = new Label("Filter Search By: ");
    
    private HBox hBoxSearchFilter = new HBox();
    
    private AnchorPane rootPane = new AnchorPane();
    
    private ArrayList<Employee> searchResults = new ArrayList();
    private static ArrayList<Employee> employeeList = SalesFX.employeeList;
    private static int currentIndex = 0;
    private static SearchStage searchStage;
    
    private SearchStage() {
        
    }
    
    public static SearchStage loadSearchStage() {
        if(searchStage == null) {
            return new SearchStage();
        }
        return searchStage;
    }
    
    public AnchorPane setUpSearchScene() {
        
        lblSearchFilter.setFont(new Font("arial", 14));
        lblSearchResultNum.setFont(new Font("arial", 14));
        lblId.setFont(new Font("arial", 14));
        lblName.setFont(new Font("arial", 14));
        lblCity.setFont(new Font("arial", 14));
        lblPosition.setFont(new Font("arial", 14));
        
        txtSearch.setPrefHeight(25.0);
        txtSearch.setPrefWidth(227.0);
        
        txtId.setEditable(false);
        txtCity.setEditable(false);
        txtName.setEditable(false);
        txtPosition.setEditable(false);
        
        btnGoBack.setLayoutX(24.0);
        btnGoBack.setLayoutY(29.0);
        btnGoBack.getStyleClass().add("button");
        btnGoBack.getStyleClass().add("navigationButton");
        
        radCity.setToggleGroup(tgSearchFilters);
        radCity.setFont(new Font("arial", 14));
        radPosition.setToggleGroup(tgSearchFilters);
        radPosition.setFont(new Font("arial", 14));
        radCity.setSelected(true);
        
        
        lblSearchResultNum.setLayoutX(114.0);
        lblSearchResultNum.setLayoutY(129.0);
        lblSearchResultNum.setPrefWidth(294);
        
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
        
        btnSearch.getStyleClass().add("button");
        btnSearch.getStyleClass().add("navigationButton");
        
        setUpRootPane();
        
        return rootPane;
    }
    
    /**
     * Returns to main menu
     */
    private void goBackToMainMenu() {
        SalesFX mainMenu = new SalesFX();
        try {
            
            mainMenu.start(new Stage());
        } catch (IOException ex) {
            Logger.getLogger(SearchStage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Sets up the root pane
     */
    private void setUpRootPane() {
        setUpEmployeeInfoPane();
        setUpSearchFilterPane();
        setUpSearchPane();
        setUpNavigationPane();
        setUpEventHandlers();
        
        rootPane.setPrefHeight(400.0);
        rootPane.setPrefWidth(505.0);
        rootPane.getChildren().addAll(btnGoBack, hBoxSearchPane, hBoxSearchFilter, lblSearchResultNum, hBoxEmployeeInfo, hBoxNavigation);
        
    }
    
    /**
     * Sets up the search pane
     */
    private void setUpSearchPane() {
        hBoxSearchPane.setLayoutX(99.0);
        hBoxSearchPane.setLayoutY(29.0);
        hBoxSearchPane.getChildren().addAll(txtSearch, btnSearch);
        
    }
    
    /**
     * Sets up the employee information pane
     */
    private void setUpEmployeeInfoPane() {
        vBoxIdAndPos.setSpacing(10.0);
        vBoxIdAndPos.getChildren().addAll(lblId, txtId, lblPosition, txtPosition);
        
        vBoxNameAndCity.setSpacing(10.0);
        vBoxNameAndCity.getChildren().addAll(lblName, txtName, lblCity, txtCity);
        
        hBoxEmployeeInfo.setLayoutX(24.0);
        hBoxEmployeeInfo.setLayoutY(191.0);
        hBoxEmployeeInfo.setSpacing(160.0);
        hBoxEmployeeInfo.getChildren().addAll(vBoxIdAndPos, vBoxNameAndCity);  
        
    }
    
    /**
     * Sets up the search filter pane
     */
    private void setUpSearchFilterPane() {
        hBoxSearchFilter.setLayoutX(94.0);
        hBoxSearchFilter.setLayoutY(80.0);
        hBoxSearchFilter.setSpacing(30.0);
        hBoxSearchFilter.getChildren().addAll(lblSearchFilter, radCity, radPosition);
    }
    
    /**
     * Sets up the navigation pane
     */
    private void setUpNavigationPane() {
        hBoxNavigation.setLayoutX(70.0);
        hBoxNavigation.setLayoutY(338.0);
        hBoxNavigation.setSpacing(5.0);
        hBoxNavigation.getChildren().addAll(btnFirst, btnPrevious, btnNext, btnLast);
    }
    
     /**
     * Performs a search
     */
    private void search(String query) {
        searchResults.clear();
        
        if(tgSearchFilters.getSelectedToggle().equals(radCity)) {
            for(Employee e: employeeList) {
                
                if(e.getCity().equals(query)) {
                    searchResults.add(e);
                }
            }
        
        }
        
        else if(tgSearchFilters.getSelectedToggle().equals(radPosition)) {
            for(Employee e: employeeList) {
                
                if(e.getPosition().equals(query)) {
                    searchResults.add(e);
                }
            }
        
        }
        
        lblSearchResultNum.setText(searchResults.size() + " search results found for " + query);
        
        if(!searchResults.isEmpty()) {
            Employee employee = searchResults.get(0);
            populateTextFields(employee.getId() + "", employee.getName(), employee.getCity(), employee.getPosition());
        }
        
        else {
            txtId.clear();
            txtName.clear();
            txtCity.clear();
            txtPosition.clear();
        }
   
    }
    
    private void populateTextFields(String id, String name, String city, String position) {
        txtId.setText(id);
        txtName.setText(name);
        txtCity.setText(city);
        txtPosition.setText(position);
    }
    
    /**
     * Sets up event handlers for buttons
     */
    public void setUpEventHandlers() {
        btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentIndex == 0 || searchResults.isEmpty()) {
                    Alert outOfBounds = new Alert(Alert.AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list.");
                    outOfBounds.show();
                    return;
                }
                currentIndex--;
                Employee employee = navigateSearchResults(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
        });
        
        btnFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentIndex == 0 || searchResults.isEmpty()) {
                    Alert outOfBounds = new Alert(Alert.AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list.");
                    outOfBounds.show();
                    return;
                }
                currentIndex  = 0;
                Employee employee = navigateSearchResults(currentIndex);
                txtId.setText(employee.getId() + "");
                txtName.setText(employee.getName());
                txtCity.setText(employee.getCity());
                txtPosition.setText(employee.getPosition());
            }
        });
        
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentIndex == searchResults.size() - 1 || searchResults.isEmpty()) {
                    Alert outOfBounds = new Alert(Alert.AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list");
                    outOfBounds.show();
                    return;
                }
                currentIndex++;
                Employee employee = navigateSearchResults(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
        });
        
        btnLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
                if(currentIndex == searchResults.size() - 1 || searchResults.isEmpty()) {
                    Alert outOfBounds = new Alert(Alert.AlertType.INFORMATION);
                    outOfBounds.setHeaderText("Reached End of List");
                    outOfBounds.setContentText("You have reached the end of the list");
                    outOfBounds.show();
                    return;
                }
                currentIndex = searchResults.size() - 1;
                Employee employee = navigateSearchResults(currentIndex);
                populateTextFields(employee.getId() + "", employee.getName(),
                        employee.getCity(), employee.getPosition());
            }
        });
        
        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { 
               if(txtSearch.getText().equals("")) {
                   Alert nullQuery = new Alert(Alert.AlertType.ERROR);
                   nullQuery.setHeaderText("Nothing to Search");
                   nullQuery.setContentText("Please enter a query to search for.");
                   nullQuery.show();
                   return;
               }
               search(txtSearch.getText());
            }
        });
        
        btnGoBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                goBackToMainMenu();
            }
        });
        
    }
    
    /**
     * Navigates the search list
     * @param index the index of the employee in the list
     */
    private Employee navigateSearchResults(int index) {
        return searchResults.get(index);
    }
    
}
