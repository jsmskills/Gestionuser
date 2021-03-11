/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;
import etities.Personne;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public interface Iservice {
public int recupererrole(String mai, String pwd1 ) throws SQLException;
   public String getMailpersonne(String mail)throws SQLException;
  public void updatepassword(String mail,String pass) throws SQLException;
  public String forgotcrudpasss(String mail) throws SQLException;
    public void add(Personne p);
    public  List <Personne>AfficherPersonne()throws SQLException;
      public void supprimerUser(int id );
      public boolean modify(Personne p) throws SQLException ;
    //  public boolean verifpassword(String username, String pwd ) throws SQLException;
}
