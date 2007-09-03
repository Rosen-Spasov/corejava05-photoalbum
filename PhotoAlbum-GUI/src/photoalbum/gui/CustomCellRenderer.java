package photoalbum.gui;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import photoalbum.entities.Category;
import photoalbum.entities.Photo;
import photoalbum.entities.User;
import photoalbum.gui.frames.MainFrame;;

public class CustomCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = -811663968624239700L;
	
	ICustomIconsSupplier iconSupplier = null;
	
	public CustomCellRenderer(ICustomIconsSupplier iconSupplier) {
		this.setIconSupplier(iconSupplier);		
	}
	
	private ICustomIconsSupplier getIconSupplier() {
		if (this.iconSupplier == null) {
			this.iconSupplier = new MainFrame();
		}
		return this.iconSupplier;
	}
	
	private void setIconSupplier(ICustomIconsSupplier iconSupplier) {
		if (iconSupplier != null) {
			this.iconSupplier = iconSupplier;
		}
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		if (value instanceof DefaultMutableTreeNode) {
        	Object node = ((DefaultMutableTreeNode) value).getUserObject();
        	if (node instanceof User) {
        		this.setIcon(this.getIconSupplier().getUserIcon());
        	} else if (node instanceof Category){
        		if (expanded) {
        			this.setIcon(this.getIconSupplier().getOpenedCategoryIcon());
        		} else {
        			this.setIcon(this.getIconSupplier().getClosedCategoryIcon());
        		}
        	} else if (node instanceof Photo){
        		this.setIcon(this.getIconSupplier().getPhotoIcon());
        	} else {
        		this.setIcon(this.getIconSupplier().getPhotoIcon());
        	}
		}

		return this;
	}

}
