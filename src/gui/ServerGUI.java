package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Server;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.awt.event.ActionEvent;

public class ServerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lb_Message;
	ServerSocket serverSocket;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI frame = new ServerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 292, 125);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lb_title = new JLabel("Chat Application Server");
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_title.setBounds(10, 10, 272, 25);
		panel.add(lb_title);
		
		JButton btn_Start = new JButton("Start");
		btn_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lb_Message.setText("Mở server ...");
				lb_Message.setForeground(Color.GREEN);
				
			}
		});
		btn_Start.setBounds(39, 55, 85, 21);
		panel.add(btn_Start);
		
		JButton btn_Stop = new JButton("Stop");
		btn_Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lb_Message.setText("Dừng server ...");
				lb_Message.setForeground(Color.RED);
				
			}
		});
		btn_Stop.setBounds(150, 55, 85, 21);
		panel.add(btn_Stop);
		
		lb_Message = new JLabel("Server chưa khởi động");
		lb_Message.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb_Message.setBounds(10, 86, 272, 29);
		panel.add(lb_Message);
	}

}
