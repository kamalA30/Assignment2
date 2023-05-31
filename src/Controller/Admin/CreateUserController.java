/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.User;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class CreateUserController implements Initializable {

    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button saveNewUserBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private ToggleGroup roleGroup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    
    // save new user to the database table by our save method in user model
    @FXML
    private void saveNewUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        // get data from all text fields 
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String email = emailTF.getText();
        String gender = ((RadioButton)genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton)roleGroup.getSelectedToggle()).getText();
        // make an user object having this data
        User user = new User(username,password,email,gender,role);
        // save the user in database by save method
        user.save();
        
        //after saving should return to the back scene and show an alert
        ViewManager.adminPage.changeSceneToUsersManagment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User inserted");
        alert.setContentText("User inserted");
        alert.showAndWait();
    }

    @FXML
    private void cancelUserCreation(ActionEvent event) {
                ViewManager.adminPage.changeSceneToUsersManagment();

    }
    
}
