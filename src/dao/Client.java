package dao;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private String groupName;

	private JPanel panelContainer;
    private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
	
    public Client(Socket socket, String username, String groupName, JPanel panelContainer) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
            this.groupName = groupName;
            this.panelContainer = panelContainer;
            
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.write(groupName);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            
            listenForMessage();
            startMessageSender();
            
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    
    public void listenForMessage() {
        Thread listenerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromGroupChat;
                try {
//                    while (socket.isConnected() && (messageFromGroupChat = bufferedReader.readLine()) != null) {
                	while (socket.isConnected() && (messageFromGroupChat = bufferedReader.readLine()) != null) {	
                		System.out.println("Received: " + messageFromGroupChat);
                    	final String message = messageFromGroupChat;
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JLabel label = new JLabel(message);
                                label.setFont(new Font("Tahoma", Font.BOLD, 13));
                                panelContainer.add(label, "wrap, pushx, left");
                                panelContainer.revalidate();
                                panelContainer.repaint();
                            }
                        });
                    }
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        });
        listenerThread.start();
    }
    public void sendMessage(String messageToSend) {
        messageQueue.add(messageToSend);
    }

    private void startMessageSender() {
        Thread senderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (socket.isConnected()) {
                        String messageToSend = messageQueue.take();
                        bufferedWriter.write(messageToSend);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        System.out.println(username + " : " + messageToSend);
                    }
                } catch (InterruptedException | IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        });
        senderThread.start();
    }
	
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
