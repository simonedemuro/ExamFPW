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
public class InvoiceTableItem {
    private long Id;
    private String onwerFulllName;
    private int price;
    private String description;
    private LocalDate reserveationDate;
    private String reservationPeriod;
    private int numReserverSlot;

    public InvoiceTableItem() {
    }

    public InvoiceTableItem(long Id, String onwerFulllName, int price, String description, LocalDate reserveationDate, String reservationPeriod, int numReserverSlot) {
        this.Id = Id;
        this.onwerFulllName = onwerFulllName;
        this.price = price;
        this.description = description;
        this.reserveationDate = reserveationDate;
        this.reservationPeriod = reservationPeriod;
        this.numReserverSlot = numReserverSlot;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getOnwerFulllName() {
        return onwerFulllName;
    }

    public void setOnwerFulllName(String onwerFulllName) {
        this.onwerFulllName = onwerFulllName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReserveationDate() {
        return reserveationDate;
    }

    public void setReserveationDate(LocalDate reserveationDate) {
        this.reserveationDate = reserveationDate;
    }

    public String getReservationPeriod() {
        return reservationPeriod;
    }

    public void setReservationPeriod(String reservationPeriod) {
        this.reservationPeriod = reservationPeriod;
    }

    public int getNumReserverSlot() {
        return numReserverSlot;
    }

    public void setNumReserverSlot(int numReserverSlot) {
        this.numReserverSlot = numReserverSlot;
    }
    
    
}
