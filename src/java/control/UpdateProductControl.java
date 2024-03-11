/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Category;
import entity.Product;
import entity.SubImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateProductControl", urlPatterns = {"/UpdateProductControl"})
public class UpdateProductControl extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        DAO dao = new DAO();
        Product product = dao.getProductByID(id);
        List<Category> listC = dao.getAllCategory();

        String select = "";

        for (Category category : listC) {
            if (product.getCateID() != category.getId()) {
                select += "<option value=\"" + category.getId() + "\">" + category.getName() + "</option>\n";
            } else {
                select += "<option value=\"" + category.getId() + "\"selected>" + category.getName() + "</option>\n";
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<div class=\"modal-dialog\">\n"
                + "                    <div class=\"modal-content\">\n"
                + "                        <form action=\"UpdateProductControl\" method=\"post\" id=\"UpdateProductForm\">\n"
                + "                            <div class=\"modal-header\">\n"
                + "                                <h4 class=\"modal-title\">Edit Product</h4>\n"
                + "                                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n"
                + "                            </div>\n"
                + "                            <div class=\"modal-body\">\n"
                + "                                \n"
                + "                                    <input name=\"pID\" type=\"hidden\" class=\"form-control\" value=\"" + product.getId() + "\">\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Name</label>\n"
                + "                                    <input name=\"name\" type=\"text\" class=\"form-control\" required value=\"" + product.getName() + "\">\n"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Image</label>\n"
                + "                                    <input name=\"image\" type=\"text\" class=\"form-control\" required value=\"" + product.getImage() + "\">\n"
                + "<img src=\"" + product.getImage() + "\"/>"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>subImage1</label>\n"
                + "                                    <input name=\"subImage1\" type=\"text\" class=\"form-control\" required value=\"" + product.getSubImage().get(0).getImage() + "\">\n"
                + "<img src=\"" + product.getSubImage().get(0).getImage() + "\"/>"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>subImage2</label>\n"
                + "                                    <input name=\"subImage2\" type=\"text\" class=\"form-control\" required value=\"" + product.getSubImage().get(1).getImage() + "\">\n"
                + "<img src=\"" + product.getSubImage().get(1).getImage() + "\"/>"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>subImage3</label>\n"
                + "                                    <input name=\"subImage3\" type=\"text\" class=\"form-control\" required value=\"" + product.getSubImage().get(2).getImage() + "\">\n"
                + "<img src=\"" + product.getSubImage().get(2).getImage() + "\"/>"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>subImage4</label>\n"
                + "                                    <input name=\"subImage4\" type=\"text\" class=\"form-control\" required value=\"" + product.getSubImage().get(3).getImage() + "\">\n"
                + "<img src=\"" + product.getSubImage().get(3).getImage() + "\"/>"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Price</label>\n"
                + "                                    <input name=\"price\" type=\"text\" class=\"form-control\" required value=\"" + product.getPrice() + "\">\n"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Title</label>\n"
                + "                                    <textarea name=\"title\" class=\"form-control\" >" + product.getTitle() + "</textarea>\n"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Description</label>\n"
                + "                                    <textarea name=\"description\" class=\"form-control\">" + product.getDescription() + "</textarea>\n"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Amount</label>\n"
                + "                                    <input name=\"amount\" type=\"text\" class=\"form-control\" required readonly value=\"" + product.getAmount() + "\">\n"
                + "                                </div>\n"
                + "                                 <div class=\"form-group\">\n"
                + "                                    <label>Size Value </label>\n"
                + "                                <input name=\"sizevalue1\" type=\"text\" class=\"form-control\" value=\"37\">\n"
                + "                                </div>\n"
                + "                               <div class=\"form-group\">\n"
                + "                                <label> Quantity </label>\n"
                + "                               <input name=\"quantity1\" type=\"text\" class=\"form-control\" required value=\"" + product.getSizedetail().get(0).getQuantity() + "\"min=\"1\" max=\"5000\">\n"
                + "                                </div>\n"
                + "                                 <div class=\"form-group\">\n"
                + "                                    <label>Size Value </label>\n"
                + "                                <input name=\"sizevalue2\" type=\"text\" class=\"form-control\" value=\"38\">\n"
                + "                                </div>\n"
                + "                               <div class=\"form-group\">\n"
                + "                                <label> Quantity </label>\n"
                + "                               <input name=\"quantity2\" type=\"text\" class=\"form-control\" required value=\"" + product.getSizedetail().get(1).getQuantity() + "\"min=\"1\" max=\"5000\">\n"
                + "                                </div>\n"
                + "                                 <div class=\"form-group\">\n"
                + "                                    <label>Size Value </label>\n"
                + "                                <input name=\"sizevalue3\" type=\"text\" class=\"form-control\" value=\"39\">\n"
                + "                                </div>\n"
                + "                               <div class=\"form-group\">\n"
                + "                                <label> Quantity </label>\n"
                + "                               <input name=\"quantity3\" type=\"text\" class=\"form-control\" required value=\"" + product.getSizedetail().get(2).getQuantity() + "\"min=\"1\" max=\"5000\">\n"
                + "                                </div>\n"
                + "                                 <div class=\"form-group\">\n"
                + "                                    <label>Size Value </label>\n"
                + "                                <input name=\"sizevalue4\" type=\"text\" class=\"form-control\" value=\"40\">\n"
                + "                                </div>\n"
                + "                               <div class=\"form-group\">\n"
                + "                                <label> Quantity </label>\n"
                + "                               <input name=\"quantity4\" type=\"text\" class=\"form-control\" required value=\"" + product.getSizedetail().get(3).getQuantity() + "\"min=\"1\" max=\"5000\">\n"
                + "                                </div>\n"
                + "                                 <div class=\"form-group\">\n"
                + "                                    <label>Size Value </label>\n"
                + "                                <input name=\"sizevalue5\" type=\"text\" class=\"form-control\" value=\"41\">\n"
                + "                                </div>\n"
                + "                               <div class=\"form-group\">\n"
                + "                                <label> Quantity </label>\n"
                + "                               <input name=\"quantity5\" type=\"text\" class=\"form-control\" required value=\"" + product.getSizedetail().get(4).getQuantity() + "\"min=\"1\" max=\"5000\">\n"
                + "                                </div>\n"
                + "                                 <div class=\"form-group\">\n"
                + "                                    <label>Size Value </label>\n"
                + "                                <input name=\"sizevalue6\" type=\"text\" class=\"form-control\" value=\"42\">\n"
                + "                                </div>\n"
                + "                               <div class=\"form-group\">\n"
                + "                                <label> Quantity </label>\n"
                + "                               <input name=\"quantity6\" type=\"text\" class=\"form-control\" required value=\"" + product.getSizedetail().get(5).getQuantity() + "\"min=\"1\" max=\"5000\">\n"
                + "                                </div>\n"
                + "                                 <div class=\"form-group\">\n"
                + "                                    <label>Size Value </label>\n"
                + "                                <input name=\"sizevalue7\" type=\"text\" class=\"form-control\" value=\"43\">\n"
                + "                                </div>\n"
                + "                               <div class=\"form-group\">\n"
                + "                                <label> Quantity </label>\n"
                + "                               <input name=\"quantity7\" type=\"text\" class=\"form-control\" required value=\"" + product.getSizedetail().get(6).getQuantity() + "\"min=\"1\" max=\"5000\">\n"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Category</label>\n"
                + "                                    <select name=\"category\" class=\"form-select\" aria-label=\"Default select example\">\n"
                + select
                + "                                    </select>"
                + "                                </div>\n"
                + "                                \n"
                + "                            </div>\n"
                + "                            <div class=\"modal-footer\">\n"
                + "                                <input type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\" value=\"Cancel\">\n"
                + "                                <input type=\"submit\" class=\"btn btn-info\" value=\"Submit\">\n"
                + "                            </div>\n"
                + "                        </form>\n"
                + "                    </div>\n"
                + "                </div>\n"
                + "            <script src=\"js/updateproduct.js\" type=\"text/javascript\"></script>\n"
        );
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
        request.setCharacterEncoding("UTF-8");
        //processRequest(request, response);
        String name = request.getParameter("name");
        String pID = request.getParameter("pID");
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

        DAO dao = new DAO();
        Product oldProduct = dao.getProductByID(pID);
        List<SubImage> listSubImg = dao.getAllSubImageByPID(pID);
        SubImage s = listSubImg.get(0);
        SubImage s1 = listSubImg.get(1);
        SubImage s2 = listSubImg.get(2);
        SubImage s3 = listSubImg.get(3);
        s.setImage(subImage1);
        s1.setImage(subImage2);
        s2.setImage(subImage3);
        s3.setImage(subImage4);

        dao.updateSubImage(s.getpID() + "", s.getImage() + "", s.getSubImageID() + "");
        dao.updateSubImage(s1.getpID() + "", s1.getImage() + "", s1.getSubImageID() + "");
        dao.updateSubImage(s2.getpID() + "", s2.getImage() + "", s2.getSubImageID() + "");
        dao.updateSubImage(s3.getpID() + "", s3.getImage() + "", s3.getSubImageID() + "");

        int totalQuantity = 0;
        for (String quantity : quantities) {
            if (quantity != null && !quantity.isEmpty()) {
                totalQuantity += Integer.parseInt(quantity);
            }
        }
        dao.updateProduct(name, image, price, title, description, category, totalQuantity, Integer.parseInt(pID));
        for (int i = 0; i < sizeValues.length; i++) {
            if (!sizeValues[i].isEmpty() && !quantities[i].isEmpty()) {
                dao.updateSizeAndQuantity(Integer.parseInt(pID), Integer.parseInt(sizeValues[i]), Integer.parseInt(quantities[i]));
            }
        }
        request.getRequestDispatcher("ManagerControl").forward(request, response);
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
