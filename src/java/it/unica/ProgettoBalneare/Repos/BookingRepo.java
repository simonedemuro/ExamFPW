/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Repos;

import it.unica.ProgettoBalneare.Db.DatabaseManager;
import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.Slot;
import it.unica.ProgettoBalneare.Models.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 *
 * @author fpw
 */
public class BookingRepo {
    private static final Logger LOG = Logger.getLogger(UserRepo.class.getName());
    private static BookingRepo instance;
    
    public BookingRepo() {
    }
    
    // get a BookingRepo instance by Singleton
    public static BookingRepo getInstance(){
        if(instance == null)
            instance = new BookingRepo();
        return instance;
    }
    // 
    public CommonResponse addSlot(Slot reservation){
        // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // Prepearing the query
            String query = "insert into available_slot (data, day_part, n_available) values (?, ?, ?);";
            stmt = conn.prepareStatement(query);
            stmt.setObject(1, reservation.getDate());
            stmt.setString(2, reservation.getTimeslot());
            stmt.setInt(3, reservation.getNumPlaces());
            
            LOG.info("adding timeslot :\n" + stmt.toString());
        
            /* executing and returning success */
            stmt.executeUpdate();
            return new CommonResponse(true,"Ok", null);
            
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
