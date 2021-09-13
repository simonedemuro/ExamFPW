/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Servlet;

import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.Slot;
import it.unica.ProgettoBalneare.Models.SlotViewModel;
import it.unica.ProgettoBalneare.Repos.BookingRepo;
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
@WebServlet(name = "AdminGetSlotCalendar", urlPatterns = {"/getSlotCalendar"})
public class AdminGetSlotCalendar extends HttpServlet {

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
            /* prendo dati dall'utente e valido */
            HttpSession session = request.getSession(false);
            String username = session != null ? (String) session.getAttribute("user") : null;
            if (username == null) {
                throw new Exception("Errore: si sta provando ad entrare nella sezione personale senza essere loggati, probabilmente la sessione è scaduta");
            }
            
            /* data di visualizzazione presa dalla sessione, se assente mese corrente */
            String strDate =(String) session.getAttribute("currentDate");
            LocalDate visualizedDate;
            if (strDate != null && !strDate.isEmpty()){
                visualizedDate = LocalDate.parse(strDate);
                session.setAttribute("currentDate", strDate);
            } else {
                visualizedDate = LocalDate.now().withDayOfMonth(1);
                session.setAttribute("currentDate", visualizedDate.toString());
            }
            
            /* Gestisco le frecccette di navigazione per spostarmi nel mese 
            *  se ricevo il parametro azione allora cambio mese in sessione */
            String action = request.getParameter("navigationAction");
            if(action != null && !action.isEmpty()) {
                if(action.equals("nextMonth")){
                    session.setAttribute("currentDate", 
                            visualizedDate.withMonth(visualizedDate.getMonthValue()+1).toString());
                    /* INTERROMPO qua e non vado avanti, tanto sarò redirezionato nuovamente qua per i dati */
                    return;
                } else if(action.equals("previousMonth")) {
                    session.setAttribute("currentDate", 
                            visualizedDate.withMonth(visualizedDate.getMonthValue()-1).toString());
                    /* INTERROMPO qua e non vado avanti, tanto sarò redirezionato nuovamente qua per i dati */
                    return;
                }
            }
            
            /* recupero i dati dal db e se non ci sono problemi restituisco la pagina renderizzata con jsp*/
            CommonResponse res = BookingRepo.getInstance().getSlotCalendar(visualizedDate);
            if(res.result) {
                ArrayList<SlotViewModel> a = (ArrayList<SlotViewModel>) res.payload;
                request.setAttribute("fullSlots", a);

                request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
            } else {
                /* in caso di errore rilancio l'eccezione per visualizzare il messaggio nella pagina di errore */
                throw new Exception(res.message);
            }
            
        } catch (Exception e) {
            /* forward to error page */
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
