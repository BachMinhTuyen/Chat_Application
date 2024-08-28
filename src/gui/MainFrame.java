package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AccountDAO;
import dao.Client;
import dao.GroupDAO;
import dao.GroupMemberDAO;
import dao.MessageDAO;
import net.miginfocom.swing.MigLayout;
import pojo.AccountPOJO;
import pojo.GroupPOJO;
import pojo.MessagePOJO;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txt_Username;
    private String username;
    private JPanel panel_ChatList;
    private JPanel panel_Container;
    
    private Client client;
    
    private JTextField txt_Message;
    private JLabel lb_Avatar;
    private JLabel lb_FullName;
    private AccountPOJO recipient;
    private JPanel panel_Content;
    private String groupName;
    

	
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
    public MainFrame(String username) throws IOException {
        this.username = username;
        setTitle("Welcome, " + username + " Chat Application.");
        initialize();
        
        client.sendMessage("no-group" + "|" + username);
//        client.sendMessage(username);
    }
    private void initialize() throws IOException {
    	String avatar = AccountDAO.getUser(username).getAvatar();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 993, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 245, 445);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
		
		panel_ChatList = new JPanel();
		panel_ChatList.setLayout(new MigLayout("fillx", "[]", "[]"));
		scrollPane.setViewportView(panel_ChatList);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 245, 69);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btn_CreateGroupChat = new JButton("Tạo nhóm");
		btn_CreateGroupChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateGroupFrame createGroupFrame = new CreateGroupFrame(username);
				createGroupFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						setVisible(true);
					}
				});
				createGroupFrame.setVisible(true);
				refresh();
			}
		});
		btn_CreateGroupChat.setBounds(117, 10, 118, 21);
		panel.add(btn_CreateGroupChat);
		
		JButton btn_CreateNewChat = new JButton("Tạo chat mới");
		btn_CreateNewChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_CreateNewChatActionPerformed();
				refresh();
			}
		});
		btn_CreateNewChat.setBounds(117, 41, 118, 21);
		panel.add(btn_CreateNewChat);
		
		txt_Username = new JTextField();
		txt_Username.setBounds(10, 11, 96, 19);
		panel.add(txt_Username);
		txt_Username.setColumns(10);
		

		panel_Content = new JPanel();
        panel_Content.setBounds(263, 0, 578, 529);
        contentPane.add(panel_Content);
        panel_Content.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(20, 0, 529, 69);
        panel_Content.add(panel_1);
        panel_1.setLayout(null);
        
        ImageIcon icon = AccountDAO.scaleImage(avatar);
        
		lb_Avatar = new JLabel("Avatar");
		lb_Avatar.setIcon(icon);
		lb_Avatar.setVerticalAlignment(SwingConstants.CENTER);
		lb_Avatar.setBounds(10, 10, 50, 49);
		panel_1.add(lb_Avatar);
		
		lb_FullName = new JLabel("Friend Name");
		lb_FullName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb_FullName.setBounds(70, 10, 249, 29);
		panel_1.add(lb_FullName);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 79, 529, 362);
		panel_Content.add(scrollPane_1);
		
		
		panel_Container = new JPanel();
		scrollPane_1.setViewportView(panel_Container);
		panel_Container.setLayout(new MigLayout("fillx", "[]", "[]"));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(20, 450, 529, 69);
		panel_Content.add(panel_3);
		panel_3.setLayout(null);
		
		txt_Message = new JTextField();
		txt_Message.setBounds(10, 40, 422, 19);
		panel_3.add(txt_Message);
		txt_Message.setColumns(10);
		
		JButton btn_UploadFile = new JButton("Tải file");
		btn_UploadFile.setBounds(10, 10, 85, 21);
		panel_3.add(btn_UploadFile);
		
		JButton btn_Send = new JButton("Gửi");
		btn_Send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage(username);
			}
		});
		btn_Send.setBounds(434, 39, 85, 21);
		panel_3.add(btn_Send);
		
		Socket socket = new Socket("localhost", 1234);
		client = new Client(socket, username, "no-group", panel_Container);
		
		refresh();
	}
	private void btn_CreateNewChatActionPerformed(){
		String inputSearch = txt_Username.getText();
		if (inputSearch.equals(this.username)) {
			JOptionPane.showMessageDialog(this, "Không thể chat với tài khoản này");
		}
		else {
			recipient = AccountDAO.getUser(txt_Username.getText());
			if (recipient != null) {	
	            createGroup();
	        }
			else {
				JOptionPane.showMessageDialog(this, "Người dùng này chưa có tài khoản!");
			}
		}
	}
	private void sendMessage(String sender) {
		String message = txt_Message.getText();
		groupName = lb_FullName.getText();
        int group_id = GroupDAO.getGroupID(groupName);
        
		if (!message.equals("") && group_id != 0) {
			JLabel label = new JLabel(message);
	        label.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        panel_Container.add(label, "wrap, pushx, right");
	        panel_Container.revalidate();
	        panel_Container.repaint();
	        txt_Message.setText("");
	        
	        String receiver = null;
	        
	        GroupPOJO g = GroupDAO.getGroupByID(group_id);
	        if (g.getGroupType().equals("single")) {
	        	String[] parts = g.getGroupName().split("-");
	        	String user1 = parts[0].trim();
	        	String user2 = parts[1].trim();
	        	
	        	AccountPOJO acc = AccountDAO.getUser(sender);
	        	
	        	if (user1.equals(acc.getFullName().trim())) {
	        		receiver = AccountDAO.getUsernameByFullName(user2);
	        	}
	        	if (user2.equals(acc.getFullName().trim())) {
	        		receiver = AccountDAO.getUsernameByFullName(user1);
	        	}
	        }
	        
	        int x = MessageDAO.insertMessage(sender, receiver, group_id, message);
	        client.sendMessage(g.getGroupName() + "|" + message);
//	        client.sendMessage(message);
		}
	}
	private void createGroup() {
		AccountPOJO user = AccountDAO.getUser(username);
		if (GroupDAO.createGroup(recipient.getFullName() + " - " + user.getFullName(), "single") != -1) {
			int groupId = GroupDAO.getGroupID(recipient.getFullName() + " - " + user.getFullName());
			GroupMemberDAO.insertGroupMember(groupId, username);
			GroupMemberDAO.insertGroupMember(groupId, recipient.getUsername());
			
			JOptionPane.showMessageDialog(this, "Tạo nhóm thành công!");
		}
		else {
			JOptionPane.showMessageDialog(this, "Tạo nhóm thất bại!");
		}
	}
	private void refresh() {
    	ArrayList<GroupPOJO> groupList = GroupDAO.getGroupList(username);
		
		for (GroupPOJO g : groupList) {
    		ChatPanel chatPanel = new ChatPanel(username, "Avatar.png", g.getGroupName(), "Hello", panel_Container, lb_FullName, lb_Avatar);
			chatPanel.setPreferredSize(new Dimension(217, 80));
			panel_ChatList.add(chatPanel, "wrap");
		}
		
		panel_ChatList.revalidate(); // Revalidate for layout changes
        panel_ChatList.repaint();
	}
}
