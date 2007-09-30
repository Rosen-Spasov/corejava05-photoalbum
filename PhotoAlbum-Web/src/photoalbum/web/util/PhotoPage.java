package photoalbum.web.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import photoalbum.entities.Photo;
import photoalbum.filesystem.FileSystemManager;

public class PhotoPage implements Serializable {
	
	private static final long serialVersionUID = -95958759730108509L;

	public static final int PHOTOS_PER_PAGE = 16;
	
	public static PhotoPage[] getPages(Collection<Photo> photos) {
		if (photos == null) {
			return null;
		}
		PhotoPage[] result = null;
		ArrayList<Photo> photosArray = new ArrayList<Photo>(photos);
		
		int numberOfPages = photosArray.size() / PHOTOS_PER_PAGE;
		if (photosArray.size() % PHOTOS_PER_PAGE != 0) {
			numberOfPages++;
		}
		
		result = new PhotoPage[numberOfPages];
		for (int pageIndex = 0; pageIndex < numberOfPages; pageIndex++) {
			int fromIndex = pageIndex * PHOTOS_PER_PAGE;
			int toIndex = fromIndex + PHOTOS_PER_PAGE;
			if (toIndex > photosArray.size()) {
				toIndex = photosArray.size();
			}
			result[pageIndex] = new PhotoPage(photosArray.subList(fromIndex, toIndex));
		}
		
		return result;
	}
	
	private Collection<Photo> photos;
	
	private Hashtable<String, String> absolutePaths;
	
	public PhotoPage() {
	}
	
	public PhotoPage(Collection<Photo> photos) {
		setPhotos(photos);
	}
	
	public Hashtable<String, String> getAbsolutePaths() {
		if (absolutePaths == null) {
			absolutePaths = new Hashtable<String, String>(getPhotos().size());
			Hashtable<String, String> absolutePaths = extractAbsolutePaths(getPhotos());
			setAbsolutePaths(absolutePaths);
		}
		return absolutePaths;
	}
	
	public Collection<Photo> getPhotos() {
		if (photos == null) {
			photos = new ArrayList<Photo>(PHOTOS_PER_PAGE);
		}
		return photos;
	}
	
	private void setAbsolutePaths(Hashtable<String, String> absolutePaths) {
		this.getAbsolutePaths().clear();
		this.getAbsolutePaths().putAll(absolutePaths);
	}
	
	public void setPhotos(Collection<Photo> photos) {
		if (photos.size() <= PHOTOS_PER_PAGE) {
			this.getPhotos().clear();
			this.getPhotos().addAll(photos);
			Hashtable<String, String> absolutePaths = extractAbsolutePaths(photos);
			this.setAbsolutePaths(absolutePaths);
		} else {
			throw new IllegalArgumentException("Collection is too large. Size: [" + photos.size() + "]. " +
				"The maximum number of photos a page can contain is [" + PHOTOS_PER_PAGE + "].");
		}
	}
	
	private Hashtable<String, String> extractAbsolutePaths(Collection<Photo> photos) {
		Hashtable<String, String> result = new Hashtable<String, String>(photos.size());
		
		for (Photo photo : photos) {
			String absolutePath = FileSystemManager.getAbsolutePath(photo);
			result.put(photo.getPath(), absolutePath);
		}
		
		return result;
	}

}
