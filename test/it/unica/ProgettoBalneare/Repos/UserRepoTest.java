/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Repos;

import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.UserModel;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Random;

/**
 *
 * @author fpw
 */
public class UserRepoTest {
    
    public UserRepoTest() {
    }
    
    

    @Test
    public void testAddUser() {
        System.out.println("addUser");
        
        UserModel newUser = new UserModel("TestUser" + (new Random()).nextInt(),
                "password", 
                "Ciccio", 
                "Pasticcio", 
                LocalDate.parse("1997-03-25"), 
                "ASDFGHJKLZXCVBNM", 
                'M', 
                "cicciPasticci@alice.it", 
                "3334445555", 
                false);
        
        UserRepo instance = new UserRepo();
        CommonResponse result = instance.addUser(newUser);
        Assert.assertTrue(result.result, result.message);
        
    }
    
     @Test
    public void testGetUser() {
        System.out.println("getting Admin user");
        
        UserRepo instance = new UserRepo();
        CommonResponse result = instance.getUserByUsername("demuro");
        Assert.assertTrue(((UserModel)result.payload).getPassword().equals("65879"), "eh no");
    }
    
}
