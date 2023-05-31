/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.User;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class UsersManagmentController implements Initializable {
    
    // 2 static vars to transfer data from this stage controller to the update stage controller
    public static User selectedUserToUpdate;
    public static Stage updateStage;
    ////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewUserBtn;
    @FXML
    private Button showAllUsersBtn;
    @FXML
    private Button updateSelectedUserBtn;
    @FXML
    private Button deleteSelectedUserBtn;
    @FXML
    private TableView<User> usersTableView;
    @FXML
    private TableColumn<User, Integer> idCol;
    @FXML
    private TableColumn<User, String> usernameCol;
    @FXML
    private TableColumn<User, String> passwordCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, String> genderCol;
    @FXML
    private TableColumn<User, String> roleCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///////////////////////////////////////////////////////////////////////////
        // make maping between TableView column's and user object attributes .   //
        // TableView { idCol,usernameCol,passwordCol,emailCol,genderCol,roleCol }//
        //              |       |           |           |       |        |       //
        // UserObject{ id   ,username   ,password   ,email   ,gender   ,role    }//
        ///////////////////////////////////////////////////////////////////////////
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory("password"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
        roleCol.setCellValueFactory(new PropertyValueFactory("role"));
    }    
    
    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        // you are in this scence
    }
    
    //change scence to Accounts Managment scene
    @FXML
    private void showAccountsPage(ActionEvent event) {
         ViewManager.adminPage.changeSceneToAccountsManagment();
    }
    
    
    //change scence to Operations scene
    @FXML
    private void showOperationsPage(ActionEvent event) {
        //scene not complete yet
    }
    
    
    //change scence to create new user scene
    @FXML
    private void showUserCreationPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToCreateUser();
    }

    @FXML
    // show all users from users table in users TableView using our users model ( getAllUsers method )
    private void showAllUsers(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<User> usersList =
      FXCollections.observableArrayList(User.getAllUsers());
      
      usersTableView.setItems(usersList);  
    }

    //show updata stage    
    @FXML
    private void updateSelectedUser(ActionEvent event) throws IOException {
        //check if there is an user selected from the TableView
        if(usersTableView.getSelectionModel().getSelectedItem() != null){
        //store the selected user from the TableView in our global var user selectedUserToUpdate   
        selectedUserToUpdate = usersTableView.getSelectionModel().getSelectedItem();
        //load update page fxml
        FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateUserPage.fxml"));
        Parent rootUpdate = loaderUpdate.load();     
        Scene updateUserScene = new Scene(rootUpdate); 
        //store loaded fxml in our global stage updateStage and show it
        updateStage = new Stage();
        updateStage.setScene(updateUserScene);
        updateStage.setTitle("Update user " + selectedUserToUpdate.getUsername() );
        updateStage.show();
        }
    }

    @FXML
    private void deleteSelectedUser(ActionEvent event) throws SQLException {
        //check if there is an user selected from the TableView
        if(usersTableView.getSelectionModel().getSelectedItem() != null){
            //store the selected user from the TableView in new user object
            User selectedUser = usersTableView.getSelectionModel().getSelectedItem();
            
            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this user ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    //delete the selected user from database table using delete method in our User model
                    selectedUser.delete();
                } catch (SQLException ex) {
                    Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UsersManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
            deletedSuccessAlert.setTitle("User deleted");
            deletedSuccessAlert.setContentText("User deleted");
            deletedSuccessAlert.show();  
            }
            });
        
        }else{
        Alert warnAlert = new Alert(Alert.AlertType.WARNING);
        warnAlert.setTitle("Select an user");
        warnAlert.setContentText("Please select an user from the table view");
        warnAlert.show();  
        }
    }
    
}
