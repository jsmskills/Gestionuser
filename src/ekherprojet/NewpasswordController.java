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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class NewpasswordController implements Initializable {

    @FXML
    private TextField nwpass;
    @FXML
    private Button updatepwdbutt;
  
     
    @FXML
     private  Label labmail; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
public void setLabmail(String getmail)
    {
    this.labmail.setText(getmail);
    }
    @FXML
    private void updatepassword(ActionEvent event) throws SQLException {
//      String mail=fgmail.getText();
        String pwd=nwpass.getText();
        personnecrud pr= new personnecrud();
    //  String mai= a.getMailpersonne(pwd);
    String ml=labmail.getText();
        pr.updatepassword(ml,pwd);
       
    }
    
}
