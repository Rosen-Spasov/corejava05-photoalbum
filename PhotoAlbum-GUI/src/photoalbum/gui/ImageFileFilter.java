package photoalbum.gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ImageFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String fileName = f.getName();
		if (fileName.endsWith(".jpg") || fileName.endsWith(".gif") || fileName.endsWith(".png")) {
			return true;
			
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Images files (*.jpg, *.gif, *.png)";
	}

}
