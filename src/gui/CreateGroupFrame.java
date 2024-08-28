package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.GroupDAO;
import dao.GroupMemberDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateGroupFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_GroupName;
	private String username;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CreateGroup frame = new CreateGroup();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public CreateGroupFrame(String username) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				setVisible(true);
			}
		});
		this.username = username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 263, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 230, 171);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lb_Title = new JLabel("Tạo Nhóm");
		lb_Title.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lb_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Title.setBounds(10, 10, 212, 19);
		panel.add(lb_Title);
		
		JLabel lb_GroupName = new JLabel("Tên nhóm:");
		lb_GroupName.setBounds(10, 50, 104, 13);
		panel.add(lb_GroupName);
		
		txt_GroupName = new JTextField();
		txt_GroupName.setBounds(81, 47, 123, 19);
		panel.add(txt_GroupName);
		txt_GroupName.setColumns(10);
		
		JLabel lb_Avatar = new JLabel("Avatar");
		lb_Avatar.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Avatar.setBounds(10, 76, 51, 51);
		panel.add(lb_Avatar);
		
		JButton btn_Upload = new JButton("Tải lên");
		btn_Upload.setBounds(81, 91, 68, 21);
		panel.add(btn_Upload);
		
		JButton btn_Create = new JButton("Tạo");
		btn_Create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createGroup();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		btn_Create.setBounds(81, 122, 85, 21);
		panel.add(btn_Create);
	}
	
	private void createGroup() throws IOException {
		if (GroupDAO.createGroup(txt_GroupName.getText(), "multi") != -1) {
			
			int groupId = GroupDAO.getGroupID(txt_GroupName.getText());
			GroupMemberDAO.insertGroupMember(groupId, username);
			
			JOptionPane.showMessageDialog(this, "Tạo nhóm thành công!");
	        setVisible(false);
		}
		else {
			JOptionPane.showMessageDialog(this, "Tạo nhóm thất bại!");
		}
	}
}
