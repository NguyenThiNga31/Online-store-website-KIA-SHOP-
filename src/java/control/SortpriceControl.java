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
 * @author PhucPHCE171653
 */
@WebServlet(name = "SortpriceControl", urlPatterns = {"/SortpriceControl"})
public class SortpriceControl extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        try {
            List<Category> listC = dao.getAllCategory();
            request.setAttribute("listC", listC);
            String selectedPrice = request.getParameter("price");
            String currentCID = request.getParameter("cID");
            String showAllProductsParam = request.getParameter("showAllProducts");
            String[] sortprices = selectedPrice.split("-");
            double minprice = Double.parseDouble(sortprices[0]);
            double maxprice = Double.parseDouble(sortprices[1]);
            boolean showAllProducts = Boolean.parseBoolean(showAllProductsParam);
            if (showAllProducts) {
                String previousCID = (String) request.getSession().getAttribute("previousCID");
                currentCID = (previousCID != null) ? previousCID : currentCID;
            }
            // Lưu giữ giá trị cID vào session
            request.setAttribute("price", selectedPrice);
            request.setAttribute("showAllProducts", showAllProducts);
            request.setAttribute("cID", currentCID);
            int pageIndex = (request.getParameter("pageIndex") != null) ? Integer.parseInt(request.getParameter("pageIndex")) : 1;

            if (showAllProducts) {

                List<Product> allProducts = dao.getAllProduct();
                int numberProductPerPage = (request.getParameter("numberProductPerPage") != null) ? Integer.parseInt(request.getParameter("numberProductPerPage")) : 10;
                int pageSize = dao.getPageSize(numberProductPerPage, allProducts.size());
                List<Product> paginatedProducts = dao.paginateList(allProducts, pageIndex, numberProductPerPage);

                request.setAttribute("numberProductPerPage", numberProductPerPage);
                request.setAttribute("listP", paginatedProducts);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalPage", pageSize);

            } else {
                if (currentCID.equals("")) {
                    List<Product> ls = dao.getProductByPrice(minprice, maxprice);
                    int numberProduct = (request.getParameter("numberProduct") != null) ? Integer.parseInt(request.getParameter("numberProductPerPage")) : 6;

                    request.setAttribute("productList", ls);
                    int totalProducts = ls.size();
                    int totalPage = (int) Math.ceil((double) totalProducts / numberProduct);
                    int start = (pageIndex - 1) * numberProduct;
                    int end = Math.min(start + numberProduct, totalProducts);
                    List<Product> paginatedProducts = ls.subList(start, end);

                    request.setAttribute("listP", paginatedProducts);
                    request.setAttribute("pageIndex", pageIndex);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("numberProductPerPage", numberProduct);
                    request.setAttribute("price", selectedPrice);
                    request.removeAttribute(currentCID);
                } else {
                    List<Product> ls = dao.getProductByPricecatogy(minprice, maxprice, currentCID);
                    int numberProduct = (request.getParameter("numberProduct") != null) ? Integer.parseInt(request.getParameter("numberProductPerPage")) : 6;

                    request.setAttribute("productList", ls);
                    int totalProducts = ls.size();
                    int totalPage = (int) Math.ceil((double) totalProducts / numberProduct);
                    int start = (pageIndex - 1) * numberProduct;
                    int end = Math.min(start + numberProduct, totalProducts);
                    List<Product> paginatedProducts = ls.subList(start, end);

                    request.setAttribute("listP", paginatedProducts);
                    request.setAttribute("pageIndex", pageIndex);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("numberProductPerPage", numberProduct);
                    request.setAttribute("price", selectedPrice);
                    request.setAttribute("tag", currentCID);
                }

            }
            request.getRequestDispatcher("Shop.jsp").forward(request, response);
            request.removeAttribute(currentCID);
        } catch (Exception e) {
            e.printStackTrace();
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
