package photoalbum.gui.dialogs;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import photoalbum.gui.frames.MainFrame.DialogResult;

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
	
	private boolean editMode = false;
	
	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
		this.getTxtUsername().setEnabled(!editMode);
	}

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
		this.setSize(292, 167);
		this.setModal(true);
		this.setResizable(false);
		this.setTitle("Потребителски Диалог");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.insets = new Insets(7, 22, 14, 30);
			gridBagConstraints9.gridy = 2;
			gridBagConstraints9.ipadx = 24;
			gridBagConstraints9.ipady = -10;
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.insets = new Insets(7, 30, 14, 22);
			gridBagConstraints8.gridy = 2;
			gridBagConstraints8.ipadx = 41;
			gridBagConstraints8.ipady = -10;
			gridBagConstraints8.gridx = 0;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridx = 1;
			gridBagConstraints7.gridy = 1;
			gridBagConstraints7.ipadx = 117;
			gridBagConstraints7.ipady = -4;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.insets = new Insets(22, 7, 7, 15);
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(7, 7, 22, 45);
			gridBagConstraints6.gridy = 1;
			gridBagConstraints6.ipadx = 33;
			gridBagConstraints6.gridx = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.ipadx = 117;
			gridBagConstraints5.ipady = -4;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(22, 15, 7, 7);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(7, 15, 22, 37);
			gridBagConstraints4.gridy = 1;
			gridBagConstraints4.ipadx = 65;
			gridBagConstraints4.gridx = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.ipadx = 117;
			gridBagConstraints3.ipady = -4;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(30, 7, 7, 15);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(15, 7, 22, 45);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.ipadx = 43;
			gridBagConstraints2.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 117;
			gridBagConstraints1.ipady = -4;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(30, 15, 7, 7);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(15, 15, 22, 37);
			gridBagConstraints.gridy = 0;
			gridBagConstraints.ipadx = 19;
			gridBagConstraints.gridx = 0;
			lbLastName = new JLabel();
			lbLastName.setText("Фамилия:");
			lbFirstName = new JLabel();
			lbFirstName.setText("Име:");
			lbPassword = new JLabel();
			lbPassword.setText("Парола:");
			lbUsername = new JLabel();
			lbUsername.setText("Потребител:");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(lbUsername, gridBagConstraints);
			jContentPane.add(getTxtUsername(), gridBagConstraints1);
			jContentPane.add(lbPassword, gridBagConstraints2);
			jContentPane.add(getPwdPassword(), gridBagConstraints3);
			jContentPane.add(lbFirstName, gridBagConstraints4);
			jContentPane.add(getTxtFirstName(), gridBagConstraints5);
			jContentPane.add(lbLastName, gridBagConstraints6);
			jContentPane.add(getTxtLastName(), gridBagConstraints7);
			jContentPane.add(getBtnOK(), gridBagConstraints8);
			jContentPane.add(getBtnCancel(), gridBagConstraints9);
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
			btnOK.setText("ОК");
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
			btnCancel.setText("Отказ");
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
