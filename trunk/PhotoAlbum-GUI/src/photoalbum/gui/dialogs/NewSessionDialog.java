package photoalbum.gui.dialogs;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class NewSessionDialog extends JDialog {
	
	public static enum DialogResult {CONNECT, CANCEL};

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel lbPassword = null;

	private JButton btnConnect = null;

	private JButton btnCancel = null;

	private JLabel lbHost = null;

	private JLabel lbPort = null;

	private JTextField txtHost = null;

	private JTextField txtPort = null;

	private JLabel lbDbType = null;

	private JComboBox comboBoxDbProvider = null;

	private JPasswordField pwdPassword = null;
	
	private DialogResult dialogResult = DialogResult.CANCEL;  //  @jve:decl-index=0:

	private JTextField txtSid = null;

	private JLabel lbSid = null;

	/**
	 * @param owner
	 */
	public NewSessionDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(246, 227);
		this.setMinimumSize(new Dimension(7, 33));
		this.setModal(true);
		this.setResizable(false);
		this.setTitle("New Session Dialog");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lbSid = new JLabel();
			lbSid.setBounds(new Rectangle(165, 75, 61, 16));
			lbSid.setText("SID:");
			lbDbType = new JLabel();
			lbDbType.setBounds(new Rectangle(15, 135, 75, 16));
			lbDbType.setText("DB Provider:");
			lbPort = new JLabel();
			lbPort.setBounds(new Rectangle(165, 15, 61, 16));
			lbPort.setText("Port:");
			lbHost = new JLabel();
			lbHost.setBounds(new Rectangle(15, 15, 61, 16));
			lbHost.setText("Host:");
			lbPassword = new JLabel();
			lbPassword.setBounds(new Rectangle(15, 75, 61, 16));
			lbPassword.setText("Password:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lbPassword, null);
			jContentPane.add(getBtnConnect(), null);
			jContentPane.add(getBtnCancel(), null);
			jContentPane.add(lbHost, null);
			jContentPane.add(lbPort, null);
			jContentPane.add(getTxtHost(), null);
			jContentPane.add(getTxtPort(), null);
			jContentPane.add(lbDbType, null);
			jContentPane.add(getComboBoxDbProvider(), null);
			jContentPane.add(getPwdPassword(), null);
			jContentPane.add(getTxtSid(), null);
			jContentPane.add(lbSid, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes btnConnect	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConnect() {
		if (btnConnect == null) {
			btnConnect = new JButton();
			btnConnect.setBounds(new Rectangle(15, 165, 91, 16));
			btnConnect.setText("Connect");
			btnConnect.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (validateForm()) {
						dialogResult = DialogResult.CONNECT;
						setVisible(false);
					}
				}
			});
		}
		return btnConnect;
	}

	/**
	 * This method initializes btnCancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton();
			btnCancel.setBounds(new Rectangle(135, 165, 91, 16));
			btnCancel.setText("Cancel");
			btnCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
				}
			});
		}
		return btnCancel;
	}

	/**
	 * This method initializes txtHost	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtHost() {
		if (txtHost == null) {
			txtHost = new JTextField();
			txtHost.setBounds(new Rectangle(15, 45, 136, 16));
			txtHost.setText("localhost");
		}
		return txtHost;
	}

	/**
	 * This method initializes txtPort	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtPort() {
		if (txtPort == null) {
			txtPort = new JTextField();
			txtPort.setBounds(new Rectangle(165, 45, 61, 16));
			txtPort.setText("1521");
			txtPort.setColumns(4);
		}
		return txtPort;
	}

	/**
	 * This method initializes comboBoxDbProvider	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboBoxDbProvider() {
		if (comboBoxDbProvider == null) {
			comboBoxDbProvider = new JComboBox();
			comboBoxDbProvider.setBounds(new Rectangle(103, 135, 123, 16));
		}
		return comboBoxDbProvider;
	}

	/**
	 * This method initializes pwdPassword	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPwdPassword() {
		if (pwdPassword == null) {
			pwdPassword = new JPasswordField();
			pwdPassword.setBounds(new Rectangle(15, 105, 136, 16));
			pwdPassword.setColumns(20);
			pwdPassword.setEchoChar('*');
		}
		return pwdPassword;
	}
	
	public DialogResult showDialog() {
		this.setVisible(true);
		return this.dialogResult;
	}

	/**
	 * This method initializes txtSid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtSid() {
		if (txtSid == null) {
			txtSid = new JTextField();
			txtSid.setBounds(new Rectangle(165, 105, 61, 16));
			txtSid.setText("xe");
		}
		return txtSid;
	}
	
	private boolean validateForm() {
		this.getTxtHost().setText(this.getTxtHost().getText().trim());
		if (this.getTxtHost().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Host cannot be empty!", "Invalid Host", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		this.getTxtPort().setText(this.getTxtPort().getText().trim());
		if (this.getTxtPort().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Port cannot be empty!", "Invalid Port", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			try {
				int port = Integer.parseInt(this.getTxtPort().getText());
				if (port < 0 || port > 65535) {
					JOptionPane.showMessageDialog(this, "Port must be an integer between 0 and 65535!", "Invalid Port", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (NumberFormatException exc) {
				JOptionPane.showMessageDialog(this, "Port must be an integer between 0 and 65535!s", "Invalid Port", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		this.getTxtSid().setText(this.getTxtSid().getText().trim());
		if (this.getTxtSid().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "SID cannot be empty!Invalid SID", "", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public String getHost() {
		return this.getTxtHost().getText();
	}
	
	public String getPort() {
		return this.getTxtPort().getText();
	}
	
	public String getPassword() {
		return String.valueOf(this.getPwdPassword().getPassword());
	}
	
	public String getSid() {
		return this.getTxtSid().getText();
	}
	
	public String getDbProvider() {
		return this.getComboBoxDbProvider().getSelectedItem().toString();
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
