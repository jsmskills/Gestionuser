/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ekherprojet;

import etities.Personne;
import Crudservice.personnecrud;
import Utils.basecnx;
import static java.awt.event.PaintEvent.UPDATE;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.PropertyDescriptor.SET;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminController implements Initializable {

    @FXML
    private Button btnaff;
    private Label plaff;
    @FXML
    private TextField frech;
    @FXML
    private TableView<Personne> tabaff;
    @FXML
    private TableColumn<Personne, String> colnom;
    @FXML
    private TableColumn<Personne, String> colprenom;
    @FXML
    private TableColumn<Personne, String> coluser;
    @FXML
    private TableColumn<Personne, String> colmail;
    @FXML
    private TableColumn<Personne, String> colpwd;
    @FXML
    private TableColumn<Personne, String> colrole;
    @FXML
    private TableColumn<Personne, Integer> colidu;
ObservableList<Personne> dataEvent = FXCollections.observableArrayList();
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndel;
   
    @FXML
    private TextField upnom;
    @FXML
    private TextField upprenom;
    @FXML
    private TextField upuser;
    @FXML
    private TextField upmail;
    @FXML
    private TextField uppwd;
    
    @FXML
    private TextField upidu;
    @FXML
    private ComboBox<String> upcombo;
    @FXML
    private ImageView btnout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        upcombo.getItems().addAll("etudiant", "formateur","admin");     
    }    

    @FXML
    private void AfficherPersonne(ActionEvent event) throws SQLException {
       
        colidu.setCellValueFactory(new PropertyValueFactory<>("idu"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        coluser.setCellValueFactory(new PropertyValueFactory<>("username"));
         colmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
          colpwd.setCellValueFactory(new PropertyValueFactory<>("pwd"));
           colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
  
        personnecrud paff =new personnecrud();
       
        dataEvent =FXCollections.observableArrayList(paff.AfficherPersonne());
        
        tabaff.setItems(dataEvent);  
            
    }

    @FXML
    private void rechercher() {
    //    dataEvent.addAll();
        FilteredList<Personne>filteredData=new FilteredList<>(dataEvent,b -> true);
            frech.setOnKeyReleased(e->{
        frech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<?super Personne>)Personne-> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Personne.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (Personne.getUsername().toLowerCase().contains(lowerCaseFilter)) {
					return true; }
                                else if (Personne.getNom().toLowerCase().contains(lowerCaseFilter))  {
					return true; }
				//else if (Reclamation.getIdR().contains(newValue)){
				   // return true;}
				      
				    	 return false; // Does not match.
			});
		});
        SortedList<Personne>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tabaff.comparatorProperty());
        tabaff.setItems(soretedData);
            });
        
    }

   

    @FXML
    private void SupprimerPersonne(ActionEvent event) {
           Alert alert = new Alert(AlertType.NONE);
       alert.setTitle("");
       
      
       
       
        personnecrud P = new personnecrud();
          Personne c=tabaff.getSelectionModel().getSelectedItem();
        if (c==null)
        {
//            String titrefailed="please select user to delet";
//       TrayNotification tray  =new TrayNotification();
//               AnimationType type=AnimationType.POPUP;
//               tray.setAnimationType(type);
//               tray.setTitle(titrefailed);
//              // tray.setMessage(nom);
//                tray.setNotificationType(NotificationType.ERROR);
//               tray.showAndDismiss(Duration.millis(3000));
     // JOptionPane.showMessageDialog(null,"There is nothing selected !");
        }
        else{ 
            String titre="User deleted";
          P.supprimerUser(c.getIdu());
          String nom =c.getNom();
          TrayNotification tray  =new TrayNotification();
               AnimationType type=AnimationType.POPUP;
               tray.setAnimationType(type);
               tray.setTitle(titre);
               tray.setMessage(nom);
                tray.setNotificationType(NotificationType.INFORMATION);
               tray.showAndDismiss(Duration.millis(3000));
      tabaff.setItems(dataEvent);
       
      
      
        }
        
    }
 int index=-1;
    @FXML
    private void getselection(MouseEvent event) {
        index=tabaff.getSelectionModel().getSelectedIndex();
        if(index<=-1)
        {
        return; }
        upcombo.setValue(colrole.getCellData(index).toString()); 
       upidu.setText(colidu.getCellData(index).toString());
        upnom.setText(colnom.getCellData(index).toString());
        upprenom.setText(colprenom.getCellData(index).toString());
        upuser.setText(coluser.getCellData(index).toString());
        upmail.setText(colmail.getCellData(index).toString());
        uppwd.setText(colpwd.getCellData(index).toString());
        
        }
    
     public boolean verifpasswordforupdate() throws SQLException {
       Pattern p=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,40})");
       Matcher m=p.matcher(uppwd.getText());
     if(m.matches())
     { return true ;}
        Alert alert = new  Alert(Alert.AlertType.WARNING);
        alert.setTitle("VALIDATION PASSOWRD");
        alert.setHeaderText(null);
        alert.setContentText("Password must containt at least one (Digital , Lowercase ,Uppcase) and lenght >8");
        alert.showAndWait();
    return false ;
    }

//    @FXML
//    private void Update(ActionEvent event) throws SQLException {
//                  Personne p = new Personne();
//        personnecrud ps = new personnecrud();
//             String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
//                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
//             String mail=upmail.getText();
//      Pattern pattern = Pattern.compile(masque);
//Matcher controler = pattern.matcher(mail);
//      
//      
//        
//      if(upnom.getText().isEmpty()||upprenom.getText().isEmpty()||upuser.getText().isEmpty()||upmail.getText().isEmpty()||uppwd.getText().isEmpty())
//           {JOptionPane.showMessageDialog(null,"please fill all the textfields"); }
//      else{
//      if(upnom.getText().matches("^[a-zA-Z]+$")){
//      if(upprenom.getText().matches("^[a-zA-Z]+$")){
//      if(controler.matches()){
//      if(verifpasswordforupdate()){
//        //Personne  pr = new Personne(upnom.getText(),upprenom.getText(),upuser.getText(),upmail.getText(),uppwd.getText(),upcombo.getValue());
//            ps.modify();
//      
//      }
//      
//          
//      
//      
//      } 
//      
//      }
//      
//      
//      
//      
//      
//      }
//          
//      
//      
//      }
//      
//    
//    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {                //3afat  a7med 
   
         // Personne p = new Personne();
//     personnecrud ps = new personnecrud();
             String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
             String mail=upmail.getText();
      Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(mail);
   
//         Personne  pr = new Personne(upnom.getText(),upprenom.getText(),upuser.getText(),upmail.getText(),uppwd.getText(),upcombo.getValue());
//            ps.modify(pr,mail);


    
      if(upnom.getText().isEmpty()||upprenom.getText().isEmpty()||upuser.getText().isEmpty()||upmail.getText().isEmpty()||uppwd.getText().isEmpty())
           {JOptionPane.showMessageDialog(null,"please fill all the textfields"); }
      else{
      if(upnom.getText().matches("^[a-zA-Z]+$")){
      if(upprenom.getText().matches("^[a-zA-Z]+$")){
      if(controler.matches()){
      if(verifpasswordforupdate()){
          
            personnecrud pc=new personnecrud();
     Personne p=new Personne();
     String idu = upidu.getText();
        p.setIdu(Integer.parseInt(idu));
        
        p.setNom(upnom.getText());
        p.setPrenom(upprenom.getText());
         p.setUsername(upuser.getText());
        p.setMail(upmail.getText());
         p.setPwd(uppwd.getText());
        p.setRole(upcombo.getSelectionModel().getSelectedItem());
        
        pc.modify(p);
//        Personne  pr = new Personne(upnom.getText(),upprenom.getText(),upuser.getText(),upmail.getText(),uppwd.getText(),upcombo.getValue());
//            ps.modify(pr,mail);
//      
    
      
      }
      
      
      } 
      
      }
      
      
      
      }
      
      
      
      
      }
      
    
        
    }

    @FXML
    private void gotohome(MouseEvent event) throws IOException {
           FXMLLoader fxml=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=fxml.load();
        btnout.getScene().setRoot(root);
  
        LoginController rc=fxml.getController();
    }
    }
        
    

   
    
    

