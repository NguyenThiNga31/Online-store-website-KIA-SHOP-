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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    //Kiem tra san pham co ton tai hay chua
    public Product checkExistProduct(String name, String image, String price,
            String title, String description, String cid, int amount) {
        String query = "select * from Product\n"
                + "where pName = ? and [image] = ? and price = ? and cID = ? and title = ? and [description] = ? and pAmount = ? and isDeleted != 1";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, cid);
            ps.setString(5, title);
            ps.setString(6, description);
            ps.setInt(7, amount);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    //Them san pham moi
    public void addNewProduct(String name, String image, String price,
            String title, String description, String cid, int amount) {
        String query = "INSERT into Product (pName, [image], price, title, [description], cID, pAmount, isDeleted)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, cid);
            ps.setInt(7, amount);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    //Them anh phu moi

    public void addNewSubImage(String pID, String Simage) {
        String query = "INSERT into SubImage (pID, SImage)\n"
                + "VALUES (?, ?)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, pID);
            ps.setString(2, Simage);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //
    public int getProductIDToAdd() {
        String query = "SELECT TOP(1) pID from Product order BY pID DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    //nhập size và quantity dựa vào pID

    public void addSizeAndQuantity(int productId, int sizeValue, int quantity) {
        try {
            // Tìm sizeID dựa trên sizevalue
            int sizeId = getSizeIdBySizeValue(sizeValue);

            if (sizeId != -1) {
                // Nếu tìm thấy sizeID, thêm dữ liệu vào bảng SizeDetail
                String sizeDetailQuery = "INSERT INTO SizeDetail (pID, sizeID, quantity) VALUES (?, ?, ?)";
                PreparedStatement sizeDetailPs = conn.prepareStatement(sizeDetailQuery);
                sizeDetailPs.setInt(1, productId);
                sizeDetailPs.setInt(2, sizeId);
                sizeDetailPs.setInt(3, quantity);
                sizeDetailPs.executeUpdate();

                // Đóng kết nối và tài nguyên
                sizeDetailPs.close();
            } else {
                System.out.println("Không thể tìm thấy sizeID cho sizevalue đã cho.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // lay size value từ bảng size
    private int getSizeIdBySizeValue(int sizeValue) {
        String query = "SELECT sizeID FROM Size WHERE sizevalue = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sizeValue);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("sizeID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy sizeID cho sizevalue đã cho
    }

    // Lay toan bo khach hang
    public List<Account> getAllCustomer() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account where role = 0";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("uID"));
                account.setFullname(rs.getString("fullname"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setAddress(rs.getString("address"));
                list.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
        }

        return list;
    }

    //Tong so khach hang
    public int getTotalCustomerCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // Tính số lượng khách hàng mới trong ngày hôm nay
    public int getNewCustomerCountToday(Date date) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0 AND CAST(date AS DATE) = ?";
        try (Connection conn = new DBContext().getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
            ps.setDate(1, sqlDate);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    //Tim khach hang bang so dien thoai
    public List<Account> searchCustomersByPhoneNumber(String txtSearch) {
        List<Account> customers = new ArrayList<>();
        String query = "SELECT * FROM Account WHERE phone LIKE ? and role = 0";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account customer = new Account();
                customer.setId(rs.getInt("uID"));
                customer.setFullname(rs.getString("fullname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    // tìm kiếm khách hàng bằng tên
    public List<Account> searchCustomersByName(String txtSearch) {
        List<Account> list = new ArrayList<>();
        String query = "SELECT * FROM Account WHERE LOWER(fullname) LIKE LOWER(?) AND role = 0";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account customer = new Account();
                customer.setId(rs.getInt("uID"));
                customer.setFullname(rs.getString("fullname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                list.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //Sắp xếp khách hàng mới
    public List<Account> getNewCustomers() {
        List<Account> customers = getAllCustomer();
        Collections.sort(customers, Comparator.comparing(Account::getId).reversed());
        return customers;
    }

    //Sắp xếp khách hàng A-Z
    public List<Account> getCustomersSortedByNameAZ() {
        List<Account> customers = getAllCustomer();
        Collections.sort(customers, Comparator.comparing(Account::getFullname));
        return customers;
    }

    //Sắp xếp khách hàng Z-A
    public List<Account> getCustomersSortedByNameZA() {
        List<Account> customers = getAllCustomer();
        Collections.sort(customers, Comparator.comparing(Account::getFullname).reversed());
        return customers;
    }
}
