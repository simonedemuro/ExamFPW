/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Repos;
import it.unica.ProgettoBalneare.Db.DatabaseManager;
import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.UserModel;
import it.unica.ProgettoBalneare.Models.UserTableItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
                utente.setBirthday(LocalDate.parse(set.getString("birthday")));
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
            stmt.setObject(5, newUser.getBirthday());
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
                else if (fieldName == "birthday"){
                    stmt.setObject(parameterNumber, LocalDate.parse((String)clientData.get(fieldName)));
                } else
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
    
    public CommonResponse getUsersTable(String sortField, String sortType) {
        // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // Preparo la query i parametri di sort sono trusted e faccio format sereno
            String query = "select username, role, name, surname, sex, birthday, fiscalnumber, email, phone, invoiceoptin, tot_num_res " +
                    "from view_user_totslot ";
            /* se l'ordinamento è stato riempito allora ordino per questa colonna nell'ordine scelto che se dovesse mancare sarebbe asc di default */
            if(!sortField.equals("")) {
                query = query + String.format("order by %s %s;", sortField, sortType);
            }

            stmt = conn.prepareStatement(query);
            
            LOG.info("Getting the user table :\n" + stmt.toString());

            set = stmt.executeQuery();
            ArrayList<UserTableItem> tblUsers = new ArrayList<UserTableItem>();
            while(set.next()){
                UserTableItem utente = new UserTableItem();
                utente.setUsername(set.getString("username"));
                utente.setRole("role");
                utente.setName(set.getString("name"));
                utente.setSurname(set.getString("surname"));
                utente.setSex(set.getString("sex"));
                utente.setBirthday(LocalDate.parse(set.getString("birthday")));
                utente.setFiscalnumber(set.getString("fiscalnumber"));
                utente.setEmail(set.getString("email"));
                utente.setPhone(set.getString("phone"));
                utente.setInvoiceoptin(set.getString("invoiceoptin"));
                utente.setTot_num_res(set.getInt("tot_num_res"));
                
                tblUsers.add(utente);
                
            }
            return new CommonResponse(true,"Ok",tblUsers);
            
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
