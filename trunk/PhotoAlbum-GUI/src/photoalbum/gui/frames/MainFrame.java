package photoalbum.gui.frames;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import photoalbum.CreateUserException;
import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.entities.interfaces.ICategoryContainer;
import photoalbum.filesystem.FileSystemManager;
import photoalbum.gui.CustomCellRenderer;
import photoalbum.gui.ICustomIconsSupplier;
import photoalbum.gui.ImageFileFilter;
import photoalbum.gui.dialogs.NewSessionDialog;
import photoalbum.gui.dialogs.UserDialog;
import photoalbum.logging.Logger;
import photoalbum.network.NetworkConnection;

public class MainFrame extends JFrame implements ICustomIconsSupplier, TreeSelectionListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	public static enum DialogResult {CONNECT, CREATE, OK, CANCEL, YES, NO}

	private JPanel jContentPane = null;

	private JMenuBar mBarMain = null;

	private JMenu menuFile = null;

	private JMenu menuSession = null;

	private JMenuItem mItemNewSession = null;

	private JPanel panelButtons = null;

	private JButton btnExit = null;

	private JButton btnAdd = null;

	private JButton btnEdit = null;

	private JButton btnDelete = null;

	private JMenuItem mItemExit = null;
	
	private NewSessionDialog newSessionDialog = null;
	
	private UserDialog userDialog = null;

	private JScrollPane scrollPaneData = null;

	private JTree treeData = null;
	
	private DefaultMutableTreeNode rootNode = null;
	
	private CustomCellRenderer customCellRenderer = null;
	
	private Icon userIcon = null;  //  @jve:decl-index=0:
	
	private Icon openedCategoryIcon = null;
	
	private Icon closedCategoryIcon = null;  //  @jve:decl-index=0:
	
	private Icon photoIcon = null;  //  @jve:decl-index=0:

	private JButton btnRefresh = null;
	
	private JFileChooser fileChooser = null;
	
	private NetworkConnection networkConnection = null;

	private JFileChooser getFileChooser() {
		if (this.fileChooser == null) {
			this.fileChooser = new JFileChooser();
			this.fileChooser.setMultiSelectionEnabled(true);
			this.fileChooser.setFileFilter(new ImageFileFilter());
			this.fileChooser.setAcceptAllFileFilterUsed(false);
			this.fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		}
		return this.fileChooser;
	}
	
	public Icon getPhotoIcon() {
		if (this.photoIcon == null) {
			this.photoIcon = new ImageIcon("./images/photo.png");
		}
		return this.photoIcon;
	}
	
	public Icon getClosedCategoryIcon() {
		if (this.closedCategoryIcon == null) {
			this.closedCategoryIcon = new ImageIcon("./images/closed-category.gif");
		}
		return this.closedCategoryIcon;
	}
	
	public Icon getOpenedCategoryIcon() {
		if (this.openedCategoryIcon == null) {
			this.openedCategoryIcon = new ImageIcon("./images/opened-category.gif");
		}
		return this.openedCategoryIcon;
	}
	
	public Icon getUserIcon() {
		if (this.userIcon == null) {
			this.userIcon = new ImageIcon("./images/user.gif");
		}
		return this.userIcon;
	}
	
	private CustomCellRenderer getCustomCellRenderer() {
		if (this.customCellRenderer == null) {
			this.customCellRenderer = new CustomCellRenderer(this);
		}
		return this.customCellRenderer;
	}
	
	private DefaultMutableTreeNode getRootNode() {
		if (this.rootNode == null) {
			this.rootNode = new DefaultMutableTreeNode("PhotoAlbum");
		}
		return this.rootNode;
	}
	
	private UserDialog getUserDialog() {
		if (this.userDialog == null) {
			this.userDialog = new UserDialog(this);
		}
		return this.userDialog;
	}

	private NewSessionDialog getNewSessionDialog() {
		if (this.newSessionDialog == null) {
			this.newSessionDialog = new NewSessionDialog(this);
		}
		return this.newSessionDialog;
	}

	/**
	 * This method initializes btnRefresh	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRefresh() {
		if (btnRefresh == null) {
			btnRefresh = new JButton();
			btnRefresh.setBounds(new Rectangle(15, 105, 91, 16));
			btnRefresh.setText("Refresh");
			btnRefresh.addActionListener(this);
		}
		return btnRefresh;
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
		this.setSize(580, 327);
		this.setJMenuBar(getMBarMain());
		this.setContentPane(getJContentPane());
		this.getTreeData().setEnabled(false);
		this.updateButtons(false);
		this.getBtnRefresh().setEnabled(false);
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
			jContentPane.add(getPanelButtons(), null);
			jContentPane.add(getBtnExit(), null);
			jContentPane.add(getScrollPaneData(), null);
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
			mItemNewSession.addActionListener(this);
		}
		return mItemNewSession;
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
			panelButtons.setBounds(new Rectangle(450, 0, 121, 136));
			panelButtons.setEnabled(true);
			panelButtons.add(getBtnAdd(), null);
			panelButtons.add(getBtnEdit(), null);
			panelButtons.add(getBtnDelete(), null);
			panelButtons.add(getBtnRefresh(), null);
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
			btnExit.setText("Exit");
			btnExit.setBounds(new Rectangle(465, 240, 91, 16));
			btnExit.addActionListener(this);
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
			btnAdd.setBounds(new Rectangle(15, 15, 91, 16));
			btnAdd.setText("Add");
			btnAdd.addActionListener(this);
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
			btnEdit.setBounds(new Rectangle(15, 45, 91, 16));
			btnEdit.setText("Edit");
			btnEdit.addActionListener(this);
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
			btnDelete.setBounds(new Rectangle(15, 75, 91, 16));
			btnDelete.setText("Delete");
			btnDelete.addActionListener(this);
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
			mItemExit.addActionListener(this);
		}
		return mItemExit;
	}

	/**
	 * This method initializes scrollPaneData	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollPaneData() {
		if (scrollPaneData == null) {
			scrollPaneData = new JScrollPane();
			scrollPaneData.setBounds(new Rectangle(15, 15, 436, 241));
			scrollPaneData.setViewportView(getTreeData());
		}
		return scrollPaneData;
	}

	/**
	 * This method initializes treeData	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getTreeData() {
		if (treeData == null) {
			treeData = new JTree(this.getRootNode());
			treeData.setCellRenderer(this.getCustomCellRenderer());
			treeData.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			treeData.addTreeSelectionListener(this);
		}
		return treeData;
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
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));		
		String host = this.getNewSessionDialog().getHost();
		String port = this.getNewSessionDialog().getPort();
		String password = this.getNewSessionDialog().getPassword();
		
		try {
			this.networkConnection = new NetworkConnection(host, port);
			if (!password.equals("") && this.networkConnection.adminAccessGranted(password)) {
				refreshTree();
				this.getTreeData().setEnabled(true);
				this.getBtnRefresh().setEnabled(true);
				this.getMItemNewSession().setEnabled(false);				
			} else {
				JOptionPane.showMessageDialog(this, "Invalid password. Connection refused.", "DB Connection Failed", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(this, "Could not connect to server. Check your connection settings and contact your system administrator.", "DB Connection Failed", JOptionPane.ERROR_MESSAGE);
			Logger.getDefaultInstance().log(e);
		} finally {
			this.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	private void loadTree() {
		User[] users = null;
		try {
			users = this.networkConnection.getAllUsers();
			loadChildren(this.getRootNode(), users);
			this.getTreeData().expandPath(new TreePath(this.getRootNode().getPath()));
			this.reloadTree();
		} catch (IOException e) {
			Logger.getDefaultInstance().log(e);
		} catch (ClassNotFoundException e) {
			Logger.getDefaultInstance().log(e);
		}
	}
	
	private void loadChildren(DefaultMutableTreeNode parent, Object[] children) {
		if (parent == null || children == null) {
			return;
		}
		for (Object child : children) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
			if (child instanceof ICategoryContainer) {
				ICategoryContainer categoryContainer = (ICategoryContainer) child;
				Category[] categories = new Category[categoryContainer.getCategories().size()];
				categoryContainer.getCategories().toArray(categories);
				Arrays.sort(categories);
				loadChildren(childNode, categories);
			}
			if (child instanceof Category) {
				Category photoContainer = (Category) child;
				Photo[] photos = new Photo[photoContainer.getPhotos().size()];
				photoContainer.getPhotos().toArray(photos);
				Arrays.sort(photos);
				loadChildren(childNode, photos);
			}
			parent.add(childNode);
		}
	}
	
	private void add() {
		if (!this.getTreeData().isSelectionEmpty()) {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) getTreeData().getLastSelectedPathComponent();
			Object selectedObject = selectedNode.getUserObject();
			if (selectedNode == getRootNode()) {
				addUser();
			} else if (selectedObject instanceof User || selectedObject instanceof Category) {
				addFileStructure(selectedObject);
			}
		}
	}
	
	private void addUser() {
		this.getUserDialog().resetDialog();
		this.getUserDialog().setEditMode(false);
		if (this.getUserDialog().showDialog() == DialogResult.OK) {
			String username = this.getUserDialog().getUsername();
			String password = this.getUserDialog().getPassword();
			String firstName = this.getUserDialog().getFirstName();
			String lastName = this.getUserDialog().getLastName();
			
			try {
				this.networkConnection.addUser(username, password, firstName, lastName);
				this.refreshTree();
			} catch (CreateUserException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "User Creation Failed", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void addFileStructure(Object parent) {
		if (this.getFileChooser().showDialog(this, "Add") == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = this.getFileChooser().getSelectedFiles();
			
			try {
				for (File selectedFile : selectedFiles) {
					if (selectedFile.isDirectory() && parent instanceof ICategoryContainer) {
						Category category = addCategory((ICategoryContainer) parent, selectedFile);
						((ICategoryContainer) parent).add(category);
					} else if (selectedFile.isFile() && parent instanceof Category) {
						Photo photo = addPhoto((Category) parent, selectedFile);
						((Category) parent).add(photo);
					}
				}
			} catch (IOException e) {
				Logger.getDefaultInstance().log(e);
			} catch (ClassNotFoundException e) {
				Logger.getDefaultInstance().log(e);
			}
			
			this.refreshTree();
		}
	}
	
	public Category addCategory(ICategoryContainer parent, File directory) throws IOException, ClassNotFoundException {
		if (directory == null && !directory.isDirectory()) {
			return null;
		}
		
		String catName = directory.getName();
		Category category = this.networkConnection.addCategory(parent, catName);
		if (category != null) {
			File[] children = directory.listFiles();
			for (File child : children) {
				if (child.isDirectory()) {
					addCategory(category, child);
				} else if (child.isFile() && FileSystemManager.isValidImage(child)) {
					addPhoto(category, child);
				}
			}
			
			parent.add(category);
		}
		
		return category;
	}
	
	public Photo addPhoto(Category parent, File imageFile) throws IOException, ClassNotFoundException {
		if (imageFile == null && !imageFile.isFile()) {
			return null;
		}
		
		Photo photo = this.networkConnection.addPhoto(parent, imageFile);
		parent.add(photo);
		
		return photo;
	}
	
	private void edit() {
		if (!this.getTreeData().isSelectionEmpty()) {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) this.getTreeData().getLastSelectedPathComponent();
			if (selectedNode != null && selectedNode != getRootNode()) {
				Object selectedObject = selectedNode.getUserObject();
				if (selectedObject instanceof User) {
					editUser( (User) selectedObject );
				}
			}
		}
	}
	
	private void editUser(User user) {
		String username = user.getUsername();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		this.getUserDialog().resetDialog(username, "", firstName, lastName);
		this.getUserDialog().setEditMode(true);
		if (this.getUserDialog().showDialog() == DialogResult.OK) {
			try {
				user.setUsername(this.getUserDialog().getUsername());
				user.setFirstName(this.getUserDialog().getFirstName());
				user.setLastName(this.getUserDialog().getLastName());
				String password = this.getUserDialog().getPassword();
				if (!password.equals("")) {
					user.setPassword(this.getUserDialog().getPassword());
				}
				this.networkConnection.updateUser(user);
			} catch (IOException e) {
				Logger.getDefaultInstance().log(e);
			} catch (ClassNotFoundException e) {
				Logger.getDefaultInstance().log(e);
			}
			this.reloadTree();
		}
	}
	
	private void delete() {
		if (!this.getTreeData().isSelectionEmpty()) {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) this.getTreeData().getLastSelectedPathComponent();
			if (selectedNode != null && selectedNode != getRootNode()) {
				try {
					this.networkConnection.delete(selectedNode.getUserObject());
					DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
					parentNode.remove(selectedNode);
					reloadTree();
				} catch (Throwable e) {
					Logger.getDefaultInstance().log(e);
					JOptionPane.showMessageDialog(this, "Cannot delete selected object.", "Delete Object Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void valueChanged(TreeSelectionEvent e) {
		if (!this.getTreeData().isSelectionEmpty()) {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) this.getTreeData().getLastSelectedPathComponent();
			if (selectedNode == getRootNode()) {
				this.updateButtons(true, false, false);
			} else if (selectedNode.getUserObject() instanceof User) {
					this.updateButtons(true, true, true);
			} else if (selectedNode.getUserObject() instanceof Category) {
					this.updateButtons(true, true, false);
			} else if (selectedNode.getUserObject() instanceof Photo) {
					this.updateButtons(false, true, false);
			} else {
				this.updateButtons(true);
			}
		} else {
			this.updateButtons(false);
		}
	}
	
	private void refreshTree() {
		getRootNode().removeAllChildren();
		loadTree();
	}
	
	private void reloadTree() {
		((DefaultTreeModel) getTreeData().getModel()).reload();
	}
	
	private void updateButtons(boolean enableAll) {
		this.updateButtons(enableAll, enableAll, enableAll);
	}
	
	private void updateButtons(boolean btnAddEnabled, boolean btnDeleteEnabled, boolean btnEditEnabled) {
		this.getBtnAdd().setEnabled(btnAddEnabled);
		this.getBtnDelete().setEnabled(btnDeleteEnabled);
		this.getBtnEdit().setEnabled(btnEditEnabled);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.getBtnAdd()) {
			add();
		} else if (e.getSource() == this.getBtnEdit()) {
			edit();
		} else if (e.getSource() == this.getBtnDelete()) {
			delete();
		} else if (e.getSource() == this.getBtnRefresh()) {
//			refresh();
			refreshTree();
		} else if (e.getSource() == this.getBtnExit() || e.getSource() == this.getMItemExit()) {
			exit();
		} else if (e.getSource() == this.getMItemNewSession()) {
			newSession();
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
