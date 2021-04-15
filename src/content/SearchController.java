/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package content;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author MTH
 */
public class SearchController implements Initializable {
    
//    @FXML
//    private TextField textFieldSearch;

    @FXML
    private Button buttonSearch;

    @FXML
    private RadioButton radioButtonCity;

    @FXML
    private RadioButton radioButtonPosition;

    @FXML
    private TableView<?> tableSearchResults;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnCity;

    @FXML
    private TableColumn<?, ?> columnPosition;

    @FXML
    private Button buttonGoBack;
    
    private static  ArrayList<Employee> employees;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    /**
     * Performs a search
     */
    private ArrayList<Employee> search(String query) {
        
        ArrayList<Employee> searchResults = new ArrayList();
        for(Employee e: employees) {
            if(e.getName().equals(query)) {
                searchResults.add(e);
            }
        }
        return searchResults;
    }
    
    
    
}
