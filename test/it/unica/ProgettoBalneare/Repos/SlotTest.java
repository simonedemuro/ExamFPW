/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Repos;

import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.Slot;
import it.unica.ProgettoBalneare.Models.UserModel;
import java.time.LocalDate;
import java.util.ArrayList;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author fpw
 */
public class SlotTest {
    
    public SlotTest() {
    }
    
    @Test
    public void testSlot() {
        System.out.println("test add slot");
        
        Slot reservation = new Slot(LocalDate.parse("2021-08-02"), "PM", 5);
        CommonResponse result = BookingRepo.getInstance().addSlot(reservation);
        Assert.assertTrue(result.result, "eh no");
    }
    
    @Test
    public void testGetSlots() {
        System.out.println("test get slots");
        
        LocalDate August = LocalDate.parse("2021-08-01");
        CommonResponse result = BookingRepo.getInstance().getSlotCalendar(August);
        
        Assert.assertTrue(result.result && !((ArrayList<Slot>)result.payload).isEmpty(), "eh no");
    }
    
}
