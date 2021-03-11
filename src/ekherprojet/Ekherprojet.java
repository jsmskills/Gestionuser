/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ekherprojet;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Ekherprojet extends Application {
    private Stage stage ;
    
    @Override
    public void start(Stage stage) throws Exception {
     try {
            Parent root = FXMLLoader
        .load(getClass().getResource("login.fxml"));
            
            Scene scene;
    scene = new Scene(root);
    stage.getIcons().add(new Image("images/home.png"));
            stage.setTitle("e-skills");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
