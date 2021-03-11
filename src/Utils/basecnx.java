/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus X550V
 */
public class basecnx {
   
    String URL = "jdbc:mysql://127.0.0.1:3306/e-skills";
     String LOGIN = "root";
     String PWD = "";
 static basecnx instance =null;
 private Connection cnx;
    private basecnx() {
         try {
             cnx=DriverManager.getConnection(URL,LOGIN,PWD);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println("pas de connexion");
         }
    }
    
    public Connection  getConnection()
    {
    return cnx;
    }
    
    public static basecnx getInstance()
    {if(instance==null)
        instance=new basecnx();
    return instance;
    }   
  
}
