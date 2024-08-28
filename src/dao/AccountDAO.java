package dao;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;

import pojo.AccountPOJO;

public class AccountDAO {
	public static boolean Login(String username, String password) {

		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
		try {
			String sql = "SELECT * FROM Users WHERE phone_number = ?";
			stmt = connection.prepareStatement(sql);
	        stmt.setString(1, username);
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
                String hashedPassword = rs.getString("password_hash");
                if (SecurityUtils.verifyPassword(password, hashedPassword)) {
                	System.out.println("Đăng nhập thành công");
                	return true;
                } else {
                    throw new Exception("Mật khẩu không hợp lệ");
                }
            } else {
                throw new Exception("Không tìm thấy người dùng");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Đăng nhập thất bại");
		return false;
	}
	public static boolean Register(AccountPOJO acc) {
		
		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;
        try {
        	
        	String sql = "SELECT * FROM Users WHERE phone_number = ?";
			stmt = connection.prepareStatement(sql);
	        stmt.setString(1, acc.getUsername());
	        ResultSet rs = stmt.executeQuery();
        	
	        if (rs.next()) {
	        	System.out.println("Người dùng này đã tồn tại");
	        	System.out.println("Đăng ký thất bại!");
	            return false;
	        } else {
	        	String hashedPassword = SecurityUtils.hashPassword(acc.getPassword());
	            sql = "INSERT INTO Users (phone_number, password_hash, full_name, avatar) VALUES (?, ?, ?, ?)";
	            
	            stmt = connection.prepareStatement(sql);
	            stmt.setString(1, acc.getUsername());
	            stmt.setString(2, hashedPassword);
	            stmt.setString(3, acc.getFullName());
	            stmt.setString(4, acc.getAvatar());
	            stmt.executeUpdate();
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println("Đăng ký thành công!");
        return true;
	}
	public static AccountPOJO getUser(String username) {
		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		AccountPOJO account = null;
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;
        try {
        	
        	String sql = "SELECT * FROM Users WHERE phone_number = ?";
			stmt = connection.prepareStatement(sql);
	        stmt.setString(1, username);
	        ResultSet rs = stmt.executeQuery();
        	
	        if (rs.next()) {
	        	System.out.println("Người dùng có tài khoản");
	        	account = new AccountPOJO();
	            account.setAvatar(rs.getString("avatar"));
	            account.setUsername(rs.getString("phone_number"));
	            account.setFullName(rs.getString("full_name"));
	            account.setPassword(rs.getString("password_hash"));
	        } else {
	        	System.out.println("Người dùng này chưa có tài khoản!");
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return account;
	}
	public static String getUsernameByFullName(String FullName) {
		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		
		String username = "";
		
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;
        try {
        	
        	String sql = "SELECT phone_number FROM Users WHERE full_name = ?";
			stmt = connection.prepareStatement(sql);
	        stmt.setString(1, FullName);
	        ResultSet rs = stmt.executeQuery();
        	
	        if (rs.next()) {
	        	username = rs.getString("phone_number");
	        } else {
	        	System.out.println("Người dùng này chưa có tài khoản!");
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return username;
	}
	public static ImageIcon scaleImage(String AvatarFileName) {
		ImageIcon icon = new ImageIcon("src/icon/" + AvatarFileName);
		int imageWidth = icon.getIconWidth();
		int imageHeight = icon.getIconHeight();
		double scaleX = 51.0 / imageWidth;
		double scaleY = 51.0 / imageHeight;
		double scale = Math.min(scaleX, scaleY);
		Image scaledImage = icon.getImage().getScaledInstance((int) (imageWidth * scale), (int) (imageHeight * scale), Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(scaledImage);
		return resizedIcon;
	}
}
