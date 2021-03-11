/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crudservice;
import Iservice.Iservice;
import Utils.basecnx;
import etities.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author HP
 */
public class personnecrud implements Iservice{
Connection cnx; 
Statement stm;
  public personnecrud()
    {
    cnx = basecnx.getInstance().getConnection();
    }
@Override
public int recupererrole(String mail, String pwd ) throws SQLException {
    int i=0;
 
    Statement ste = cnx.createStatement();
        if (!mail.isEmpty() && !pwd.isEmpty() ) {

            String requete = "SELECT * FROM  user WHERE mail='"+mail+"'and pwd='"+pwd+"'";
            System.out.println("requete = " +requete);
            Statement s = basecnx.getInstance().getConnection().createStatement();
            ResultSet rs = s.executeQuery(requete);
            while (rs.next()){
               // String pw = rs.getString(1);
                String roles=rs.getString("role");
                
                if(roles.equals("admin"))
                {
                    i=1;
                }
                            else    if(roles.equals("etudiant"))
                {
                    i=2;
                }
                                          else                      if(roles.equals("formateur"))
                {
                    i=3;
                }

              
            }
            
           
        }  
   return i;
}

    
    
  


   
    
    @Override
    public void add(Personne p) {
    try {
        Statement stm =cnx.createStatement();
     
       String query="INSERT INTO user (nom,prenom,username,mail,pwd,role) VALUES ("+"'"+p.getNom()+"','"+p.getPrenom()+"','"+p.getUsername()+"','"+p.getMail()+"','"+p.getPwd()+"','"+p.getRole()+"')";
    stm.executeUpdate(query);
        
    } catch (SQLException ex) {
        Logger.getLogger(personnecrud.class.getName()).log(Level.SEVERE, null, ex);
    } 
    }

    @Override
    public List<Personne> AfficherPersonne() throws SQLException{
 
        Statement stm=cnx.createStatement();
        String query ="SELECT*FROM user ";
        
        ResultSet rst =stm.executeQuery(query);
        List<Personne> personnes =new ArrayList<>();
        while(rst.next())
        {
            
            Personne p = new Personne();
            p.setIdu(rst.getInt("idu"));
            p.setNom(rst.getString("nom"));
           p.setPrenom(rst.getString("prenom")); 
           p.setUsername(rst.getString("username")); 
            p.setMail(rst.getString("mail"));
            p.setPwd(rst.getString("pwd"));
            p.setRole(rst.getString("role"));
            personnes.add(p);
            
        }
        return  personnes;
        
    } 
    
@Override
  public void supprimerUser(int id ){
        try{
            
        String requete = "DELETE FROM user WHERE idu=?";
            PreparedStatement stm = basecnx.getInstance().getConnection().prepareStatement(requete);
        stm.setInt(1,id);
        stm.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }

//    @Override
//    public String getMail(String mail) throws SQLException {
//         String a="";
//    Statement ste = cnx.createStatement();
//    ResultSet rs=ste.executeQuery("SELECT * FROM user WHERE mail='"+mail+"' ;");
//    while (rs.next()) { 
//    a=rs.getString("username");
//    }
//    return a; 
//        
//    }
  
     @Override
    public boolean modify(Personne p) throws SQLException {
        // Personne p = new Personne();
//     personnecrud ps = new personnecrud();
//             String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
//                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
//             String mail=upmail.getText();
//      Pattern pattern = Pattern.compile(masque);
//Matcher controler = pattern.matcher(mail);
         String query = "UPDATE `user` SET `nom`=?,`prenom`=?,`username`=?,`mail`=?,`pwd`=?,`role`=? WHERE `idu`=?";
         PreparedStatement statement = basecnx.getInstance().getConnection().prepareStatement(query);
//         Personne  pr = new Personne(upnom.getText(),upprenom.getText(),upuser.getText(),upmail.getText(),uppwd.getText(),upcombo.getValue());
//            ps.modify(pr,mail);

statement.setString(1, p.getNom());
statement.setString(2, p.getPrenom());
statement.setString(3, p.getUsername());
statement.setString(4, p.getMail());
statement.setString(5, p.getPwd());
statement.setString(6, p.getRole());
statement.setInt(7, p.getIdu());
statement.executeUpdate();
  return true;      }
    
    
  @Override
    public String forgotcrudpasss(String mail) throws SQLException {
    
    String a="";    
    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE mail='"+mail+"' ;");
    while (rs.next()) { 
    a=rs.getString("mail");
        System.out.println(a);
    }
    return a;
    }

    @Override
    public String getMailpersonne(String mail) throws SQLException {
           String a="";
    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * FROM `user` WHERE mail='"+mail+"' ;");
    while (rs.next()) { 
    a=rs.getString("mail");
    }
    return a;
    
    }
    
      @Override
    public void updatepassword(String mail,String pwd) throws SQLException {
PreparedStatement pre=cnx.prepareStatement("UPDATE user SET pwd='"+pwd+"' WHERE mail='"+mail+"' ;");
pre.executeUpdate();
    }
    
   
    }

