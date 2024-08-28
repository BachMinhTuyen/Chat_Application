package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler implements Runnable{
	private static Map<String, ArrayList<ClientHandler>> groupHandlers = new HashMap<>();
    
//	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String clientUsername;
	private String groupName;
	
	public ClientHandler (Socket socket) {
		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.clientUsername = bufferedReader.readLine();
			
			this.groupName = bufferedReader.readLine();
            groupHandlers.putIfAbsent(groupName, new ArrayList<>());
            groupHandlers.get(groupName).add(this);
			
//			clientHandlers.add(this);
			System.out.println("Server: " + clientUsername + " online");
			
		} catch (IOException e) {
			closeEverything(socket, bufferedReader, bufferedWriter);
		}
	}
	
	@Override
	public void run() {
		String messageFromClient;
		
		while (socket.isConnected()) {
			try {
				messageFromClient = bufferedReader.readLine();
				System.out.println("Received from client: " + messageFromClient);
				broadcastMessage(messageFromClient);
			} catch (Exception e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
				break;
			}
		}
	}
	public void broadcastMessage(String messageToSend) {
		System.out.println("Message To Send: " + messageToSend);
		 String[] parts = messageToSend.split("\\|", 2);
		 if (parts.length < 2) {
		        System.out.println("Invalid message format: " + messageToSend);
		        return; // Skip invalid message
	    }
		 if (!groupHandlers.containsKey(groupName)) {
		        System.out.println("Group not found: " + groupName);
		        return; // Skip if the group doesn't exist
		    }
	    String groupName = parts[0];
	    String messageContent = parts[1];
	    System.out.println("Group: " + groupName);
	    System.out.println("Message: " + messageContent);
        for (ClientHandler clientHandler : groupHandlers.get(groupName)) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(messageContent);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

//	public void broadcastMessage(String messageToSend) {
//	    
//		for (ClientHandler clientHandler : clientHandlers) {
//			try {
//				if (!clientHandler.clientUsername.equals(clientUsername)) {
//					clientHandler.bufferedWriter.write(messageToSend);
//					clientHandler.bufferedWriter.newLine();
//					clientHandler.bufferedWriter.flush();
//				}
//			} catch (IOException e) {
//				closeEverything(socket, bufferedReader, bufferedWriter);
//			}
//		}
//	}
	
	public void removeClientHandler() {
//		clientHandlers.remove(this);
		groupHandlers.get(groupName).remove(this);
		System.out.println("Server: " + clientUsername + " offline");
	} 
	
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
		removeClientHandler();
		try {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
