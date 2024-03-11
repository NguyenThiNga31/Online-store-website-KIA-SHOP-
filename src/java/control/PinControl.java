/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import dao.EmailSender;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI GTX
 */
@WebServlet(name = "PinControl", urlPatterns = {"/pin"})
public class PinControl extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
    }

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
        DAO dao = new DAO();
        EmailSender em = new EmailSender();
        if (request.getParameter("btnpin") != null && request.getParameter("btnpin").equals("Pin")) {
            String pinParameter = request.getParameter("pin");
            if (pinParameter != null && pinParameter.matches("\\d+")) {
                int pin = Integer.parseInt(pinParameter);
                Account a = dao.checkpin(pin);
                if (a == null) {
                    response.getWriter().write("ERROR");
                    // Thực hiện các hành động khi có lỗi
                } else {
                    response.getWriter().write("SUCCESS");
                    // Thực hiện các hành động khi thành công
                }
            } else {
                response.getWriter().write("INVALID_PIN");
                // Thực hiện các hành động khi giá trị pin không hợp lệ
            }
        }

        if (request.getParameter("backpin") != null && request.getParameter("backpin").equals("Back")) {
            HttpSession session = request.getSession();
            String emails = (String) session.getAttribute("checkEmail");
            if (emails != null) {
                try {
                    dao.DeleteAccount(emails);
                    //response.sendRedirect("Login.jsp");
                    response.getWriter().write("ERROR");
                } catch (Exception ex) {
                    Logger.getLogger(PinControl.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                response.getWriter().write("INVALID_PIN");
            }

        }
        if (request.getParameter("resendpin") != null && request.getParameter("resendpin").equals("Resendpin")) {
            HttpSession session = request.getSession();
            String emails = (String) session.getAttribute("checkEmail");
            if (emails != null) {
                try {
                    int pins = em.generateRandomPin();
                    dao.UpdatePin(pins, emails);
                    response.getWriter().write("SUCCESS");
                } catch (Exception ex) {
                    Logger.getLogger(PinControl.class.getName()).log(Level.SEVERE, null, ex);
                    response.getWriter().write("ERROR");
                }
            } else {
                response.getWriter().write("INVALID_PIN");
            }
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
