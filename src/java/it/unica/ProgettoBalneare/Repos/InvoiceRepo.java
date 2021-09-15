/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Repos;

import it.unica.ProgettoBalneare.Db.DatabaseManager;
import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.Slot;
import it.unica.ProgettoBalneare.Models.SlotViewModel;
import it.unica.ProgettoBalneare.Models.TableHandleReservation;
import it.unica.ProgettoBalneare.Models.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 *
 * @author fpw
 */
public class InvoiceRepo {
    private static final Logger LOG = Logger.getLogger(UserRepo.class.getName());
    private static InvoiceRepo instance;

    public InvoiceRepo() {
    }
    
    // get a InvoiceRepo instance by Singleton
    public static InvoiceRepo getInstance(){
        if(instance == null)
            instance = new InvoiceRepo();
        return instance;
    }
    
    public CommonResponse getProcessInvoiceTable(){   
        // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // mi prendo i campi necessari a popolare la tabella mandando in join le prenotazioni con gli utenti
            String query = "select u.username, r.reservation_date, r.reservation_period, num_reserved_slot from reservation as r " +
                            "join \"user\" as u on r.\"Id_user\" = u.\"Id\"";
            stmt = conn.prepareStatement(query);
            
            LOG.info("getting :\n" + stmt.toString());
        
            /* fetching dei risultati dalla query nel mio modello */
            set = stmt.executeQuery();
            ArrayList<TableHandleReservation> dbTbl = new ArrayList<TableHandleReservation>();
            while(set.next()){
                /* creo lo slot e lo metto nel dizionario */
                TableHandleReservation tblItem = new TableHandleReservation();
                tblItem.setUsername(set.getString("username"));
                tblItem.setReservationDate(LocalDate.parse(set.getString("reservation_date")));
                tblItem.setReservationPeriod(set.getString("reservation_period"));
                tblItem.setNumReservedSlot(set.getInt("num_reserved_slot"));
               
                dbTbl.add(tblItem);
            }
            
            /* restituisco il risultato alla servlet */
            return new CommonResponse(true, "Ok", dbTbl);
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
