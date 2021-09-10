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
import java.time.LocalDate;
import static java.util.Collections.list;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fpw
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet {

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
        System.out.println(request.getParameter("Fuser"));
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

        try {
            /* Processo la chiamata */
            // creo entità per il database
            UserModel newUser
                    = new UserModel(request.getParameter("Fuser"),
                            request.getParameter("Fpass1"),
                            request.getParameter("Fname"),
                            request.getParameter("Fsurn"),
                            LocalDate.parse(request.getParameter("Fbirt")),
                            request.getParameter("Fcode"),
                            request.getParameter("Fsex").charAt(0), // to char
                            request.getParameter("Fmail"),
                            request.getParameter("Fcell"),
                            Boolean.parseBoolean(request.getParameter("Finvoice")) // su db è boolean
                    );
            // Aggiungo l'utente sul database tramite la Factory
            UserRepo userRepo = UserRepo.getInstance();
            CommonResponse res = userRepo.addUser(newUser);
            
            // Controllo l'esito
            if(res.result) {
                /* Rispondo che è andato a buon fine */
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Utente inserito correttamente");
            }
            else {
                /* Passo al front end il messaggio di errore */
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Errore durante l'inserimento dell'utente " + res.message);
            }
            

        } catch (Exception e) {
            /* Rispondo che è andato storto*/
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Errore durante l'inserimento dell'utente " + e.getMessage());
        }

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
