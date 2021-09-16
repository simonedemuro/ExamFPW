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
        Long slotId = null;
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // Controllo se esiste gia uno slot e prendo l'id per farci un update sopra
            String query = "select \"Id\" from available_slot where data = ? and day_part = ?;";
            stmt = conn.prepareStatement(query);
            stmt.setObject(1, reservation.getDate());
            stmt.setString(2, reservation.getTimeslot());
            // mando la query a db 
            set = stmt.executeQuery();
            // fetcho l'id che ho ricevuto
            if (set.next()) {
                slotId = set.getLong("Id");
            }
            
            /* se lo slot esiste già aggiungo i posti richiesti */
            if(slotId != null) {
                query = "update available_slot set n_available = n_available + ? where \"Id\" = ?;";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, reservation.getNumPlaces());
                stmt.setLong(2, slotId);
                stmt.executeUpdate();
            } else {
                /* se lo slot non esisteva prima allora lo inserisco */
                query = "insert into available_slot (data, day_part, n_available) values (?, ?, ?);";
                stmt = conn.prepareStatement(query);
                stmt.setObject(1, reservation.getDate());
                stmt.setString(2, reservation.getTimeslot());
                stmt.setInt(3, reservation.getNumPlaces());

                /* executing and returning success */
                stmt.executeUpdate();
            }
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
            /* tratto separatamente l'ultimo del mese */
            SlotViewModel s = new SlotViewModel(iterDate.getDayOfMonth(), "AM", 0, 0){};
            fullSlots.add(s);
            s = new SlotViewModel(iterDate.getDayOfMonth(), "PM", 0, 0){};
            fullSlots.add(s);
            
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

    public CommonResponse bookSlots(LocalDate fromDate, String fromAmPm, LocalDate toDate, String toAmPm, int numReservedSlots, long userId) {
         // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try {
             // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // Prepearing the query ordered by date and then day part so that it will be AM then PM
            String query = "select \"Id\" , data, day_part, n_available from available_slot where data >= ? and data <= ? order by data, day_part";
            stmt = conn.prepareStatement(query);
            stmt.setObject(1, fromDate);
            stmt.setObject(2, toDate);
            
            LOG.info("getting timeslots in that range :\n" + stmt.toString());
        
            /* fetching dei risultati dalla query nel mio modello */
            set = stmt.executeQuery();
            Queue<Slot> querySlots = new LinkedList<Slot>();
            while(set.next()){
                /* creo lo slot e lo metto nel dizionario */
                Slot slot = new Slot();
                slot.setId(set.getLong("Id"));
                slot.setDate(LocalDate.parse(set.getString("data")));
                slot.setNumPlaces(set.getInt("n_available"));
                slot.setTimeslot(set.getString("day_part"));
                
                querySlots.add(slot);
            }
            
            /* in questa lista metterò gli id degli slot da modificare in modo che 
            *  la query sia una sola update e se fallisse lo farebbe in blocco */
            ArrayList<Long> slotIdToBeReserved = new ArrayList<Long>();
            
            /* ora controllo che ci sia lo spazio per fare l'update
            *  ciclo con iterDate da from a to date +1 perche controllo il before */
            LocalDate iterDate = fromDate;
            while(iterDate.isBefore(toDate.plusDays(1))) {
                /* questa variabile è un work around dato che gli stream in java sono stateless e vogliono solo effectively final :(  */
                LocalDate curr = iterDate;
                /* prendo dal db gli slot per il giorno su cui sto iterando */
                Slot matchingSlotAm = querySlots.stream()
                        .filter(x -> (x.getDate().equals(curr) && 
                                x.getTimeslot().equals("AM")))
                        .findAny()
                        .orElse(null);
                /* prendo anche lo slot PM */
                Slot matchingSlotPm = querySlots.stream()
                        .filter(x -> (x.getDate().equals(curr) &&
                                x.getTimeslot().equals("PM")) )
                        .findAny()
                        .orElse(null);
                
                /* gestisco il caso in cui l'ultimo giorno lo slot da 
                *  considerare sia solo AM*/
                if(iterDate.equals(toDate) && toAmPm.equals("AM")) {
                    /* controllo solo am */
                    /* se non sono stati trovati lancio un eccezione */
                    if (matchingSlotAm == null) {
                        throw new Exception("Errore: all'interno del range di date "
                                + "fornito almeno uno slot " + iterDate.toString() 
                                + " non è stato ancora inserito dall'amministrattore");
                    }
                    /* se non bastano i posti lancio un eccezione */
                    if(matchingSlotAm.getNumPlaces() < numReservedSlots ) {
                        throw new Error("Errore: non sono disponibili sufficienti "
                                + "posti per il range di date selezionate, "
                                + "in particolare nel giorno " + iterDate.toString());
                    }
                    slotIdToBeReserved.add(matchingSlotAm.getId());
                } else {
                    /* se non sono stati trovati lancio un eccezione */
                    if (matchingSlotAm == null || matchingSlotPm == null) {
                        throw new Exception("Errore: all'interno del range di date "
                                + "fornito almeno uno slot " + iterDate.toString() 
                                + " non è stato ancora inserito dall'amministrattore");
                    }
                    /* se non bastano i posti lancio un eccezione */
                    if(matchingSlotAm.getNumPlaces() < numReservedSlots || 
                            matchingSlotPm.getNumPlaces() < numReservedSlots ) {
                        throw new Error("Errore: non sono disponibili sufficienti "
                                + "posti per il range di date selezionate, "
                                + "in particolare nel giorno " + iterDate.toString());
                    }
                    slotIdToBeReserved.add(matchingSlotAm.getId());
                    slotIdToBeReserved.add(matchingSlotPm.getId());
                }
                /* incremento il giorno corrente per continuare il controllo */
                iterDate = iterDate.plusDays(1);
            }
            /* genero stringa con gli id da modificare, non uso il prepared statement per sostituire il valore alla query
            *  ma perché sono sicuro della bontà degli id che mi pervengono incontaminati da query qua sopra */
            String strIds = slotIdToBeReserved.stream().map(x -> x.toString()).collect(Collectors.joining(","));
            
            /* se siamo arrivati qua allora ci sono i posti necessari per effettuare la prenotazione */
            query = String.format("update available_slot " +
                    "set n_available = n_available - ? " +
                    "where \"Id\" in (%s);", strIds);
            
            /* preparo e lancio la query */
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, numReservedSlots);

            stmt.executeUpdate();
            
            String datePeriodText = "Dal: "+ fromDate.toString() + " " + fromAmPm + " Al: " + toDate.toString() + " " + toAmPm;
            /* adesso creo la prenotazione vera e propria */
            query = "insert into reservation (\"Id_user\", num_reserved_slot, reservation_period) " +
                        "values (?, ?, ?);";
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, userId);
            stmt.setInt(2, numReservedSlots);
            stmt.setString(3, datePeriodText);
            
            stmt.executeUpdate();
            return new CommonResponse(true,"Prenotazione avvenuta con successo", null);
                       
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
