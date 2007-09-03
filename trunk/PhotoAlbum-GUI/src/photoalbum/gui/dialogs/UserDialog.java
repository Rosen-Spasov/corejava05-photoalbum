package photoalbum.gui.dialogs;

import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import photoalbum.common.Common.DialogResult;

public class UserDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel lbUsername = null;

	private JLabel lbPassword = null;

	private JTextField txtUsername = null;

	private JPasswordField pwdPassword = null;

	private JLabel lbFirstName = null;

	private JLabel lbLastName = null;

	private JTextField txtFirstName = null;

	private JTextField txtLastName = null;

	private JButton btnOK = null;

	private JButton btnCancel = null;
	
	private DialogResult dialogResult = DialogResult.CANCEL;
	
	public DialogResult getDialogResult() {
		return this.dialogResult;
	}
	
	private void setDialogResult(DialogResult dialogResult) {
		this.dialogResult = dialogResult;
	}

	/**
	 * @param owner
	 */
	public UserDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 170);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle("User Dialog");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lbLastName = new JLabel();
			lbLastName.setBounds(new Rectangle(165, 60, 91, 16));
			lbLastName.setText("Last name:");
			lbFirstName = new JLabel();
			lbFirstName.setBounds(new Rectangle(15, 60, 91, 16));
			lbFirstName.setText("First name:");
			lbPassword = new JLabel();
			lbPassword.setBounds(new Rectangle(165, 15, 91, 16));
			lbPassword.setText("Password:");
			lbUsername = new JLabel();
			lbUsername.setBounds(new Rectangle(15, 15, 91, 16));
			lbUsername.setText("Username:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lbUsername, null);
			jContentPane.add(getTxtUsername(), null);
			jContentPane.add(lbPassword, null);
			jContentPane.add(getPwdPassword(), null);
			jContentPane.add(lbFirstName, null);
			jContentPane.add(getTxtFirstName(), null);
			jContentPane.add(lbLastName, null);
			jContentPane.add(getTxtLastName(), null);
			jContentPane.add(getBtnOK(), null);
			jContentPane.add(getBtnCancel(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes txtUsername	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtUsername() {
		if (txtUsername == null) {
			txtUsername = new JTextField();
			txtUsername.setBounds(new Rectangle(15, 30, 121, 16));
		}
		return txtUsername;
	}

	/**
	 * This method initializes pwdPassword	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getPwdPassword() {
		if (pwdPassword == null) {
			pwdPassword = new JPasswordField();
			pwdPassword.setBounds(new Rectangle(150, 30, 121, 16));
		}
		return pwdPassword;
	}

	/**
	 * This method initializes txtFirstName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtFirstName() {
		if (txtFirstName == null) {
			txtFirstName = new JTextField();
			txtFirstName.setBounds(new Rectangle(15, 75, 121, 16));
		}
		return txtFirstName;
	}

	/**
	 * This method initializes txtLastName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtLastName() {
		if (txtLastName == null) {
			txtLastName = new JTextField();
			txtLastName.setBounds(new Rectangle(150, 75, 121, 16));
		}
		return txtLastName;
	}

	/**
	 * This method initializes btnOK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton();
			btnOK.setBounds(new Rectangle(30, 105, 91, 16));
			btnOK.setText("OK");
			btnOK.addActionListener(this);
		}
		return btnOK;
	}

	/**
	 * This method initializes btnCancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton();
			btnCancel.setBounds(new Rectangle(165, 105, 91, 16));
			btnCancel.setText("Cancel");
			btnCancel.addActionListener(this);
		}
		return btnCancel;
	}
	
	private boolean validateForm() {		
		this.getTxtUsername().setText(this.getTxtUsername().getText().trim());
		if (this.getTxtUsername().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Username cannot be empty!", "Invalid Username", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		this.getTxtFirstName().setText(this.getTxtFirstName().getText().trim());
		if (this.getTxtFirstName().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "First name cannot be empty!", "Invalid First Name", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		this.getTxtLastName().setText(this.getTxtLastName().getText().trim());
		if (this.getTxtLastName().getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Last name cannot be empty!", "Invalid Last Name", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	public DialogResult showDialog() {
		this.setVisible(true);
		return this.getDialogResult();
	}
	
	public String getUsername() {
		return this.getTxtUsername().getText();
	}
	
	public String getPassword() {
		return String.valueOf(this.getPwdPassword().getPassword());
	}
	
	public String getFirstName() {
		return this.getTxtFirstName().getText();
	}
	
	public String getLastName() {
		return this.getTxtLastName().getText();
	}
	
	public void resetDialog() {
		resetDialog("", "", "", "");
	}
	
	public void resetDialog(String username, String password, String firstName, String lastName) {
		this.getTxtUsername().setText(username);
		this.getPwdPassword().setText(password);
		this.getTxtFirstName().setText(firstName);
		this.getTxtLastName().setText(lastName);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.getBtnOK()) {
			if (validateForm()) {
				setDialogResult(DialogResult.OK);
				setVisible(false);
			}
		} else if (e.getSource() == this.getBtnCancel()) {
			setDialogResult(DialogResult.CANCEL);
			setVisible(false);
		}
		
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
