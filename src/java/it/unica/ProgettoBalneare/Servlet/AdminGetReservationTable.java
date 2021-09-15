/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Servlet;

import it.unica.ProgettoBalneare.Models.CommonResponse;
import it.unica.ProgettoBalneare.Models.SlotViewModel;
import it.unica.ProgettoBalneare.Models.TableHandleReservation;
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
@WebServlet(name = "AdminGetReservationTable", urlPatterns = {"/getReservationTable"})
public class AdminGetReservationTable extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* prendo dati dall'utente e verifico che sia loggato e che sia admin */
            HttpSession session = request.getSession(false);
            String username = session != null ? (String) session.getAttribute("user") : null;
            String userRole = session != null ? (String) session.getAttribute("userRole") : null;
            long userId = session != null ? (long)session.getAttribute("userId") : -1;
            if (username == null || userRole == null || userId == -1) {
                throw new Exception("Errore: si sta provando ad entrare nella sezione personale senza essere loggati o senza essere autorizzati");
            }
            
            /* prendo e controllo l'input passatomi dal form */
//            String formUsername = request.getParameter("Fusername");
//            LocalDate formDate = LocalDate.parse(request.getParameter("Fdate"));
//            String formDatePeriod = request.getParameter("Fperiod");
//            int formNumSlot = Integer.parseInt(request.getParameter("Fnumslot"));
//            int formPrice = Integer.parseInt(request.getParameter("Fprice"));
//            String formDesctioption = request.getParameter("Fdescr");
            
            /* processo la richiesta lato db */
            CommonResponse res = InvoiceRepo.getInstance().getProcessInvoiceTable();
                    
            if(res.result) {
               ArrayList<TableHandleReservation> tbl = (ArrayList<TableHandleReservation>) res.payload;
               request.setAttribute("reservationTable", tbl);

                request.getRequestDispatcher("ReservationTblSection.jsp").forward(request, response);
            } else {
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
