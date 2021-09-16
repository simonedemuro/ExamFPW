/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Servlet;

import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.UserModel;
import it.unica.ProgettoBalneare.Repos.BookingRepo;
import it.unica.ProgettoBalneare.Repos.UserRepo;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
@WebServlet(name = "BookSlot", urlPatterns = {"/bookSlot"})
public class BookSlot extends HttpServlet {

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
            /* prendo dati dall'utente e verifico che sia loggato e che sia admin */
            HttpSession session = request.getSession(false);
            String username = session != null ? (String) session.getAttribute("user") : null;
            String userRole = session != null ? (String) session.getAttribute("userRole") : null;
            long userId = session != null ? (long)session.getAttribute("userId") : -1;
            if (username == null || userRole == null || userId == -1) {
                throw new Exception("Errore: si sta provando ad entrare nella sezione personale senza essere loggati o senza essere autorizzati");
            }
            
            /* prendo e controllo l'input passatomi dal form */
            LocalDate fromDate = LocalDate.parse(request.getParameter("Ffrom"));
            String fromAmPm = request.getParameter("FfromSlot").equals("Mattina")?"AM":"PM";
            LocalDate toDate = LocalDate.parse(request.getParameter("Fto"));
            String toAmPm = request.getParameter("FtoSlot").equals("Mattina")?"AM":"PM";
            int numReservedSlots = Integer.parseInt(request.getParameter("Fnumplaces"));
            
            if (numReservedSlots < 0 ){
                throw new Exception("Non si può prenotare un numero negativo di slot");
            }
            
            CommonResponse res = BookingRepo.getInstance().bookSlots(fromDate, fromAmPm, toDate, toAmPm, numReservedSlots, userId);
            
            response.getWriter().write(res.message);
        } catch (Exception e) {
            response.getWriter().write("Errore: " + e.getMessage());
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
