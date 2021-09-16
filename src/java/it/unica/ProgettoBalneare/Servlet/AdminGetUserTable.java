/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Servlet;

import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.Slot;
import it.unica.ProgettoBalneare.Models.UserTableItem;
import it.unica.ProgettoBalneare.Repos.BookingRepo;
import it.unica.ProgettoBalneare.Repos.UserRepo;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
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
@WebServlet(name = "AdminGetUserTable", urlPatterns = {"/getTblUser"})
public class AdminGetUserTable extends HttpServlet {

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
       try {
            /* prendo la sessione e controllo l'utente loggato sia l'admin */
            HttpSession session = request.getSession(false);
            String username = session != null ? (String) session.getAttribute("user") : null;
            String userRole = session != null ? (String) session.getAttribute("userRole") : null;
            long userId = session != null ? (long)session.getAttribute("userId") : -1;
            if (username == null || userRole == null || !userRole.equals("admin") || userId == -1) {
                throw new Exception("Errore: si sta provando ad entrare nella sezione personale senza essere loggati o senza essere autorizzati");
            }    
        
            /* prendo leggo i parametri di ordinamento che mi sono stati passati
            *  forzo il loro stato per essere SOLO tra uno di quelli ammessi visto che jdbc non mi fa mettere le colonne
            *  e cerco di proteggermi da solo contro sql injection */
            String sortBy = request.getParameter("sortBy");
            String sortType = request.getParameter("sortType");
            if( !(sortBy.equals("surname") || sortBy.equals("tot_num_res") || sortBy.equals("")) ||
                    !(sortType.equals("asc") || sortType.equals("desc") || sortType.equals("")) ) {
                throw new Exception("si sta cercando di mettere valori non ammessi nella query per prendere gli utenti");
            }
            
            
            /* Effettuo l'inserimento sul db e controllo l'esito */
            CommonResponse res = UserRepo.getInstance().getUsersTable(sortBy, sortType); 
            if(!res.result){
                throw new Exception(res.message);
            }
            
            /* renedrizzo la tabella e la attacco alla DOM */
            ArrayList<UserTableItem> tblUsers = (ArrayList<UserTableItem>)res.payload;
            request.setAttribute("tblUser", tblUsers);
            request.getRequestDispatcher("TblUserSection.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("ErrorHandle.jsp").forward(request, response);
           
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
