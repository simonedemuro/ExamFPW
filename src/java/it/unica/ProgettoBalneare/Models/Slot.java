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
public class Slot {
    private LocalDate date;
    private String timeslot;
    private int numPlaces;
    private long Id;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public Slot(LocalDate date, String timeslot, int numPlaces) {
        this.date = date;
        this.timeslot = timeslot;
        this.numPlaces = numPlaces;
    }

    public Slot() {
    }

    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public int getNumPlaces() {
        return numPlaces;
    }

    public void setNumPlaces(int numPlaces) {
        this.numPlaces = numPlaces;
    }
    
    
}
