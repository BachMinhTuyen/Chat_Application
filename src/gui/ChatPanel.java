package gui;

import dao.Client;
import dao.GroupDAO;
import dao.MessageDAO;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.border.LineBorder;

import dao.AccountDAO;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import gui.MainFrame;
import pojo.MessagePOJO;
public class ChatPanel extends JPanel {

	private static final long serialVersionUID = 1L;
//	private JPanel panel_Container;
	private JPanel panel_Content;
	private JLabel lb_FullNameChat;
	private JLabel lb_AvatarChat;
	private MainFrame mainFrame;
	
	private Color defaultColor;
    private Color hoverColor;
	private JPanel panelContainer;
	/**
	 * Create the panel.
	 */
	public ChatPanel(String username, String avatar, String groupName, String latestMessage, JPanel panel_container, JLabel lb_FullNameChat, JLabel lb_AvatarChat) {
		defaultColor = getBackground();
		hoverColor = new Color(230, 230, 230);
		
		this.panelContainer = panel_container;
		this.lb_FullNameChat = lb_FullNameChat;
		this.lb_AvatarChat = lb_AvatarChat;
		
		
		ImageIcon icon = AccountDAO.scaleImage(avatar);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(hoverColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				 setBackground(defaultColor);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Bắt đầu chat với " + groupName);
				lb_FullNameChat.setText(groupName);
				lb_AvatarChat.setIcon(icon);
				
				panel_container.removeAll();
				 
				int groupId = GroupDAO.getGroupID(groupName);
				ArrayList<MessagePOJO> lst = MessageDAO.getMessagesByGroupId(groupId);
				for (MessagePOJO m : lst) {
					JLabel label = new JLabel(m.getContent());
			        
			        if (m.getSenderId().equals(username)) {
			        	label.setFont(new Font("Tahoma", Font.PLAIN, 13));
			            panel_container.add(label, "wrap, pushx, right");
			        } else {
			        	label.setFont(new Font("Tahoma", Font.BOLD, 13));
			            panel_container.add(label, "wrap, pushx, left");
			        }
				}
				panel_container.revalidate();
				panel_container.repaint();
			}
		});
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(217, 80));
		setLayout(null);
		
		JLabel lb_Avatar = new JLabel(avatar);
		lb_Avatar.setIcon(icon);
		lb_Avatar.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Avatar.setBounds(10, 10, 55, 55);
		add(lb_Avatar);
		
		JLabel lb_FullName = new JLabel(groupName);
		lb_FullName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_FullName.setBounds(75, 10, 132, 23);
		add(lb_FullName);
		
		JLabel lb_Content = new JLabel(latestMessage);
		lb_Content.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lb_Content.setBounds(75, 43, 132, 22);
		add(lb_Content);

	}
}
