/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.ProgettoBalneare.Servlet;

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
            // Utils.checkString(user, 4, 20); //valida i parametri ricevuti
            
            // Utente utente = UtenteFactory.getInstance().getUtenteByUsernamePassword(user, pass);
            
            //if(login(user, pass)){ 
            if("" != null){ // verifica se le credenziali sono corrette
                session.setAttribute("user", "gianni"/*utente.getUsername()*/); //imposta utente
                session.setAttribute("lastLogin", "01/01/2001"/*Utils.convertTime(session.getLastAccessedTime())*/); // imposta last login
                session.setMaxInactiveInterval(30); // tempo massimo di inattività (in secondi) prima che la sessione scada
                response.sendRedirect("user"); // redirect alla servlet user
            }
            else
                throw new Exception("User o pass non validi!");
            
        }catch(Exception e){
            session.invalidate(); // invlaida sessione
            request.setAttribute("errorMessage", e.getMessage()); //imposto parametri richiesta 
            request.setAttribute("link", "login.jsp");
            request.getRequestDispatcher("error.jsp").forward(request, response); //inoltra alla pagina di errore
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
