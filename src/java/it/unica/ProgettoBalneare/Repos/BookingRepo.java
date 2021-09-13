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
    public CommonResponse getSlotCalendar(LocalDate monthYear){
        /* se cosi non fosse gia forzo ad usare il primo giorno del mese*/
        monthYear = monthYear.withDayOfMonth(1);
               
        // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // Prepearing the query ordered by date and then day part so that it will be AM then PM
            String query = "select \"Id\" , data, day_part, n_available from available_slot where extract('month' from data) = ? order by data, day_part";
            stmt = conn.prepareStatement(query);
            stmt.setObject(1, monthYear.getMonthValue());
            
            LOG.info("getting timeslots :\n" + stmt.toString());
        
            /* fetching dei risultati dalla query nel mio modello */
            set = stmt.executeQuery();
            Queue<Slot> querySlots = new LinkedList<Slot>();
            while(set.next()){
                /* creo lo slot e lo metto nel dizionario */
                Slot slot = new Slot();
                slot.setDate(LocalDate.parse(set.getString("data")));
                slot.setNumPlaces(set.getInt("n_available"));
                slot.setTimeslot(set.getString("day_part"));
                
                querySlots.add(slot);
            }
            /* il mio range di date è dal primo del mese all'ultimo */
            LocalDate iterDate = monthYear;
            LocalDate endDate = monthYear.withDayOfMonth(monthYear.lengthOfMonth());
            
            /* costruisco una collezione contenente tutti gli slot del mese ma vuoti */
            ArrayList<SlotViewModel> fullSlots = new ArrayList<SlotViewModel>();
            String timeAmPm = "AM"; // variabile cambia tra AM e PM
            while(iterDate.isBefore(endDate)) {
                int currDay = iterDate.getDayOfMonth();
                SlotViewModel s = new SlotViewModel(currDay, timeAmPm, 0, 0){};
                fullSlots.add(s);
                
                /* vado avanti di un girno e inverte am pm e viceversa */
                iterDate = iterDate.withDayOfMonth(currDay+1);
                timeAmPm = timeAmPm.equals("AM")?"PM":"AM"; // inverto AM-PM e viceversa
            }
            
            /* Ora faccio una "join" per mettere i valori sugli slot presenti */
            iterDate = monthYear;
            while(!querySlots.isEmpty()) {
                /* tolgo l'elemento da spostare dalla coda */
                Slot dbSlot = querySlots.poll();

                /* prendo l'elemento corrispondente dalla lista completa */
                SlotViewModel matchingSlot = fullSlots.stream()
                        .filter(x -> x.day == dbSlot.getDate().getDayOfMonth())
                        .findAny()
                        .orElse(null);
                
                /* controllo se am o pm e setto il numero di slot disponibili 
                 * nel posto giusto */
                if(dbSlot.getTimeslot().equals("AM")) {
                    matchingSlot.numAm = dbSlot.getNumPlaces();
                } else if(dbSlot.getTimeslot().equals("PM")) {
                    matchingSlot.numPm = dbSlot.getNumPlaces();
                }
            }
            return new CommonResponse(true, "Ok", fullSlots);
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
