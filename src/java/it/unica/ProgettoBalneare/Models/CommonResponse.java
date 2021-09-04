/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Models;

/**
 * Fornisce una risposta comune per gestire i poput di errore
 * @author fpw
 */
public class CommonResponse {
    
    public boolean result;
    public String message;
    public Object payload; 

    public CommonResponse(boolean result, String message, Object payload) {
        this.result = result;
        this.message = message;
        this.payload = payload;
    }

    public CommonResponse() {
    }
    

}
