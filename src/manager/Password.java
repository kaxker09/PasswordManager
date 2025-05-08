/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;

import javax.crypto.spec.GCMParameterSpec;

/**
 *
 * @author kacpe
 */
public class Password {
    private int id_pass;
    private String login;
    private String password;
    private String website;
    private int id;
    private String salt;
    private String vector;
    
    public int getId(){
        return id;
}
    public void setId(int id){
        this.id = id;
    }       
    
    public String getLogin(){
        return login;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public String getVector(){
        return vector;
    }
    
    public void setVector(String vector){
        this.vector = vector;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getWebsite(){
        return website;
    }
    
    public void setWebsite(String website){
        this.website = website;
    }
     public String getSalt(){
        return salt;
    }
    
    public void setSalt(String salt){
        this.salt = salt;
    }
    
    
    public Password (){}
    public Password(String login, String password, String website, int id, String salt){
        this.login = login;
        this.password = password;
        this.website = website;   
        this.id = id;
        this.salt = salt;
        
    }
    
     public Password(String login, String password, String website, String salt){
        this.login = login;
        this.password = password;
        this.website = website;   
        this.salt = salt;
        
    }
    
     
     public Password(String login, String password, String website,int id, String salt, String vector){
        this.vector = vector;
        this.login = login;
        this.password = password;
        this.website = website;   
        this.salt = salt;
        this.id = id;
    }
   
}
