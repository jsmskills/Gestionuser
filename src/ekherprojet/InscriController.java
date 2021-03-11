/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ekherprojet;

import Crudservice.personnecrud;
import etities.Personne;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class InscriController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfuser;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfpwd;
    @FXML
    private ImageView btnret;
    @FXML
    private Button btnup;
    @FXML
    private ComboBox<String> combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          combo.getItems().addAll("etudiant", "formateur","admin");      
        
// TODO
    }    
    
    
      @FXML
    private void Gotolog(MouseEvent event) throws IOException {
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        tfmail.getScene().setRoot(root);
        LoginController rc=fxml.getController();
        
        
    }
    
    ToggleGroup role =new  ToggleGroup();
  

    private void Gotolog(ActionEvent event) throws IOException {
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        tfmail.getScene().setRoot(root);
        LoginController rc=fxml.getController();
        
    }
    public boolean verifpassword() throws SQLException {
       Pattern p=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,40})");
       Matcher m=p.matcher(tfpwd.getText());
     if(m.matches())
     { return true ;}
        Alert alert = new  Alert(Alert.AlertType.WARNING);
        alert.setTitle("VALIDATION PASSOWRD");
        alert.setHeaderText(null);
        alert.setContentText("Password must containt at least one (Digital , Lowercase ,Uppcase) and lenght >8");
        alert.showAndWait();
    return false ;
    }
    
    
    @FXML
    private void add(ActionEvent event) throws IOException, SQLException {
        String titre="Welcom to the community";         
        Personne p = new Personne();
        personnecrud ps = new personnecrud();
           String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
           String mail = tfmail.getText();
Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(mail);
          String nom = tfnom.getText();
            String prenom = tfprenom.getText();
             String username = tfuser.getText();
             
            String pwd = tfpwd.getText();
          if(combo.getSelectionModel().getSelectedItem().equals("etudiant")){
            p.setRole("a:1:{i:0;s:11:\"etudiant\";}");
          }else if(combo.getSelectionModel().getSelectedItem().equals("formateur")) {
              p.setRole("a:1:{i:0;s:11:\"formateur\";}");
          }
          else if(combo.getSelectionModel().getSelectedItem().equals("admin")) {
              p.setRole("a:1:{i:0;s:11:\"admin\";}");
          }
        
 
        
           // p.setRole(1);
            String fileNameInServer = null;
       try{
            if (nom.isEmpty()|| prenom.isEmpty() || username.isEmpty() || mail.isEmpty()|| pwd.isEmpty()){
            JOptionPane.showMessageDialog(null,"SVP Remplire tous les text field");
            }
            else{
               
             if(nom.matches("^[a-zA-Z]+$")){
                  if(prenom.matches("^[a-zA-Z]+$")){
                                        
             if(controler.matches()){
           if(verifpassword()){
            Personne  pr = new Personne(nom,prenom,username,mail,pwd,combo.getValue());
            ps.add(pr);
               TrayNotification tray  =new TrayNotification();
               AnimationType type=AnimationType.SLIDE;
               tray.setAnimationType(type);
               tray.setTitle(titre);
               tray.setMessage(nom);
               tray.setNotificationType(NotificationType.SUCCESS);
               tray.showAndDismiss(Duration.millis(3000));
            
            
           
           FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        tfmail.getScene().setRoot(root);
        LoginController rc=fxml.getController();
        
             }
             
                  }else {  JOptionPane.showMessageDialog(null,"le mail n'est pas correct "); 
              TrayNotification tray  =new TrayNotification();
              
                  
             }
            
             }
                  else {
                   JOptionPane.showMessageDialog(null,"le prenom doit contenir seulment des caractére"); 
                  }
             }
             else {
              JOptionPane.showMessageDialog(null,"le nom doit contenir seulment des caractére  ");
             }
            }}catch (ProtocolException ex) {
            Logger.getLogger(InscriController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

  

    
}
