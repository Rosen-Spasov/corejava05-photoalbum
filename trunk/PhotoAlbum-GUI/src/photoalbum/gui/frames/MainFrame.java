package photoalbum.gui.frames;

import hibernate.utils.HibernateConnectionManager;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import photoalbum.gui.dialogs.NewSessionDialog;
import photoalbum.gui.dialogs.NewSessionDialog.DialogResult;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenuBar mBarMain = null;

	private JMenu menuFile = null;

	private JMenu menuSession = null;

	private JMenuItem mItemNewSession = null;

	private JScrollPane scrollPaneUsers = null;

	private JPanel panelButtons = null;

	private JButton btnExit = null;

	private JButton btnAdd = null;

	private JButton btnEdit = null;

	private JButton btnDelete = null;

	private JMenuItem mItemExit = null;
	
	private NewSessionDialog newSessionDialog = null;

	public NewSessionDialog getNewSessionDialog() {
		if (this.newSessionDialog == null) {
			this.newSessionDialog = new NewSessionDialog(this);
		}
		return this.newSessionDialog;
	}

	/**
	 * This is the default constructor
	 */
	public MainFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(564, 328);
		this.setJMenuBar(getMBarMain());
		this.setContentPane(getJContentPane());
		this.setTitle("Manage Photo Album");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getScrollPaneUsers(), null);
			jContentPane.add(getPanelButtons(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes mBarMain	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMBarMain() {
		if (mBarMain == null) {
			mBarMain = new JMenuBar();
			mBarMain.add(getMenuFile());
			mBarMain.add(getMenuSession());
		}
		return mBarMain;
	}

	/**
	 * This method initializes menuFile	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMenuFile() {
		if (menuFile == null) {
			menuFile = new JMenu();
			menuFile.setText("File");
			menuFile.add(getMItemExit());
		}
		return menuFile;
	}

	/**
	 * This method initializes menuSession	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMenuSession() {
		if (menuSession == null) {
			menuSession = new JMenu();
			menuSession.setText("Session");
			menuSession.add(getMItemNewSession());
		}
		return menuSession;
	}

	/**
	 * This method initializes mItemNewSession	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMItemNewSession() {
		if (mItemNewSession == null) {
			mItemNewSession = new JMenuItem();
			mItemNewSession.setText("New Session");
			mItemNewSession.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					newSession();
				}
			});
		}
		return mItemNewSession;
	}

	/**
	 * This method initializes scrollPaneUsers	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollPaneUsers() {
		if (scrollPaneUsers == null) {
			scrollPaneUsers = new JScrollPane();
			scrollPaneUsers.setLayout(null);
			scrollPaneUsers.setBounds(new Rectangle(0, 0, 451, 271));
		}
		return scrollPaneUsers;
	}

	/**
	 * This method initializes panelButtons	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelButtons() {
		if (panelButtons == null) {
			panelButtons = new JPanel();
			panelButtons.setLayout(null);
			panelButtons.setBounds(new Rectangle(450, 0, 106, 271));
			panelButtons.add(getBtnExit(), null);
			panelButtons.add(getBtnAdd(), null);
			panelButtons.add(getBtnEdit(), null);
			panelButtons.add(getBtnDelete(), null);
		}
		return panelButtons;
	}

	/**
	 * This method initializes btnExit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton();
			btnExit.setBounds(new Rectangle(15, 240, 76, 16));
			btnExit.setText("Exit");
			btnExit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					exit();
				}
			});
		}
		return btnExit;
	}

	/**
	 * This method initializes btnAdd	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton();
			btnAdd.setBounds(new Rectangle(15, 15, 76, 16));
			btnAdd.setText("Add");
		}
		return btnAdd;
	}

	/**
	 * This method initializes btnEdit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton();
			btnEdit.setBounds(new Rectangle(15, 45, 76, 16));
			btnEdit.setText("Edit");
		}
		return btnEdit;
	}

	/**
	 * This method initializes btnDelete	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton();
			btnDelete.setBounds(new Rectangle(15, 75, 76, 16));
			btnDelete.setText("Delete");
		}
		return btnDelete;
	}

	/**
	 * This method initializes mItemExit	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMItemExit() {
		if (mItemExit == null) {
			mItemExit = new JMenuItem();
			mItemExit.setText("Exit");
			mItemExit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					exit();
				}
			});
		}
		return mItemExit;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame thisClass = new MainFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
	
	private void exit() {
		if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation Dialog", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			exit(0);
		}
	}
	
	private void exit(int exitCode) {
		System.exit(exitCode);
	}
	
	private void newSession() {
		if (this.getNewSessionDialog().showDialog() == DialogResult.CONNECT) {
			connect();
		}
	}
	
	private void connect() {
		String password = this.getNewSessionDialog().getPassword();
		String host = this.getNewSessionDialog().getHost();
		String port = this.getNewSessionDialog().getPort();
		String sid = this.getNewSessionDialog().getSid();
		String dbProvider = this.getNewSessionDialog().getDbProvider();
		HibernateConnectionManager.configure(password, host, port, sid, dbProvider);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
