/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Cart;
import entity.CartItem;
import entity.Product;
import entity.Size;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CartControl", urlPatterns = {"/cart"})
public class CartControl extends HttpServlet {

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
        HttpSession session = request.getSession();
        DAO dao = new DAO();
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (session.getAttribute("acc") != null) {
            int id = Integer.parseInt(request.getParameter("id"));
            String action = request.getParameter("action");
            int sizeID = Integer.parseInt(request.getParameter("sizeId"));
            Account customerAccount = (Account) session.getAttribute("acc");
            int accountID = customerAccount.getId();

            if (!(id == 0 && action == null)) {
                if (action != null) {
                    if (action.equalsIgnoreCase("add")) {
                        if (dao.checkcartExist(id, accountID, sizeID)) {
                            dao.updateaddCart(id, accountID, sizeID);
                        } else {
                            if (dao.checkcartquantity(id, accountID, sizeID)) {
                                PrintWriter out = response.getWriter();
                                out.println("<script>alert('Số lượng quá nhiều');</script>");
                            } else {
                                dao.addCart(id, accountID, sizeID);
                            }
                        }
                    } else if (action.equalsIgnoreCase("minus")) {

                        dao.updateminusCart(id, accountID, sizeID);
                    } else if (action.equalsIgnoreCase("delete")) {
                        // Additional logic if needed for delete action
                        dao.deleteCart(id, accountID, sizeID);
                    }
                }
            }

            List<CartItem> listx = dao.getAllCart(accountID);
            request.setAttribute("listx", listx);
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        } else {
            // Người dùng chưa đăng nhập, có thể thực hiện các hành động khác hoặc chuyển hướng đến trang đăng nhập
            response.sendRedirect("Login.jsp"); // Điều hướng đến trang đăng nhập

        }
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
