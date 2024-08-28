package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AccountDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_Username;
	private JTextField txt_Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_Username = new JLabel("Số điện thoại:");
		lb_Username.setBounds(41, 94, 87, 13);
		contentPane.add(lb_Username);
		
		txt_Username = new JTextField();
		txt_Username.setBounds(138, 91, 124, 19);
		contentPane.add(txt_Username);
		txt_Username.setColumns(10);
		
		JLabel lb_Password = new JLabel("Mật khẩu:");
		lb_Password.setBounds(41, 136, 87, 13);
		contentPane.add(lb_Password);
		
		txt_Password = new JTextField();
		txt_Password.setBounds(138, 133, 124, 19);
		contentPane.add(txt_Password);
		txt_Password.setColumns(10);
		
		JButton btn_Login = new JButton("Đăng nhập");
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btn_LoginActionPerformed(e);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		btn_Login.setBounds(164, 175, 98, 21);
		contentPane.add(btn_Login);
		
		JLabel lb_Title = new JLabel("Login");
		lb_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Title.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lb_Title.setBounds(138, 35, 85, 21);
		contentPane.add(lb_Title);
		
		JButton btn_Register = new JButton("Đăng ký");
		btn_Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame registerFrame = new RegisterFrame();
				registerFrame.setVisible(true);
				dispose();
			}
		});
		btn_Register.setBounds(164, 206, 98, 21);
		contentPane.add(btn_Register);
		
		JLabel lb_Register = new JLabel("Bạn chưa có tài khoản:");
		lb_Register.setBounds(41, 210, 126, 13);
		contentPane.add(lb_Register);
	}
	private void btn_LoginActionPerformed(java.awt.event.ActionEvent e) throws IOException {
		String username = txt_Username.getText();
		String password = txt_Password.getText();
		if (AccountDAO.Login(username, password)) {
			String message = "Đăng nhập thành công";
			JOptionPane.showMessageDialog(this, message);
			
			MainFrame mainFrame = new MainFrame(username);
	        mainFrame.setVisible(true);
//	        dispose(); // Close the current frame
	        setVisible(false);
		}
		else {
			String message = "Đăng nhập thất bại";
			JOptionPane.showMessageDialog(this, message);
		}
	}
}
