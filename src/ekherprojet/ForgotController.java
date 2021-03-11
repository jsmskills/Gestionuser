/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ekherprojet;

import Crudservice.personnecrud;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import com.email.durgesh.Email;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javax.mail.MessagingException;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class ForgotController implements Initializable {

    @FXML
    private TextField fgmail;
    @FXML
    private Button fgentry;
    @FXML
    private Button vrfbutt;
    @FXML
    private TextField vrfcodetxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
int randomcode;


   @FXML
    private void SendCodeMail(ActionEvent event) throws SQLException, MessagingException, UnsupportedEncodingException, IOException {
   String getmail=fgmail.getText();
        String mai = fgmail.getText();
    personnecrud p = new personnecrud();
    //String a=p.forgotcrudpasss(mai);
    Random rand =new Random();
    randomcode=rand.nextInt(99999999);
   String a=p.getMailpersonne(mai);
    if(p.getMailpersonne(mai)!="")
    {
    Email email = new Email("serviceclient619@gmail.com","25163816");
        email.setFrom("serviceclient@gmail.com", "E-skills");
        email.setSubject("Get your password");
        email.setContent("<h1>This is your verification code :</h1>"+randomcode, "text/html");
        email.addRecipient(mai);
        email.send();
        
      
      
    }
    else{
    JOptionPane.showMessageDialog(null,fgmail+" doesn't existing, PLEASE SIGN UP");
    FXMLLoader fxml=new FXMLLoader(getClass().getResource("inscri.fxml"));
        Parent root=fxml.load();
        fgmail.getScene().setRoot(root);
        InscriController rc=fxml.getController();
    }
    }

    @FXML
    private void VerifierCode(ActionEvent event) {
        String mail=fgmail.getText();
        String pwd=vrfcodetxt.getText();
        personnecrud cn=new personnecrud();
        if(Integer.valueOf(vrfcodetxt.getText())==randomcode)
        {
            try {
             
                 FXMLLoader fxml=new FXMLLoader(getClass().getResource("newpassword.fxml"));
        Parent root=fxml.load();
        vrfbutt.getScene().setRoot(root);
        NewpasswordController rc=fxml.getController();
              
            } catch (Exception e) {
            }
        }
    }
    
    
  //  String getmail=fgmail.getText();
   
    
}
