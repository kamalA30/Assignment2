/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Yahya
 */
public class RegisterPage extends Stage {
    final CheckBox darkModeCheck;
    final Label usernameLabel;
    final TextField usernameTF;

    final Label passwordLabel;
    final PasswordField passwordTF;

    final Label passwordConfirmLabel;
    final PasswordField passwordConfirmTF;

    final Label emailLabel;
    final TextField emailTF;

    final Label groupLabel;
    final ToggleGroup genderGroup;
    final RadioButton maleGender;
    final RadioButton femaleGender;

    final Button registerBtn;
    final GridPane grid;
    final Label stackPaneLabel;
    final StackPane stackPane;
    final HBox hbox;

    public RegisterPage() {
        usernameLabel = new Label("User name : ");
        usernameTF = new TextField();

        passwordLabel = new Label("Password : ");
        passwordTF = new PasswordField();

        passwordConfirmLabel = new Label("Confirm Password : ");
        passwordConfirmTF = new PasswordField();

        emailLabel = new Label("Email : ");
        emailTF = new TextField();
        
        groupLabel = new Label("Gender : ");
        genderGroup = new ToggleGroup();
        maleGender = new RadioButton("Male");
        femaleGender = new RadioButton("Female");
        maleGender.setToggleGroup(genderGroup);
        femaleGender.setToggleGroup(genderGroup);
        HBox genderHbox = new HBox(10,maleGender,femaleGender);
        
        registerBtn = new Button("Enter As Guest");
        registerBtn.setCursor(Cursor.HAND);
        registerBtn.getStyleClass().add("btn");
        registerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ViewManager.openAdminPage();
                    ViewManager.closeRegisterPage();
                } catch (IOException ex) {
                    Logger.getLogger(RegisterPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        darkModeCheck = new CheckBox("Dark Mode");
        darkModeCheck.setPadding(new Insets(0, 0, 20, 0));
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.add(darkModeCheck, 0, 0);
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameTF, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordTF, 1, 2);
        grid.add(passwordConfirmLabel, 0, 3);
        grid.add(passwordConfirmTF, 1, 3);
        grid.add(emailLabel, 0, 4);
        grid.add(emailTF, 1, 4);
        grid.add(groupLabel, 0, 5);
        grid.add(genderHbox, 1, 5);
        grid.add(registerBtn, 1, 6, 2, 2);
        grid.setAlignment(Pos.CENTER);
        stackPaneLabel = new Label("Sign up \n Welcome to our Bank System");
        stackPane = new StackPane(stackPaneLabel);
        stackPane.setMinWidth(200);
        stackPane.setMinHeight(200);
        hbox = new HBox(30, stackPane, grid);
        
        darkModeCheck.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(hbox.getStyleClass().isEmpty()){
                    hbox.getStyleClass().add("darkMode");
                }else{
                    hbox.getStyleClass().clear();
                }
            }
    
    });
        
        StackPane root = new StackPane();
        root.getChildren().add(hbox);
        
        Scene scene = new Scene(root, 740, 440);
        stackPane.getStyleClass().add("stackPane");
        scene.getStylesheets().add("/css/style.css");

        this.setScene(scene);
        this.setTitle("Rigester Page");
        this.setResizable(false);
        this.show();
    }

}
