/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Category;
import entity.Product;
import entity.SizeDetail;
import entity.SubImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MSI GTX
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //----------------------------------------------Phúc----------------------------------------
    public String encryptToMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;

        }
    }

    // login 
    public Account login(String email, String password) {
        String query = "select * from Account\n"
                + "where email = ? and password = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            String enterdPassword = encryptToMD5(password);
            ps.setString(1, email);
            ps.setString(2, enterdPassword);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDate(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    //check email exits
    public Account checkExist(String email) {
        String query = "select * from Account\n"
                + "where email = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, email);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDate(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    // update password
    public Account Updatepass(String password, int pin, String email) {
        String query = "UPDATE Account\n"
                + "SET password = ?, pin = ?\n"
                + "WHERE email = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, password);
            ps.setInt(2, pin);
            ps.setString(3, email);
            ps.executeUpdate();

        } catch (Exception ex) {
        }
        return null;
    }

    // check pin 
    public Account checkpin(int pin) {
        String query = "select * from Account\n"
                + "WHERE pin=?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setInt(1, pin);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDate(9));
            }
        } catch (Exception ex) {
        }
        return null;
    }

    // update mã pin
    public Account UpdatePin(int pin, String email) {
        String query = "UPDATE Account\n"
                + "SET pin = ?\n"
                + "WHERE email = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, pin);
            ps.setString(2, email);
            ps.executeUpdate();

        } catch (Exception ex) {
        }
        return null;
    }

    // xóa mã pin
    public Account DeletePin(String emails) {
        String query = "UPDATE Account \n"
                + "SET pin = 0\n"
                + "WHERE email=?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, emails);
            ps.executeUpdate();
        } catch (Exception ex) {
        }
        return null;
    }

    // register Account
    public void signUp(String username, String email, String password, int pin, Date date) {
        String query = "insert into Account\n"
                + "values(?,?,?, null, null,0,?,CURRENT_TIMESTAMP)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, pin);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    // xóa tài khoản khỏi databases

    public int DeleteAccount(String email) throws Exception {
        String query = "delete from Account where email=?";
        int result = 0;
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return result;
    }

    public void updateViewed() {//edit param
        //edit query (my_table), number of param
        String query = "update [View]\n"
                + "set viewed = viewed + 1";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    //------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        DAO dao = new DAO();
    }
    //----------------huy---------

    public void updateProfile(Account a) {
        String query = "update Account\n"
                + "set fullname = ?, email = ?, phone = ?, address= ? where uID = ?";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, a.getFullname());
            ps.setString(2, a.getEmail());
            ps.setString(3, a.getPhone());
            ps.setString(4, a.getAddress());
            ps.setInt(5, a.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account getAccountById(int id) {
        String query = "SELECT * FROM Account WHERE uID = ?";
        try {
            ps = new DBContext().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDate(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePassword(int accId, String newPassword) {
        String query = "update Account\n"
                + "set password = ? where uID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, newPassword);
            ps.setInt(2, accId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //----------------Nga-----------------------------
    // hiện tất cả sản phẩm có isdelete bằng 0
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where isDeleted != 1";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    // Hiện tất cả sản phẩm theo danh mục
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    //Tim kiem san pham 
    public List<Product> search(String txtSearch, String cID) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE isDeleted != 1";

        if (cID != null && !cID.trim().equals("")) {
            query += " AND cID = ?";
        }

        if (txtSearch != null && !txtSearch.trim().equals("")) {
            query += " AND pName LIKE ?";
        }

        try {
            DAO dao = new DAO();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            int paramIndex = 1;

            if (cID != null && !cID.trim().equals("")) {
                ps.setInt(paramIndex++, Integer.parseInt(cID));
            }

            if (txtSearch != null && !txtSearch.trim().equals("")) {
                ps.setString(paramIndex++, "%" + txtSearch + "%");
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where pName like ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    // lấy hình ảnh bằng ID sản phẩm
    public List<SubImage> getAllSubImageByPID(String cid) {
        List<SubImage> list = new ArrayList<>();
        String query = "select S.*\n"
                + "from Product P, SubImage S\n"
                + "where P.pID = S.pID and S.pID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SubImage(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    //lấy size bằng ID sản phẩm
    public List<SizeDetail> getAllSizeByID(String id) {
        List<SizeDetail> list = new ArrayList<>();
        String query = "SELECT *"
                + "FROM SizeDetail SD\n"
                + "JOIN Size S ON SD.sizeID = S.sizeID\n"
                + "WHERE SD.pID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, id);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new SizeDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }
//Sắp xếp sản phẩm hàng A-Z

    public List<Product> getProductSortedByNameAZ() {
        List<Product> products = getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getName));
        return products;
    }

    //Sắp xếp sản phẩm hàng Z-A
    public List<Product> getProductSortedByNameZA() {
        List<Product> products = getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getName).reversed());
        return products;
    }

    // Phương thức để sắp xếp danh sách sản phẩm theo giá tăng dần
    public List<Product> getProductSortedByPriceAscending() {
        List<Product> products = getAllProduct();
        // Sử dụng phương thức sort của lớp Collections và truyền vào một Comparator
        Collections.sort(products, Comparator.comparing(Product::getPrice));
        return products;
    }

    // Phương thức để sắp xếp danh sách sản phẩm theo giá giảm dần
    public List<Product> getProductSortedByPriceDescending() {
        List<Product> products = getAllProduct();
        // Sử dụng phương thức sort của lớp Collections và truyền vào một Comparator
        Collections.sort(products, Comparator.comparing(Product::getPrice).reversed());
        return products;
    }

    // Phương thức để sắp xếp danh sách sản phẩm theo giá giảm dần
    public List<Product> getProductSortedByNewestProduct() {
        List<Product> products = getAllProduct();
        // Sử dụng phương thức sort của lớp Collections và truyền vào một Comparator
        Collections.sort(products, Comparator.comparing(Product::getId).reversed());
        return products;
    }
}
