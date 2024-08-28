package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDataAccess {
	private static Connection connection;
	
	public static Connection getConnection() {
		return connection;
	}
	
	public void open() {
		String strServer = "localhost";
		String strDatabase = "Chat_Application";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://" + strServer + ":1433;encrypt=false;trustServerCertificate=false;databaseName=" + strDatabase + ";user=sa;password=123456";
			connection = DriverManager.getConnection(connectionURL);
			System.out.println("Kết nối database thành công!");
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			System.err.println("Lỗi kết nối database: " + ex.getMessage());
		}
	}
	
	public void close() {
		try {
			this.connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ResultSet executeQuery (String sql) {
		ResultSet rs = null;
		try {
			Statement stm = connection.createStatement();
			rs = stm.executeQuery(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	public int executeUpdate (String sql) {
		int n = -1;
		try {
			Statement stm = connection.createStatement();
			n = stm.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return n;
	}
}
