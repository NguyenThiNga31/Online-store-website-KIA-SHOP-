/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author MSI GTX
 */
@WebServlet(name = "SearchControl", urlPatterns = {"/SearchControl"})
public class SearchControl extends HttpServlet {

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
        //        response.setContentType("text/html;charset=UTF-8");
        String txtSearch = request.getParameter("txt");
        String cID = request.getParameter("cID");
        DAO dao = new DAO();
        List<Category> listC = dao.getAllCategory();
        request.setAttribute("listC", listC);
        List<Product> list;

        if (txtSearch != null && !txtSearch.trim().isEmpty()) {
            if (cID != null && !cID.isEmpty()) {
                list = dao.search(txtSearch, cID);
            } else {
                list = dao.searchByName(txtSearch);
            }
            request.setAttribute("listP", list);
            request.setAttribute("txtSearch", txtSearch);
            request.setAttribute("cID", cID);
            // Sau khi tìm kiếm xong và trước khi chuyển hướng tới trang JSP, tính số lượng sản phẩm đã tìm thấy và lưu vào biến resultCount
            int resultCount = list.size();
            request.setAttribute("resultCount", resultCount);
            if (resultCount == 0) {
                request.setAttribute("errorMessage", "No products found matching your search criteria.");
            }

            request.getRequestDispatcher("Shop.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Please enter a keyword.");
            request.getRequestDispatcher("Shop.jsp").forward(request, response);
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
