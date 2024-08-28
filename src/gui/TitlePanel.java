package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class TitlePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TitlePanel() {
		setLayout(null);
		
		JLabel lb_Avatar = new JLabel("Avatar");
		lb_Avatar.setVerticalAlignment(SwingConstants.CENTER);
		lb_Avatar.setBounds(10, 10, 50, 49);
		add(lb_Avatar);
		
		JLabel lb_FullName = new JLabel("Friend Name");
		lb_FullName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb_FullName.setBounds(70, 10, 249, 29);
		add(lb_FullName);

	}
}
