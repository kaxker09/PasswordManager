/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package manager;
import java.sql.*;

/**
 *
 * @author kacpe
 */
public class Manager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Connection con = null;
        String sql = "select * from User";
        PreparedStatement ps;
        //ResultSet rs;
        
        
            try{
                Class.forName("org.sqlite.JDBC");
                Connection con = DriverManager.getConnection("jdbc:sqlite:Password.db");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                
                
                while(rs.next()){
                   System.out.println(rs.getInt("ID"));  
                }
               
                
            }
            catch(Exception e){
                System.out.println("not succes" + e);
            }
        
    }
    
}
