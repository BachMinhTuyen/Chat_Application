package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.FileUtils;

import dao.AccountDAO;
import pojo.AccountPOJO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_Username;
	private JTextField txt_Password;
	private JTextField txt_FullName;
	private JTextField txt_Avatar;
	
	String projectPath = System.getProperty("user.dir"); // Get project directory
	File selectedFile = null;
	String iconDirPath = "";
	String fileName = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_Title = new JLabel("Register");
		lb_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Title.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lb_Title.setBounds(100, 29, 96, 13);
		contentPane.add(lb_Title);
		
		JLabel lb_Username = new JLabel("Số điện thoại:");
		lb_Username.setBounds(32, 74, 85, 13);
		contentPane.add(lb_Username);
		
		txt_Username = new JTextField();
		txt_Username.setBounds(133, 71, 137, 19);
		contentPane.add(txt_Username);
		txt_Username.setColumns(10);
		
		JLabel lb_Password = new JLabel("Mật khẩu:");
		lb_Password.setBounds(32, 103, 85, 13);
		contentPane.add(lb_Password);
		
		txt_Password = new JTextField();
		txt_Password.setBounds(133, 100, 137, 19);
		contentPane.add(txt_Password);
		txt_Password.setColumns(10);
		
		JLabel lb_FullName = new JLabel("Họ và tên:");
		lb_FullName.setBounds(32, 142, 85, 13);
		contentPane.add(lb_FullName);
		
		txt_FullName = new JTextField();
		txt_FullName.setBounds(133, 139, 137, 19);
		contentPane.add(txt_FullName);
		txt_FullName.setColumns(10);
		
		JLabel lb_Avatar = new JLabel("Ảnh đại diện:");
		lb_Avatar.setBounds(32, 179, 85, 13);
		contentPane.add(lb_Avatar);
		
		txt_Avatar = new JTextField();
		txt_Avatar.setBounds(133, 206, 137, 19);
		txt_Avatar.setText("Avatar.png");
		txt_Avatar.setEditable(false);
		contentPane.add(txt_Avatar);
		txt_Avatar.setColumns(10);
		
		JButton btn_Upload = new JButton("Tải lên");
		btn_Upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_UploadActionPerformed();
			}
		});
		btn_Upload.setBounds(133, 175, 96, 21);
		contentPane.add(btn_Upload);
		
		JButton btn_Register = new JButton("Đăng ký");
		btn_Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_RegisterActionPerformed(e);
			}
		});
		btn_Register.setBounds(164, 247, 106, 21);
		contentPane.add(btn_Register);
		
		JLabel lb_Login = new JLabel("Bạn đã có tài khoản?");
		lb_Login.setBounds(32, 282, 143, 13);
		contentPane.add(lb_Login);
		
		JButton btn_Login = new JButton("Đăng nhập");
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				dispose();
			}
		});
		btn_Login.setBounds(164, 278, 106, 21);
		contentPane.add(btn_Login);
	}
	private void btn_RegisterActionPerformed(java.awt.event.ActionEvent e) {
		AccountPOJO accountPOJO = new AccountPOJO();
		accountPOJO.setUsername(txt_Username.getText());
		accountPOJO.setPassword(txt_Password.getText());
		accountPOJO.setFullName(txt_FullName.getText());
		accountPOJO.setAvatar(txt_Avatar.getText());
		if (AccountDAO.Register(accountPOJO)) {
			if (fileName != "") {
				// Save the file to the src/icon directory
		        File savedFile = new File(iconDirPath + "/" + fileName);
		        try {
		            FileUtils.copyFile(selectedFile, savedFile);
		        } catch (IOException ex) {
		            // Handle file saving error
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi lưu ảnh.");
		        }
			}
			String message = "Đăng ký thành công";
			JOptionPane.showMessageDialog(this, message);
		}
		else {
			String message = "Đăng ký thất bại";
			JOptionPane.showMessageDialog(this, message);
		}
	}
	private void btn_UploadActionPerformed() {
		JFileChooser fileChooser = new JFileChooser();
		
		iconDirPath = projectPath + "/src/icon"; // Construct path to icon directory
		fileChooser.setCurrentDirectory(new File(iconDirPath));
		
		int result = fileChooser.showOpenDialog(this); // Display dialog
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    // Get the selected file
		    selectedFile  = fileChooser.getSelectedFile();

		    // Get the file name and extension
		    fileName = selectedFile.getName();
		    String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

		    // Validate the file extension (e.g., only allow images)
		    if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")) {
		        

		        // Update the txt_Avatar field with the file name
		        txt_Avatar.setText(fileName);
		    } else {
		        // Display an error message for invalid file type
		        JOptionPane.showMessageDialog(this, "Chỉ hỗ trợ các định dạng ảnh PNG, JPG, JPEG.");
		    }
		}
	}
}
