package photoalbum.entities.interfaces;

import java.util.Set;

import photoalbum.entities.Category;

public interface ICategoryContainer {
	
	public Set<Category> getCategories();
	
	public void remove(Category category);

}
