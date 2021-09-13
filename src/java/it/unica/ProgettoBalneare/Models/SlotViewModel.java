/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Models;

import java.time.LocalDate;

/**
 * Questa classe mi serve per mostrare gli slot nel front end in modo comodo e
 * nasce con questa esigenza. Si differenzia da Slot in quanto rappresenta
 * il database.
 * @author fpw
 */
public class SlotViewModel {
    public int day;     // giorno del mese come intero
    public String ampm; // per sapere se si tratta di AM o PM
    public int numAm;   // numero di posti relativi alla mattina
    public int numPm;   // numero di posti relativi alla sera

    public int getDay() {
        return day;
    }

    public int getNumAm() {
        return numAm;
    }

    public int getNumPm() {
        return numPm;
    }
    
    

    public SlotViewModel() {
    }

    public SlotViewModel(int day, String ampm, int numAm, int numPm) {
        this.day = day;
        this.ampm = ampm;
        this.numAm = numAm;
        this.numPm = numPm;
    }
    
    
}
