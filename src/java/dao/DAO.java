/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.CartItem;
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

    public List<Product> paginateList(List<Product> productList, int pageIndex, int numberProductPerPage) {
        int start = (pageIndex - 1) * numberProductPerPage;
        int end = Math.min(start + numberProductPerPage, productList.size());
        return productList.subList(start, end);

    }

    public int getPageSize(int numberProduct, int allProduct) {
        int pageSize = allProduct / numberProduct;
        if (allProduct % numberProduct != 0) {
            pageSize = (allProduct / numberProduct) + 1;
        }
        return pageSize;
    }

    public List<Product> getProductByPrice(double minprice, double maxprice) {
        List<Product> list = new ArrayList<>();
        String query = "select *\n"
                + "from Product\n"
                + "where price >= ? AND price <= ? AND isDeleted != 1";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setDouble(1, minprice);
            ps.setDouble(2, maxprice);
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

    public List<Product> getProductByPricecatogy(double minprice, double maxprice, String cID) {
        List<Product> list = new ArrayList<>();
        String query = "select *\n"
                + "from Product\n"
                + "where price >= ? AND price <= ? AND cID=? AND isDeleted != 1";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setDouble(1, minprice);
            ps.setDouble(2, maxprice);
            ps.setString(3, cID);
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

    //code giỏ hàng đang làm
    public void addCart(int productID, int accountID, int sizeID) {
        String query = "INSERT INTO [Cart](productID, accountID, sizeID, quantity) VALUES (?, ?, ?, 1)";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            // Set parameters for both UPDATE and INSERT
            ps.setInt(1, productID);
            ps.setInt(2, accountID);
            ps.setInt(3, sizeID);
            ps.executeUpdate();
        } catch (Exception e) {
            // Handle exceptions appropriately
        }
    }

    public List<CartItem> getAllCart(int accountID) {
        List<CartItem> list = new ArrayList<>();
        String query = "SELECT c.[productID], c.[accountID], c.[sizeID], c.[quantity], \n"
                + "            p.[image], p.[price], p.[pName], s.[sizeValue] \n"
                + "            FROM [Project_KiAShop].[dbo].[Cart] c \n"
                + "            JOIN [Project_KiAShop].[dbo].[Product] p ON c.[productID] = p.[pID] \n"
                + "            JOIN [Project_KiAShop].[dbo].[Size] s ON c.[sizeID] = s.[sizeID] \n"
                + "            WHERE c.[accountID] = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            rs = ps.executeQuery();
            DAO dao = new DAO();
            while (rs.next()) {
                CartItem cartItem = new CartItem(
                        rs.getInt("productID"),
                        rs.getInt("accountID"),
                        rs.getInt("sizeID"),
                        rs.getInt("quantity"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("pName"),
                        rs.getInt("sizeValue"));  // Assuming you don't have the size information here
                list.add(cartItem); // Add the created CartItem to the lis
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
        }
        return list;
    }

    public Boolean checkcartExist(int productID, int accountID, int sizeID) {
        String query = "SELECT *  FROM [Cart] WHERE productID = ? AND accountID = ? AND sizeID = ? ";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setInt(1, productID);
            ps.setInt(2, accountID);
            ps.setInt(3, sizeID);
            rs = ps.executeQuery();

            // Check if the result set has any rows
            return rs.next();
        } catch (Exception e) {
        }
        return false;
    }

    public void updateaddCart(int productID, int accountID, int sizeID) {
        String query = "Update [Cart] set quantity = quantity +1 WHERE productID = ? AND accountID = ? AND sizeID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, accountID);
            ps.setInt(3, sizeID);
            ps.executeUpdate();

        } catch (Exception ex) {
        }
    }

    public void deleteCart(int productID, int accountID, int sizeID) {
        String query = "DELETE FROM Cart WHERE productID = ? AND accountID = ? AND sizeID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, accountID);
            ps.setInt(3, sizeID);
            ps.executeUpdate();

        } catch (Exception ex) {
        }
    }

    public void updateminusCart(int productID, int accountID, int sizeID) {
        String updateQuery = "UPDATE Cart SET quantity = quantity - 1 WHERE productID = ? AND accountID = ? AND sizeID = ?";
        String deleteQuery = "DELETE FROM Cart WHERE productID = ? AND accountID = ? AND sizeID = ?";

        try {
            conn = new DBContext().getConnection(); // Open connection to SQL
            conn.setAutoCommit(false); // Disable auto-commit for transaction

            // Update quantity
            ps = conn.prepareStatement(updateQuery);
            ps.setInt(1, productID);
            ps.setInt(2, accountID);
            ps.setInt(3, sizeID);
            int updatedRows = ps.executeUpdate();

            // Check if quantity became zero, then delete the record
            if (updatedRows > 0) {
                ps = conn.prepareStatement("SELECT quantity FROM Cart WHERE productID = ? AND accountID = ? AND sizeID = ?");
                ps.setInt(1, productID);
                ps.setInt(2, accountID);
                ps.setInt(3, sizeID);
                ResultSet rs = ps.executeQuery();

                if (rs.next() && rs.getInt("quantity") == 0) {
                    // If quantity is zero, delete the record
                    ps = conn.prepareStatement(deleteQuery);
                    ps.setInt(1, productID);
                    ps.setInt(2, accountID);
                    ps.setInt(3, sizeID);
                    ps.executeUpdate();
                }
            }

            conn.commit(); // Commit the transaction
        } catch (Exception ex) {
        }
    }

    public Boolean checkcartquantity(int productID, int accountID, int sizeID) {
        String query = "SELECT "
                + "    c.[quantity] AS 'CustomerQuantity', "
                + "    s.[quantity] AS 'TotalQuantity' "
                + "FROM "
                + "    [Cart] c "
                + "JOIN "
                + "    [SizeDetail] s ON c.[productID] = s.[pID] AND c.[sizeID] = s.[sizeID] "
                + "WHERE "
                + "    c.[productID] = ? "
                + "    AND c.[accountID] = ? "
                + "    AND c.[sizeID] = ?";

        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, accountID);
            ps.setInt(3, sizeID);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int customerQuantity = rs.getInt("CustomerQuantity");
                    int totalQuantity = rs.getInt("TotalQuantity");
                    // Kiểm tra nếu số lượng khách hàng nhập lớn hơn tổng số lượng
                    return customerQuantity >= totalQuantity;
                }
            }
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();  // Log or handle the exception as appropriate
        }

        return false;
    }

    public SizeDetail getProductSizesByProductIDAndSizeValue(int productID, int sizeValue) {
        String query = "SELECT sd.*, s.sizevalue\n"
                + "FROM SizeDetail sd\n"
                + "JOIN Size s ON sd.sizeID = s.sizeID\n"
                + "WHERE sd.pID = ? AND s.sizevalue = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productID);
            ps.setInt(2, sizeValue);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int pID = rs.getInt("pID");
                    int sizeID = rs.getInt("sizeID");
                    int quantity = rs.getInt("quantity");
                    int sizeValues = rs.getInt("sizevalue");
                    SizeDetail size = new SizeDetail(sizeID, pID, quantity, sizeValues);
                    return size;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        DAO dao = new DAO();
    }

}
