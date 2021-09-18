/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Servlet;

import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.InvoiceTableItem;
import it.unica.ProgettoBalneare.Models.SlotViewModel;
import it.unica.ProgettoBalneare.Repos.BookingRepo;
import it.unica.ProgettoBalneare.Repos.InvoiceRepo;
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
@WebServlet(name = "InvoiceServlet", urlPatterns = {"/getInvoices"})
public class InvoiceServlet extends HttpServlet {

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
             /* prendo la sessione e controllo sia un utente semplice*/
            HttpSession session = request.getSession(true);
            String username = session != null ? (String) session.getAttribute("user") : null;
            String userRole = session != null ? (String) session.getAttribute("userRole") : null;
            Long userId = session != null ? (Long)session.getAttribute("userId") : null;
            if (username == null || !userRole.equals("simple") || userId == null) {
                throw new Exception("Errore: si sta provando ad entrare nella sezione personale senza essere loggati o senza essere autorizzati");
            }
            
            /* recupero i dati dal db e se non ci sono problemi restituisco la pagina renderizzata con jsp*/
            CommonResponse res = InvoiceRepo.getInstance().getInvoicesByUser(userId);
            if(res.result) {
                ArrayList<InvoiceTableItem> tblInvoices = (ArrayList<InvoiceTableItem> ) res.payload;
                request.setAttribute("tblInvoices", tblInvoices);

                request.getRequestDispatcher("invoicesRows.jsp").forward(request, response);
            } else {
                /* in caso di errore rilancio l'eccezione per visualizzare il messaggio nella pagina di errore */
                throw new Exception(res.message);
            }
            
        } catch (Exception e) {
            /* forward to error page */
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("invoicesRows.jsp").forward(request, response);
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
