/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;



/**
 *
 * @author kacpe
 */
public class ConnectionToDb {
    
    
    public static Connection getConnection () throws ClassNotFoundException{
     Connection connection = null;
    
    try{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:Password.db");
        
}
    catch (SQLException ex) {
            Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return connection; 
    }
    
    
    public void saveSignUp(User user) throws ClassNotFoundException{
        String sql = "Insert into user (username, password, salt) values (?,?,?)";
        
        try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSalt());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex) {
                Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public int getUserId(String username) throws ClassNotFoundException{
        String sql = "select id from `user` where `username` = ?";
        
        ResultSet rs;
        
          try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1,username); 
            rs = ps.executeQuery();
            if(rs.next()){
                int idDb = rs.getInt("ID");
                return idDb;
            }
            ps.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return 0;
    }
    
    /**
     *
     * @param username
     * @return
     * @throws ClassNotFoundException
     */
    public User logIn(String username)throws ClassNotFoundException{
        String sql = "select *from  `user` where `username` = ?";
        
       
        ResultSet rs;
        
        try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1,username); 
            rs = ps.executeQuery();
            if(rs.next()){
                int idDb = rs.getInt("ID");
                String usernameDb = rs.getString("username");
                String passwordDb = rs.getString("password");
                String saltDb = rs.getString("salt");
                return new User(idDb, usernameDb, passwordDb, saltDb);
            }
            ps.close();
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public void addPass(Password pass)throws ClassNotFoundException {
        
        String sql = "insert into pass (login, password, website, id, salt, vector) values (?,?,?,?,?,?)";
        
        try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)){
            
            //System.out.println(pass.getLogin());
            
            ps.setString(1, pass.getLogin());
            ps.setString(2, pass.getPassword());
            ps.setString(3, pass.getWebsite());
            ps.setInt(4, pass.getId());
            ps.setString(5, pass.getSalt());
            ps.setString(6, pass.getVector());
            ps.executeUpdate();
            ps.close();
            
        }
        catch (SQLException ex) {
                Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        public List<Password> table(int id)throws ClassNotFoundException {
            String sql = "select website, login, password, salt, vector from `pass` where id =  ?";
            List<Password> passwords = new ArrayList<>();
            ResultSet rs;
             
            try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                String websiteDb = rs.getString("website");
                String loginDb = rs.getString("login");
                String passwordDb = rs.getString("password");
                String salt = rs.getString("salt");
                String vector = rs.getString("vector");
                Password pass = new Password(loginDb, passwordDb, websiteDb,id, salt, vector);
                passwords.add(pass);
            } 
            ps.executeQuery();
            ps.close();
            
        }
        catch (SQLException ex) {
                Logger.getLogger(ConnectionToDb.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            return passwords;
        }
        
        
    
}
