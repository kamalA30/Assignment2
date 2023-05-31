/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.project;


import Model.Users;
import Model.UsersJpaController;
import View.ViewManager;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Yahya
 */
public class JavaFXProject extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       ViewManager.openRegisterPage();
// ****************************** JPA EXAMPLES **************************************      
//       EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankPU");
//       Users u = new Users(null,"yahya99999","sdfsdfds","yahay@fff","male","user");
//       UsersJpaController usersController = new UsersJpaController(emf);
//       usersController.create(u);
//        List<Users> list = usersController.findUsersEntities();
//        list.forEach(e -> System.out.println(e.getUsername() + " ---- " + e.getEmail()));
//        
//        
//       EntityManager em = emf.createEntityManager();
//        Query query = em.createNamedQuery("Users.findByUsername").setParameter("username", "yahya99999");
//        List<Users> results = query.getResultList();
//        results.forEach(e -> System.out.println(e.getUsername()));
//**************************************************************************************
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
