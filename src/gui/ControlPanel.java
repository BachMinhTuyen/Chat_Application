package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ControlPanel() {
		setLayout(null);
		
		JButton btn_UploadFile = new JButton("Tải file");
		btn_UploadFile.setBounds(12, 10, 85, 21);
		add(btn_UploadFile);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(12, 40, 422, 19);
		add(textField);
		
		JButton btn_Send = new JButton("Gửi");
		btn_Send.setBounds(436, 39, 85, 21);
		add(btn_Send);

	}
}
