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
import java.util.Map;

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
    
    public CommonResponse getUserByUsername(String username){
        // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // Prepearing the query
            String query = 
                    "SELECT username, password, name, surname, birthday, fiscalnumber, sex, email, phone, invoiceoptin, \"Id\", \"isAdmin\" FROM public.\"user\" WHERE username = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            
            LOG.info("Getting the user:\n" + username + stmt.toString());
        
            set = stmt.executeQuery();
            if(set.next()){
                UserModel utente = new UserModel();
                utente.setUsername(set.getString("username"));
                utente.setPassword(set.getString("password"));
                utente.setName(set.getString("name"));
                utente.setSurname(set.getString("surname"));
                utente.setBirthday(set.getString("birthday"));
                utente.setFiscalNumber(set.getString("fiscalnumber"));
                utente.setSex(set.getString("sex").charAt(0));
                utente.setEmail(set.getString("email"));
                utente.setPhone(set.getString("phone"));
                utente.setInvoiceOptIn(set.getBoolean("invoiceoptin"));
                utente.setId(set.getInt("Id"));
                utente.setIsAdmin(set.getBoolean("IsAdmin"));
                
                return new CommonResponse(true,"Ok", utente);
            }else{
                return new CommonResponse(false,"user non trovato", null);
            }
        }catch(SQLException e){
            Logger.getLogger(UserRepo.class.getName()).severe(e.getMessage());
            return new CommonResponse(false,e.getMessage(),e);
        } finally {
            try{ set.close();} catch(Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        }
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
    
    public CommonResponse updateUser(long Id, Map<String, Object> clientData) {
        /* Controllo i parametri passati e creo la query */
        // Creo solo la stringa query
        String query = "UPDATE \"user\" SET";
        for (String fieldName : clientData.keySet()) {
            query += String.format(" %s = ? ,",fieldName);
            // clientData.get(fieldName));
        }
        query = query.substring(0, query.length() - 1); // tolgo l'ultima virgola di troppo
        query += " WHERE \"Id\" = ?;";
        

        // Preparo la connessione
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // Prepearing the query
            stmt = conn.prepareStatement(query);

            /* aggiungo alla query i parametri che voglio updatare
            uso un contatore per avere anche il numero del parametro */
            int parameterNumber = 1;
            for (String fieldName : clientData.keySet()) {
                /* facciamo un eccezione per i campi che non sono stringa */
                if (fieldName == "invoiceoptin")
                    stmt.setBoolean(parameterNumber, Boolean.parseBoolean((String)clientData.get(fieldName)));
                else
                    stmt.setString(parameterNumber, (String)clientData.get(fieldName));
                parameterNumber++;
            }
            /* Importante aggiungo l'id alla fine che sono sicuro sia sano
            *  perché proviene dalla query fatta con getUser in PersonlArea.java*/
            stmt.setLong(parameterNumber, Id);
            
            // Lancio la query sul database e rispondo true
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
