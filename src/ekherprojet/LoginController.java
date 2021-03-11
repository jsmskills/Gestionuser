/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ekherprojet;

import Crudservice.personnecrud;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {

    @FXML
    private TextField fmail;
    @FXML
    private TextField fpwd;
//    @FXML
//    private Button gofacebook;
    @FXML
    private Button btlnlogi;
    @FXML
    private Button btnsignup;
    @FXML
    private Label btnfo;
    

   
  
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
private Stage stage ;
    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
    
              String mai = fmail.getText();
            String pwd1 = fpwd.getText();
            personnecrud user = new personnecrud();
          //   String u=user.getPers(mai) ;
            int k=user.recupererrole(mai,pwd1);
           
            if(k==1)
            { 
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("admin.fxml"));
        Parent root=fxml.load();
        fmail.getScene().setRoot(root);
          
           
        AdminController rc=fxml.getController();
        
   
        
        
       
            }
            else   if(k==2)
            { 
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("etudiant.fxml"));
        Parent root=fxml.load();
        fmail.getScene().setRoot(root);
        EtudiantController rc=fxml.getController();
       
            }
                 else    if(k==3)
            { 
            FXMLLoader fxml=new FXMLLoader(getClass().getResource("formateur.fxml"));
        Parent root=fxml.load();
        fmail.getScene().setRoot(root);
        FormateurController rc=fxml.getController();
       
            }
         
         
        
            else{
                       JOptionPane.showMessageDialog(null,"User doesn't exist !!");
          
                
                 }
        
    }

    @FXML
    private void Gotosignup(ActionEvent event) throws IOException {
          FXMLLoader fxml=new FXMLLoader(getClass().getResource("inscri.fxml"));
        Parent root=fxml.load();
        btnsignup.getScene().setRoot(root);
  
        InscriController rc=fxml.getController();
        
    }

    @FXML
    private void Gotoforgot(MouseEvent event) throws IOException {
           FXMLLoader fxml=new FXMLLoader(getClass().getResource("forgot.fxml"));
        Parent root=fxml.load();
        btnfo.getScene().setRoot(root);
        ForgotController rc=fxml.getController();
        
    }

    
}
