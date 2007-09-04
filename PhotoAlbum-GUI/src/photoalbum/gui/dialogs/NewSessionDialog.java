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

import photoalbum.common.PhotoAlbumManager.DBProvider;
import photoalbum.common.PhotoAlbumManager.DialogResult;

public class NewSessionDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel lbPassword = null;

	private JButton btnConnect = null;

	private JButton btnCancel = null;

	private JLabel lbDbHost = null;

	private JLabel lbDbPort = null;

	private JTextField txtDbHost = null;

	private JTextField txtDbPort = null;

	private JLabel lbDbType = null;

	private JComboBox comboBoxDbProvider = null;

	private JPasswordField pwdPassword = null;
	
	private DialogResult dialogResult = DialogResult.CANCEL;  //  @jve:decl-index=0:

	private JTextField txtSid = null;

	private JLabel lbSid = null;

	private JLabel lbHost = null;

	private JTextField txtHost = null;

	private JTextField txtPort = null;

	private JLabel lbPort = null;
	
	public DialogResult getDialogResult() {
		return this.dialogResult;
	}
	
	private void setDialogResult(DialogResult dialogResult) {
		this.dialogResult = dialogResult;
	}

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
		this.setSize(246, 242);
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
			lbPort = new JLabel();
			lbPort.setBounds(new Rectangle(165, 15, 61, 16));
			lbPort.setText("Port:");
			lbHost = new JLabel();
			lbHost.setBounds(new Rectangle(15, 15, 61, 16));
			lbHost.setText("Host:");
			lbSid = new JLabel();
			lbSid.setBounds(new Rectangle(165, 105, 61, 16));
			lbSid.setText("SID:");
			lbDbType = new JLabel();
			lbDbType.setBounds(new Rectangle(17, 150, 75, 16));
			lbDbType.setText("DB Provider:");
			lbDbPort = new JLabel();
			lbDbPort.setBounds(new Rectangle(165, 60, 61, 16));
			lbDbPort.setText("DB Port:");
			lbDbHost = new JLabel();
			lbDbHost.setBounds(new Rectangle(15, 60, 61, 16));
			lbDbHost.setText("DB Host:");
			lbPassword = new JLabel();
			lbPassword.setBounds(new Rectangle(15, 105, 61, 16));
			lbPassword.setText("Password:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnConnect(), null);
			jContentPane.add(getBtnCancel(), null);
			jContentPane.add(lbDbHost, null);
			jContentPane.add(getTxtDbHost(), null);
			jContentPane.add(lbDbPort, null);
			jContentPane.add(getTxtDbPort(), null);
			jContentPane.add(lbDbType, null);
			jContentPane.add(getComboBoxDbProvider(), null);
			jContentPane.add(lbPassword, null);
			jContentPane.add(getPwdPassword(), null);
			jContentPane.add(lbSid, null);
			jContentPane.add(getTxtSid(), null);
			jContentPane.add(lbHost, null);
			jContentPane.add(getTxtHost(), null);
			jContentPane.add(lbPort, null);
			jContentPane.add(getTxtPort(), null);
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
			btnConnect.setBounds(new Rectangle(17, 180, 91, 16));
			btnConnect.setText("Connect");
			btnConnect.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (validateForm()) {
						setDialogResult(DialogResult.CONNECT);
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
			btnCancel.setBounds(new Rectangle(137, 180, 91, 16));
			btnCancel.setText("Cancel");
			btnCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setDialogResult(DialogResult.CANCEL);
					setVisible(false);
				}
			});
		}
		return btnCancel;
	}

	/**
	 * This method initializes txtDbHost	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDbHost() {
		if (txtDbHost == null) {
			txtDbHost = new JTextField();
			txtDbHost.setBounds(new Rectangle(15, 75, 136, 16));
			txtDbHost.setText("localhost");
		}
		return txtDbHost;
	}

	/**
	 * This method initializes txtDbPort	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDbPort() {
		if (txtDbPort == null) {
			txtDbPort = new JTextField();
			txtDbPort.setBounds(new Rectangle(165, 75, 61, 16));
			txtDbPort.setText("1521");
			txtDbPort.setColumns(4);
		}
		return txtDbPort;
	}

	/**
	 * This method initializes comboBoxDbProvider	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComboBoxDbProvider() {
		if (comboBoxDbProvider == null) {
			comboBoxDbProvider = new JComboBox(DBProvider.values());
			comboBoxDbProvider.setBounds(new Rectangle(105, 150, 121, 16));
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
			pwdPassword.setBounds(new Rectangle(15, 120, 136, 16));
			pwdPassword.setColumns(20);
			pwdPassword.setEchoChar('*');
		}
		return pwdPassword;
	}
	
	public DialogResult showDialog() {
		this.setVisible(true);
		return this.getDialogResult();
	}

	/**
	 * This method initializes txtSid	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtSid() {
		if (txtSid == null) {
			txtSid = new JTextField();
			txtSid.setBounds(new Rectangle(165, 120, 61, 16));
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
		this.getTxtDbHost().setText(this.getTxtDbHost().getText().trim());
		if (this.getTxtDbHost().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "DB host cannot be empty!", "Invalid DB Host", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		this.getTxtDbPort().setText(this.getTxtDbPort().getText().trim());
		if (this.getTxtDbPort().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "DB port cannot be empty!", "Invalid DB Port", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			try {
				int port = Integer.parseInt(this.getTxtDbPort().getText());
				if (port < 0 || port > 65535) {
					JOptionPane.showMessageDialog(this, "DB port must be an integer between 0 and 65535!", "Invalid DB Port", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (NumberFormatException exc) {
				JOptionPane.showMessageDialog(this, "Port must be an integer between 0 and 65535!s", "Invalid DB Port", JOptionPane.ERROR_MESSAGE);
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
	
	public String getDbHost() {
		return this.getTxtDbHost().getText();
	}
	
	public String getDbPort() {
		return this.getTxtDbPort().getText();
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
	
	public String getHost() {
		return this.getTxtHost().getText();
	}
	
	public String getPort() {
		return this.getTxtPort().getText();
	}

	/**
	 * This method initializes txtHost	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtHost() {
		if (txtHost == null) {
			txtHost = new JTextField();
			txtHost.setBounds(new Rectangle(15, 30, 136, 16));
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
			txtPort.setBounds(new Rectangle(165, 30, 61, 16));
			txtPort.setText("3301");
		}
		return txtPort;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
