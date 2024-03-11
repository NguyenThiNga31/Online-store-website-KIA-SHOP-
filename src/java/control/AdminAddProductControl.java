/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AdminAddProductControl", urlPatterns = {"/addProduct"})
public class AdminAddProductControl extends HttpServlet {

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

        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String subImage1 = request.getParameter("subImage1");
        String subImage2 = request.getParameter("subImage2");
        String subImage3 = request.getParameter("subImage3");
        String subImage4 = request.getParameter("subImage4");
        String price = request.getParameter("price");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String category = request.getParameter("category");

        String sizevalue1 = request.getParameter("sizevalue1");
        String quantity1 = request.getParameter("quantity1");
        String sizevalue2 = request.getParameter("sizevalue2");
        String quantity2 = request.getParameter("quantity2");
        String sizevalue3 = request.getParameter("sizevalue3");
        String quantity3 = request.getParameter("quantity3");
        String sizevalue4 = request.getParameter("sizevalue4");
        String quantity4 = request.getParameter("quantity4");
        String sizevalue5 = request.getParameter("sizevalue5");
        String quantity5 = request.getParameter("quantity5");
        String sizevalue6 = request.getParameter("sizevalue6");
        String quantity6 = request.getParameter("quantity6");
        String sizevalue7 = request.getParameter("sizevalue7");
        String quantity7 = request.getParameter("quantity7");
        String[] sizeValues = {sizevalue1, sizevalue2, sizevalue3, sizevalue4, sizevalue5, sizevalue6, sizevalue7};
        String[] quantities = {quantity1, quantity2, quantity3, quantity4, quantity5, quantity6, quantity7};

        int totalQuantity = 0;
        for (String quantity : quantities) {
            if (quantity != null && !quantity.isEmpty()) {
                totalQuantity += Integer.parseInt(quantity);
            }
        }
        DAO dao = new DAO();
        if (dao.checkExistProduct(name, image, price, title, description, category, totalQuantity) != null) {
            request.getRequestDispatcher("ManagerControl").forward(request, response);
            return;
        } else {

            dao.addNewProduct(name, image, price, title, description, category, totalQuantity);
            int pID = dao.getProductIDToAdd();
            dao.addNewSubImage(pID + "", subImage1);
            dao.addNewSubImage(pID + "", subImage2);
            dao.addNewSubImage(pID + "", subImage3);
            dao.addNewSubImage(pID + "", subImage4);

            // Thêm các cặp sizevalue và quantity vào cơ sở dữ liệu nếu đã được nhập
            for (int i = 0; i < sizeValues.length; i++) {
                if (!sizeValues[i].isEmpty() && !quantities[i].isEmpty()) {
                    dao.addSizeAndQuantity(pID, Integer.parseInt(sizeValues[i]), Integer.parseInt(quantities[i]));
                }
            }
            request.getRequestDispatcher("ManagerControl").forward(request, response);
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
