/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Servlet;

import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.Slot;
import it.unica.ProgettoBalneare.Repos.BookingRepo;
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
@WebServlet(name = "AdminAddSlot", urlPatterns = {"/addSlot"})
public class AdminAddSlot extends HttpServlet {

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
        
            /* prendo dati del form utente e li metto nel modello da passare al repo*/
            Slot reservation = new Slot(
                LocalDate.parse((String)request.getParameter("Fdt")),
                request.getParameter("Fslot").equals("Mattina") ? "AM" : "PM",
                Integer.parseInt(request.getParameter("Naccomodation"))
            );
            
            /* Attenzione a chi cerca di generare posti malevolmente */
            if (reservation.getNumPlaces() < 0) {
                throw new Exception("Non è possibile inserire un numero negativo di posti");
            }
            
            /* Effettuo l'inserimento sul db e controllo l'esito */
            CommonResponse res = (BookingRepo.getInstance()).addSlot(reservation); 
            if(!res.result){
                throw new Exception(res.message);
            }
            
            /* Invio una risposta testuale in caso di successo per mostrare un popup a front end */
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Slot inserito correttamente");
        } catch (Exception e) {
            //request.setAttribute("errorMessage", e.getMessage());
            //request.getRequestDispatcher("ErrorHandle.jsp").forward(request, response);
            response.getWriter().write("Error: " + e.getMessage());
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
