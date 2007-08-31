package photoalbum.gui;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import photoalbum.gui.frames.MainFrame;
import entities.Category;
import entities.Photo;
import entities.User;

public class CustomCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = -811663968624239700L;
	
	MainFrame frame = null;
	
	public CustomCellRenderer(MainFrame frame) {
		this.setFrame(frame);		
	}
	
	private MainFrame getFrame() {
		if (this.frame == null) {
			this.frame = new MainFrame();
			this.frame.setTitle("Manage Photo Album");
		}
		return this.frame;
	}
	
	private void setFrame(MainFrame frame) {
		if (frame != null) {
			this.frame = frame;
		}
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		if (value instanceof DefaultMutableTreeNode) {
        	Object node = ((DefaultMutableTreeNode) value).getUserObject();
        	if (node instanceof User) {
        		this.setIcon(this.getFrame().getUserIcon());
        	} else if (node instanceof Category ){
        		if (expanded) {
        			this.setIcon(this.getFrame().getOpenedCategoryIcon());
        		} else {
        			this.setIcon(this.getFrame().getClosedCategoryIcon());
        		}
        	} else if (node instanceof Photo){
        		this.setIcon(this.getFrame().getPhotoIcon());
        	} else {
        		this.setIcon(this.getFrame().getPhotoIcon());
        	}
		}

		return this;
	}

}
