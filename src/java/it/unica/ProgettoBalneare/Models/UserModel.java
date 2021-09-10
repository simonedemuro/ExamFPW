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
public class UserModel {    
    private boolean isAdmin;
    private long Id;
    private String Username;
    private String Password;
    private String Name;
    private String Surname;
    private LocalDate Birthday;
    private String FiscalNumber;
    private char sex;
    private String email;
    private String phone;
    private boolean invoiceOptIn;
    
    public UserModel(String Username, String Password, String Name, String Surname, LocalDate Birthday, String FiscalNumber, char sex, String email, String phone, boolean invoiceOptIn) {
        this.Username = Username;
        this.Password = Password;
        this.Name = Name;
        this.Surname = Surname;
        this.Birthday = Birthday;
        this.FiscalNumber = FiscalNumber;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.invoiceOptIn = invoiceOptIn;
    }

    public UserModel() {
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public LocalDate getBirthday() {
        return Birthday;
    }

    public void setBirthday(LocalDate Birthday) {
        this.Birthday = Birthday;
    }

    public String getFiscalNumber() {
        return FiscalNumber;
    }

    public void setFiscalNumber(String FiscalNumber) {
        this.FiscalNumber = FiscalNumber;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isInvoiceOptIn() {
        return invoiceOptIn;
    }

    public void setInvoiceOptIn(boolean invoiceOptIn) {
        this.invoiceOptIn = invoiceOptIn;
    }

    
    
    
}
