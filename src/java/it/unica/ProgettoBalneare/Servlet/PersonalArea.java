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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        try {
            response.setContentType("text/html;charset=UTF-8");

            /* prendo dati dall'utente e valido */
            HttpSession session = request.getSession(false);
            String username = session != null ? (String) session.getAttribute("user") : null;
            if (username == null) {
                throw new Exception("Errore: si sta provando ad entrare nella sezione personale senza essere loggati, probabilmente la sessione è scaduta");
            }

            /* recupero i dati dal db */
            UserRepo userRepo = UserRepo.getInstance();
            CommonResponse res = userRepo.getUserByUsername(username);
            if (!res.result) {
                throw new Exception("Errore: non è stato possibile recuperare l'utente " + username + " ci scusiamo");
            }
            UserModel dbUser = (UserModel) res.payload;

            /* Se viene settata l'azione di fare update processo la richiesta */
            Object action = request.getParameter("action");
            if (action != null && ((String) action).equals("update")) {
                // LinkedHashMap mi serve per avere la garanzia di mantenimento dell'ordine
                Map<String, Object> clientData = new LinkedHashMap<String, Object>();

                /* rinomino manualmente i campi anzi che passare quelli ricevuti 
            dalla pagina in modo da non permettere Sql Injection 💉💉💉💉💉💉💉💉💉💉💉💉💉💉💉*/
                if (request.getParameter("Fuser")!= null){
                    clientData.put("username", request.getParameter("Fuser"));
                }
                if (request.getParameter("Fpass1") != null) {
                    clientData.put("password", request.getParameter("Fpass1"));
                }
                if (request.getParameter("Fname") != null) {
                    clientData.put("name", request.getParameter("Fname"));
                }
                if (request.getParameter("Fsurn") != null) {
                    clientData.put("surname", request.getParameter("Fsurn"));
                }
                if (request.getParameter("Fbirt") != null) {
                    clientData.put("birthday", request.getParameter("Fbirt"));
                }
                if (request.getParameter("Fcode") != null) {
                    clientData.put("fiscalnumber", request.getParameter("Fcode"));
                }
                if (request.getParameter("Fsex") != null) {
                    clientData.put("sex", request.getParameter("Fsex"));
                }
                if (request.getParameter("Fmail") != null) {
                    clientData.put("email", request.getParameter("Fmail"));
                }
                if (request.getParameter("Fcell") != null) {
                    clientData.put("phone", request.getParameter("Fcell"));
                }
                if (request.getParameter("Fbirt") != null) {
                    clientData.put("birthday", request.getParameter("Fbirt"));
                }
                if (request.getParameter("Finvoice") != null) {
                    clientData.put("invoiceoptin", request.getParameter("Finvoice"));
                }

                /* update sul db */
                CommonResponse updateRes = userRepo.updateUser(dbUser.getId(), clientData);
                if(!updateRes.result){
                    throw new Error("Error: impossibile effettuare le modifiche richieste");
                }
                /* se venisse cambiato username userei il nuovo per querare */
                if (request.getParameter("Fuser")!= null){
                    username = request.getParameter("Fuser");
                }
                /* aggiornamento utente appena aggionrato per garantire allineamento */
                CommonResponse justUpdatedUser = userRepo.getUserByUsername(username);
                if (!updateRes.result || !justUpdatedUser.result) {
                    throw new Error("Errore: impossibile salavre le modifiche richieste, ci spiace.");
                }
                dbUser = (UserModel)justUpdatedUser.payload;
                /* Se viene cambiato username deve cambiare nella sessione */
                if(!session.getAttribute("user").equals(dbUser.getUsername())){
                    session.setAttribute("user", dbUser.getUsername());
                }
            }

            /* arrivato qua ritorno lo user perche so di non essere in update */
            /* Metto nella reuqest */
            request.setAttribute("dbUser", dbUser);
            /* Mando indietro */
            request.getRequestDispatcher("PersonalArea.jsp").forward(request, response);
        } catch (Exception e) {
            // forward to error page
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("ErrorHandle.jsp").forward(request, response);
           System.out.println(e.getMessage());
        }

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
