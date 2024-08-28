package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupMemberDAO {
	
	public static int insertGroupMember(int groupId, String username) {
		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;
		
        int x = -1;
        try {
            
    		String sql = "INSERT INTO group_members (group_id, user_id) VALUES (?, ?)";
			stmt = connection.prepareStatement(sql);
	        stmt.setInt(1, groupId);
	        stmt.setString(2, username);
	        x = stmt.executeUpdate();
	        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }
}
