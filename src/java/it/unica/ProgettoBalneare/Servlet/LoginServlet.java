/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Servlet;

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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession(); // crea una nuova sessione o recupera quella esistente
        String user = request.getParameter("Fuser"); // recupera i parametri passati dal client (login.jsp)
        String pass = request.getParameter("Fpass");
        
        try{
            /* prendo utente dal DB */
            CommonResponse userDBResult = UserRepo.getInstance().getUserByUsername(user);
            // controllo sia stato trovato altrimenti lo gestisco come errore
            if (!userDBResult.result){
                throw new Exception("Errore durante il recupero dell'utente: " + userDBResult.message);
            } 
            // cast da risposta a oggetto Utente
            UserModel dbUser = (UserModel)userDBResult.payload;
            
            /* Controllo la password corrisponda */
            if(dbUser != null && dbUser.getPassword().equals(pass)){ 
                session.setAttribute("user", dbUser.getUsername());
                session.setAttribute("userRole", dbUser.isIsAdmin()?"admin":"simple");
                session.setAttribute("userId", dbUser.getId());
                session.setAttribute("userInvoiceOptIn", dbUser.isInvoiceOptIn());
                session.setMaxInactiveInterval(1800); // timeout scadenza sessione 30 minuti
                response.getWriter().write("Login effettuato correttamente");
                //response.sendRedirect("index.jsp");
            }
            else
                throw new Exception("Errore: password errata");
            
        }catch(Exception e){
            session.invalidate();
            request.setAttribute("errorMessage", e.getMessage()); 
            request.setAttribute("link", "login.jsp");
            response.getWriter().write(e.getMessage());
            //request.getRequestDispatcher("error.jsp").forward(request, response); 
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
