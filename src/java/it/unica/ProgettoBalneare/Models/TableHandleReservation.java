/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Models;

import java.time.LocalDate;

/**
 *
 * @author fpw
 */
public class TableHandleReservation {
    //u.username, r.reservation_date, r.reservation_period, num_reserved_slot
    private String username;
    private LocalDate reservationDate;
    private String reservationPeriod;
    private int numReservedSlot;
    private long reservationId;

    public TableHandleReservation() {
    }

    public TableHandleReservation(String username, LocalDate reservationDate, String reservationPeriod, int numReservedSlot) {
        this.username = username;
        this.reservationDate = reservationDate;
        this.reservationPeriod = reservationPeriod;
        this.numReservedSlot = numReservedSlot;
    }
    
    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationPeriod() {
        return reservationPeriod;
    }

    public void setReservationPeriod(String reservationPeriod) {
        this.reservationPeriod = reservationPeriod;
    }

    public int getNumReservedSlot() {
        return numReservedSlot;
    }

    public void setNumReservedSlot(int numReservedSlot) {
        this.numReservedSlot = numReservedSlot;
    }
    
    
}
