package plus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MyBD {
    
     Connection connect=null;
        Statement s=null;
        PreparedStatement p=null;
         ResultSet r=null;
    
    public MyBD(String lien,String typeDB){
        
       
         
         try {
          
           Class.forName(typeDB);
           connect=DriverManager.getConnection(lien);
           s=connect.createStatement();
           write("PRAGMA foreign_keys = ON;");
   

       }
       catch (Exception e){
           e.printStackTrace();
           
       }
        
    }
    
    
    //read from DB  , avec while(r.next()){ r.getString(nom_Column); }
    public ResultSet read(String query) throws SQLException{
              
          r=s.executeQuery(query);
          
          return r;
    }
    
    
    //write into db ..
    public void write(String query) throws SQLException{
         
        connect.prepareStatement(query).executeUpdate();
    }
    
}
