/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Order;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ManageOrderControl", urlPatterns = {"/ManageOrderControl"})
public class ManageOrderControl extends HttpServlet {

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

        DAO dao = new DAO();
        List<Order> list = dao.getAllOrders();
        Order order;
        String sortOption = request.getParameter("sortOption");
        String txtSearch = request.getParameter("searchInput");
        String searchOption = request.getParameter("searchOption");
        String acceptID = request.getParameter("acceptID");

        double totalEarnings = dao.getTotalEarnings();
        int totalOrders = dao.getTotalOrderCount();
        int waitingOrder = dao.getCountWaitingOrders();
        int acceptedOrder = dao.getCountAcceptedOrders();

        // Kiểm tra nếu người dùng thực hiện tìm kiếm
        if (txtSearch != null && !txtSearch.isEmpty()) {
            if ("byPhoneNumber".equals(searchOption)) {
                list = dao.searchOrderByPhoneNumber(txtSearch);
            } else {
                list = dao.searchOrderByName(txtSearch);
            }
        } else {
            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả khách hàng
            list = dao.getAllOrders();
        }

        // Kiểm tra và thực hiện sắp xếp nếu có yêu cầu
        if (sortOption != null && !sortOption.isEmpty()) {
            if (sortOption.equals("Waiting")) {
                list = dao.getAllWaitingOrders();
            } else if (sortOption.equals("Accepted")) {
                list = dao.getAllAcceptedOrders();
            }
        }

//        if (acceptOption.equals("Accept")) {
//            dao.updateStatusOrder(acceptID);
//        }
        request.setAttribute("acceptedOrder", acceptedOrder);
        request.setAttribute("waitingOrder", waitingOrder);
        request.setAttribute("totalE", totalEarnings);
        request.setAttribute("totalOrders", totalOrders);

        request.setAttribute("listO", list);
        request.getRequestDispatcher("ManageOrder.jsp").forward(request, response);
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
     * * Handles the HTTP <code>POST</code> method.
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
