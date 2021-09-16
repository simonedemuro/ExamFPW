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
public class UserTableItem {
    private String username;
    private String role;
    private String name;
    private String surname;
    private String sex;
    private LocalDate birthday;
    private String fiscalnumber;
    private String email;
    private String phone;
    private String invoiceoptin;
    private int tot_num_res;

    public UserTableItem() {
    }

    public UserTableItem(String username, String role, String name, String surname, String sex, LocalDate birthday, String fiscalnumber, String email, String phone, String invoiceoptin, int tot_num_res) {
        this.username = username;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.birthday = birthday;
        this.fiscalnumber = fiscalnumber;
        this.email = email;
        this.phone = phone;
        this.invoiceoptin = invoiceoptin;
        this.tot_num_res = tot_num_res;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getFiscalnumber() {
        return fiscalnumber;
    }

    public void setFiscalnumber(String fiscalnumber) {
        this.fiscalnumber = fiscalnumber;
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

    public String getInvoiceoptin() {
        return invoiceoptin;
    }

    public void setInvoiceoptin(String invoiceoptin) {
        this.invoiceoptin = invoiceoptin;
    }

    public int getTot_num_res() {
        return tot_num_res;
    }

    public void setTot_num_res(int tot_num_res) {
        this.tot_num_res = tot_num_res;
    }
    
    
    
}
