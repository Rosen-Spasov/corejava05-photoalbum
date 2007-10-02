package photoalbum.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import photoalbum.CreateCategoryException;
import photoalbum.entities.Category;
import photoalbum.entities.interfaces.ICategoryContainer;


 public class CategoryServlet extends photoalbum.web.servlets.BaseServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 2108794434589014900L;
	
	public static final String PARAM_ACTION = "action";
	
	public static final String PARAM_PARENT_TYPE = "parentType";
	
	public static final String PARAM_PARENT_ID = "parentId";
	
	public static final String PARAM_CATEGORY_ID = "categoryId";
	
	public static final String PARAM_CAT_NAME = "catName";
	
	public static enum ParentType {USER, CATEGORY}
	
	public static enum Action {ADD, DELETE, RENAME}

	public CategoryServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		try {
			String action = request.getParameter(PARAM_ACTION);
			switch (Action.valueOf(action)) {
			case ADD:
				try {
					int parentId = Integer.parseInt(request.getParameter(PARAM_PARENT_ID));
					String parentType = request.getParameter(PARAM_PARENT_TYPE);
					String catName = request.getParameter(PARAM_CAT_NAME);
					addCategory(parentId, parentType, catName);
				} catch (NumberFormatException e) {
					getLogger().log(e);
				}
				break;
			case DELETE:
				try {
					int categoryId = Integer.parseInt(request.getParameter(PARAM_CATEGORY_ID));
					deleteCategory(categoryId);
				} catch (NumberFormatException e) {
					getLogger().log(e);
				}
				break;
			case RENAME:
				try {
					int categoryId = Integer.parseInt(request.getParameter(PARAM_CATEGORY_ID));
					String catName = request.getParameter(PARAM_CAT_NAME);
					renameCategory(categoryId, catName);
				} catch (NumberFormatException e) {
					getLogger().log(e);
				}
				break;
			default:
				getLogger().log("[" + action + "] is not a valid [action] parameter. It must be equal to ADD, DELETE or RENAME.");
			}
		} finally {
			response.sendRedirect("./user?action=REFRESH");
		}
	}

	private void addCategory(int parentId, String parentType, String catName) {
		ICategoryContainer parent = null;
		
		switch (ParentType.valueOf(parentType)) {
		case USER:
			parent = getPam().getUserById(parentId);
			break;
		case CATEGORY:
			parent = getPam().getCategoryById(parentId);
			break;
		default:
			getLogger().log("[" + parentType + "] is not a valid argument for [addCategory(int parentId, String parentType, String catName)]. Parent type must be equal to USER or CATEGORY.");
			throw new IllegalArgumentException("[" + parentType + "] is not a valid argument for [addCategory(int parentId, String parentType, String catName)]. Parent type must be equal to USER or CATEGORY.");
		}
		
		if (parent != null) {
			try {
				getPam().addCategory(parent, catName);
			} catch (CreateCategoryException e) {
				getLogger().log(e);
			}
		}
	}
	
	private void deleteCategory(int categoryId) {
		Category category = getPam().getCategoryById(categoryId);
		getPam().deleteCategory(category);
	}
	
	private void renameCategory(int categoryId, String catName) {
		Category category = getPam().getCategoryById(categoryId);
		getPam().renameCategory(category, catName);
	}
}