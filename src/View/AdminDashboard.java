/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Yahya
 */
public class AdminDashboard extends Stage{
    
    // All Scenes that AdminDashboard page have
    private Scene usersManagmentScene;
    private Scene createUserScene;
    private Scene accountsManagmentScene;

    //private Scene operationsScene;

    public AdminDashboard() throws IOException {
        
        //load UserManagment FXML File in UsersManagment Scene
        FXMLLoader usersManagmentLoader = new FXMLLoader(getClass().getResource("AdminFXML/UsersManagment.fxml"));
        Parent usersManagmentRoot = usersManagmentLoader.load();     
        usersManagmentScene = new Scene(usersManagmentRoot);
        
        
        //load CreateUser FXML File in CreateUser Scene
        FXMLLoader createUserLoader = new FXMLLoader(getClass().getResource("AdminFXML/CreateUser.fxml"));
        Parent createUserRoot = createUserLoader.load();     
        createUserScene = new Scene(createUserRoot);
        
        //load AccountsManagment FXML File in AccountsManagment Scene
        FXMLLoader accountsLoader = new FXMLLoader(getClass().getResource("AdminFXML/AccountsManagment.fxml"));
        Parent accountsRoot = accountsLoader.load();     
        accountsManagmentScene = new Scene(accountsRoot);
        
        // Set Main Scene in Admin Dasboard ( UsersManagment Scene )
        this.setScene(usersManagmentScene);
        this.setTitle("Admin Dashboard");
        
        
    }
    public void changeSceneToCreateUser(){
        this.setScene(createUserScene);
    }
    public void changeSceneToUsersManagment(){
        this.setScene(usersManagmentScene);
    }
    
    public void changeSceneToAccountsManagment(){
        this.setScene(accountsManagmentScene);
    }
    
    
    
}
