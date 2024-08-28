package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.AccountPOJO;
import pojo.GroupPOJO;

public class GroupDAO {
	
	public static int createGroup(String groupName, String groupType) {
		
		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;
		
        int x = -1;
        try {
            
    		String sql = "INSERT INTO Groups(group_name, group_type) VALUES (?, ?)";
			stmt = connection.prepareStatement(sql);
	        stmt.setString(1, groupName);
	        stmt.setString(2, groupType);
	        x = stmt.executeUpdate();
	        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }
	
	public static int getGroupID(String group_name) {
		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;
        int groupId = 0;
        try {
        	
        	String sql = "SELECT group_id FROM Groups WHERE group_name = ?";
			stmt = connection.prepareStatement(sql);
	        stmt.setString(1, group_name);
	        ResultSet rs = stmt.executeQuery();
        	
	        if (rs.next()) {
	        	groupId = rs.getInt("group_id");
	        }
	        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return groupId;
	}
	
	public static GroupPOJO getGroupByID (int group_id) {
		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		
    	GroupPOJO g = new GroupPOJO();
		
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;

        try {
        	String sql = "SELECT g.group_id, g.group_name, g.group_type "
				+ "FROM Groups g "
				+ "WHERE g.group_id = ? ";
        	stmt = connection.prepareStatement(sql);
	        stmt.setInt(1, group_id);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	g.setGroupId(rs.getInt("group_id"));
	        	g.setGroupName(rs.getString("group_name"));
	        	g.setGroupType(rs.getString("group_type"));
	        }
        }
        catch (Exception e) {
        	e.printStackTrace();
		}
        return g;
	}
	
	public static ArrayList<GroupPOJO> getGroupList(String username) {
		
		ArrayList<GroupPOJO> lst = new ArrayList<GroupPOJO>();
		
		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;

        try {
        	String sql = "SELECT g.group_id, g.group_name, g.group_type "
				+ "FROM Groups g "
				+ "JOIN Group_Members gm ON g.group_id = gm.group_id "
				+ "JOIN Users u ON gm.user_id = u.phone_number "
				+ "WHERE u.phone_number = ? ";
        	stmt = connection.prepareStatement(sql);
	        stmt.setString(1, username);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	GroupPOJO g = new GroupPOJO();
	        	g.setGroupId(rs.getInt("group_id"));
	        	g.setGroupName(rs.getString("group_name"));
	        	g.setGroupType(rs.getString("group_type"));
	        	lst.add(g);
	        }
        }
        catch (Exception e) {
        	e.printStackTrace();
		}
        return lst;
	}
}
