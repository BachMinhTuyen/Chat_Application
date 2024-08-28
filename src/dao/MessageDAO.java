package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.MessagePOJO;

public class MessageDAO {
	public static int insertMessage(String sender, String receiver, int group_id, String message) {
		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;
		
        int x = -1;
        try {
            
    		String sql = "INSERT INTO Messages (sender_id, receiver_id, group_id, content, sent_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
			stmt = connection.prepareStatement(sql);
	        stmt.setString(1, sender);
	        stmt.setString(2, receiver);
	        stmt.setInt(3, group_id);
	        stmt.setString(4, message);
	        x = stmt.executeUpdate();
	        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
	}
	
	public static ArrayList<MessagePOJO> getMessagesByGroupId(int groupId) {
		ArrayList<MessagePOJO> messages = new ArrayList<>();

		MySQLDataAccess helper = new MySQLDataAccess();
		helper.open();
		
		Connection connection = MySQLDataAccess.getConnection();
        PreparedStatement stmt = null;
        
        try {
        	
            String query = "SELECT * FROM messages WHERE group_id = ?";
            stmt = connection.prepareStatement(query);
	        stmt.setInt(1, groupId);
	        ResultSet resultSet = stmt.executeQuery();
	        
            while (resultSet.next()) {
                MessagePOJO message = new MessagePOJO();
                message.setMessageId(resultSet.getInt("message_id"));
                message.setSenderId(resultSet.getString("sender_id"));
                message.setReceiverId(resultSet.getString("receiver_id"));
                message.setGroupId(resultSet.getInt("group_id"));
                message.setContent(resultSet.getString("content"));
                message.setSentAt(resultSet.getTimestamp("sent_at").toLocalDateTime());

                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }
}
