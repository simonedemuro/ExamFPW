/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Repos;

import it.unica.ProgettoBalneare.Db.DatabaseManager;
import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.InvoiceTableItem;
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
            String query = "select r.\"Id\", u.username, r.reservation_date, r.reservation_period, num_reserved_slot from reservation as r " +
                            "join \"user\" as u on r.\"Id_user\" = u.\"Id\"";
            stmt = conn.prepareStatement(query);
            
            LOG.info("getting :\n" + stmt.toString());
        
            /* fetching dei risultati dalla query nel mio modello */
            set = stmt.executeQuery();
            ArrayList<TableHandleReservation> dbTbl = new ArrayList<TableHandleReservation>();
            while(set.next()){
                /* creo lo slot e lo metto nel dizionario */
                TableHandleReservation tblItem = new TableHandleReservation();
                tblItem.setReservationId(set.getLong("Id"));
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
    
    public CommonResponse getInvoicesByUser(long userId){   
        // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // mi prendo i campi necessari a popolare la tabella mandando in join le prenotazioni con gli utenti
            String query = "select i.\"Id\",  CONCAT(u.name, ' ', u.surname) as ownerFullName, i.price, i.description, i.reservation_date, i.reservation_period, i.num_reserved_slot from invoice as i " +
                            "join \"user\" u on i.\"Id_user\" = u.\"Id\" " +
                            "where u.\"Id\" = ?";
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, userId);
            
            LOG.info("getting invoices for the user to see:\n" + stmt.toString());
        
            /* fetching dei risultati dalla query nel mio modello */
            set = stmt.executeQuery();
            ArrayList<InvoiceTableItem> dbTbl = new ArrayList<InvoiceTableItem>();
            while(set.next()){
                /* creo lo slot e lo metto nel dizionario */
                InvoiceTableItem tblItem = new InvoiceTableItem();
                tblItem.setId(set.getLong("Id"));
                tblItem.setOnwerFulllName(set.getString("ownerfullname"));
                tblItem.setPrice(set.getInt("price"));
                tblItem.setDescription(set.getString("description"));
                tblItem.setReserveationDate(LocalDate.parse(set.getString("reservation_date")));
                tblItem.setReservationPeriod(set.getString("reservation_period"));
                tblItem.setNumReserverSlot(set.getInt("num_reserved_slot"));
               
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
    
    
     public CommonResponse processInvoice(long reservationId, int price, String description){   
        // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            String query = "select r.\"Id\", u.username, u.\"Id\" as \"userId\", u.invoiceoptin,  r.reservation_date, r.reservation_period, num_reserved_slot from reservation as r " +
                            "join \"user\" as u on r.\"Id_user\" = u.\"Id\" where r.\"Id\" = ?";
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, reservationId);
            
            LOG.info("getting the reservation :\n" + stmt.toString());
        
            /* fetching dei risultati dalla query nel mio modello */
            set = stmt.executeQuery();
            TableHandleReservation reservation = new TableHandleReservation();
            long involvedUserId;
            boolean isInvoiceUser;
            if(set.next()){
                /* creo lo slot e lo metto nel dizionario */
                reservation.setReservationId(set.getLong("Id"));
                reservation.setUsername(set.getString("username"));
                reservation.setReservationDate(LocalDate.parse(set.getString("reservation_date")));
                reservation.setReservationPeriod(set.getString("reservation_period"));
                reservation.setNumReservedSlot(set.getInt("num_reserved_slot"));
                involvedUserId = set.getLong("userId");
                isInvoiceUser = set.getBoolean("invoiceoptin");
            } else {
                throw new Exception("Errore: non è stato trovato l'utente che ha effettuato la prenotazione che si vuole processare.");
            }
            
            /* se l'utente desidera ricevere la fattura allora la creo in questo momento */
            if(isInvoiceUser) {
                // prendo i campi necessari a popolare la tabella mandando in join le prenotazioni con gli utenti
                query = "insert into invoice (\"Id_user\", price, description, reservation_date, reservation_period, num_reserved_slot) " +
                            " values (?, ?, ?, ?, ?, ?);";
                stmt = conn.prepareStatement(query);
                stmt.setLong(1, involvedUserId);
                stmt.setInt(2, price);
                stmt.setString(3, description);
                stmt.setObject(4, reservation.getReservationDate());
                stmt.setString(5, reservation.getReservationPeriod());
                stmt.setInt(6, reservation.getNumReservedSlot());

                LOG.info("inserisco fattura :\n" + stmt.toString());

                stmt.executeUpdate();
            }
            
            /* elimino la prenotazione che è divenuta ora fattura o rimossa completamente (Dino 15/06/2021 use case ) */
            query = "delete from reservation where \"Id\" = ?;";
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, reservation.getReservationId());
            stmt.executeUpdate();
                        
            /* restituisco il risultato alla servlet */
            return new CommonResponse(true, "Ok", null);
        }catch(Exception e){
            Logger.getLogger(UserRepo.class.getName()).severe(e.getMessage());
            return new CommonResponse(false,e.getMessage(),e);
        } finally {
            try{ set.close();} catch(Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        }
    }
    
}
