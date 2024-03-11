/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * @author Nguyen Thi Nga - CS171351
 */
@WebServlet(name = "ManagerCustomerControl", urlPatterns = {"/ManagerCustomerControl"})
public class ManagerCustomerControl extends HttpServlet {

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
        String txtSearch = request.getParameter("searchInput");
        String searchOption = request.getParameter("searchOption");
        String sortOption = request.getParameter("sortOption");

        DAO dao = new DAO();

        List<Account> customers = null;
        int totalCustomers = dao.getTotalCustomerCount();
        Date date = new Date(); // Lấy ngày hiện tại
        int totalNewCustomers = dao.getNewCustomerCountToday(date);
        // Kiểm tra nếu người dùng thực hiện tìm kiếm
        if (txtSearch != null && !txtSearch.isEmpty()) {
            if ("byPhoneNumber".equals(searchOption)) {
                customers = dao.searchCustomersByPhoneNumber(txtSearch);
            } else {
                customers = dao.searchCustomersByName(txtSearch);
            }
        } else {
            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả khách hàng
            customers = dao.getAllCustomer();
        }

        // Kiểm tra và thực hiện sắp xếp nếu có yêu cầu
        if (sortOption != null && !sortOption.isEmpty()) {
            if (sortOption.equals("AZ")) {
                customers = dao.getCustomersSortedByNameAZ();
            } else if (sortOption.equals("ZA")) {
                customers = dao.getCustomersSortedByNameZA();
            } else if (sortOption.equals("createdAt")) {
                customers = dao.getNewCustomers();
            }
        }

        // Đặt các thuộc tính cho request và chuyển hướng đến trang JSP
        request.setAttribute("listA", customers);
        request.setAttribute("totalCustomers", totalCustomers);
        request.setAttribute("totalNewCustomers", totalNewCustomers);
        request.setAttribute("txtSearch", txtSearch);
        request.setAttribute("searchOption", searchOption);
        request.setAttribute("sortOption", sortOption);
        request.getRequestDispatcher("ManagerCustomer.jsp").forward(request, response);
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
