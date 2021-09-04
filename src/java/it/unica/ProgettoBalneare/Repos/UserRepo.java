/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Repos;
import it.unica.ProgettoBalneare.Db.DatabaseManager;
import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fpw
 */
public class UserRepo {
    private static final Logger LOG = Logger.getLogger(UserRepo.class.getName());
    private static UserRepo instance;
    
    public UserRepo() {
    }
    
    // get a UserRepo instance by Singleton
    public static UserRepo getInstance(){
        if(instance == null)
            instance = new UserRepo();
        return instance;
    }
    
    
    public CommonResponse addUser(UserModel newUser){
        // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // Prepearing the query
            String query = 
                    "INSERT INTO public.\"user\"(" +
            " username, password, name, surname, birthday, fiscalnumber, sex, email, phone, invoiceoptin)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, newUser.getUsername());
            stmt.setString(2, newUser.getPassword());
            stmt.setString(3, newUser.getName());
            stmt.setString(4, newUser.getSurname());
            stmt.setString(5, newUser.getBirthday());
            stmt.setString(6, newUser.getFiscalNumber());
            stmt.setString(7, newUser.getSex()+"");
            stmt.setString(8, newUser.getEmail());
            stmt.setString(9, newUser.getPhone());
            stmt.setBoolean(10, newUser.isInvoiceOptIn());

            LOG.info("Inserting a new user:\n" + stmt.toString());
        
            stmt.executeUpdate();
            return new CommonResponse(true,null,null);
        }catch(SQLException e){
            Logger.getLogger(UserRepo.class.getName()).severe(e.getMessage());
            return new CommonResponse(false,e.getMessage(),e);
        } finally {
            try{ set.close();} catch(Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        }
    }
     
}
