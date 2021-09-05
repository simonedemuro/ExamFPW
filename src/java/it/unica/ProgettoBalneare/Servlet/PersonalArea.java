/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Servlet;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.UserModel;
import it.unica.ProgettoBalneare.Repos.UserRepo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fpw
 */
@WebServlet(name = "PersonalArea", urlPatterns = {"/personalData"})
public class PersonalArea extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        /* prendo dati dall'utente e valido */
        HttpSession session = request.getSession(false);
        String username = (String)session.getAttribute("user");
        if (username == null){
            // gestisci errore
            return;
        }
                
        /* recupero i dati dal db */
        UserRepo userRepo = UserRepo.getInstance();
        CommonResponse res = userRepo.getUserByUsername(username);
        if(!res.result){
            // gestisci errore
            return;
        }
        UserModel dbUser = (UserModel)res.payload;
        
        /* Metto nella reuqest */
        request.setAttribute("dbUser", dbUser);
         /* Mando indietro */
         request.getRequestDispatcher("PersonalArea.jsp").forward(request, response);
         
//        request.setAttribute("isAdmin", dbUser.isIsAdmin());
//        request.setAttribute("Id", dbUser.getId());
//        request.setAttribute("Username", dbUser.getUsername());
//        request.setAttribute("Password", dbUser.getPassword());
//        request.setAttribute("Name", dbUser.getName());
//        request.setAttribute("Surname", dbUser.getSurname());
//        request.setAttribute("Birthday", dbUser.getBirthday());
//        request.setAttribute("FiscalNumber", dbUser.getFiscalNumber());
//        request.setAttribute("sex", dbUser.getSex());
//        request.setAttribute("email", dbUser.getEmail());
//        request.setAttribute("phone", dbUser.getPhone());
//        request.setAttribute("invoiceOptIn", dbUser.isInvoiceOptIn());
       
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
