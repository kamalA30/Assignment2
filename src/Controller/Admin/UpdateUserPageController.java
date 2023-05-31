/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.User;
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
public class UpdateUserPageController implements Initializable {
    //old user var to store the user to update
    private User oldUser;
    
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private RadioButton userRadio;
    @FXML
    private ToggleGroup roleGroup;
    @FXML
    private RadioButton adminRadio;
    @FXML
    private Button updateUserBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        //get selected user data from UsersManagmentController updatedUser var
        this.oldUser = Controller.Admin.UsersManagmentController.selectedUserToUpdate;
        
        //set text field's data the same as updatedUser data
        usernameTF.setText(oldUser.getUsername());
        emailTF.setText(oldUser.getEmail());
        passwordTF.setText(oldUser.getPassword());
        
        if (oldUser.getGender().equals("female")) {
            genderGroup.selectToggle(femaleRadio);
        }
        
        if (oldUser.getRole().equals("admin")) {
            roleGroup.selectToggle(adminRadio);
        }
    }    

    @FXML
    private void updateUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        //get the new data from text field's and store it in new user object
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String email = emailTF.getText();
        String gender = ((RadioButton)genderGroup.getSelectedToggle()).getText();
        String role = ((RadioButton)roleGroup.getSelectedToggle()).getText();
        
        //make an new user object having this data
        User newUser = new User(username,password,email,gender,role);
        
        //set the new user id the same as the old user
        newUser.setId(oldUser.getId());
        
        // update the user in database by update method
        newUser.update();
        
        //close the update stage using our global stage var in UsersManagmentController and show an alert
        Controller.Admin.UsersManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User updated");
        alert.setContentText("User updated");
        alert.showAndWait();
    }

    //close the update stage using our global stage var in UsersManagmentController
    @FXML
    private void cancelCreation(ActionEvent event) {
       Controller.Admin.UsersManagmentController.updateStage.close(); 
    }
    
}
