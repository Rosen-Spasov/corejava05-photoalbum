package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import photoalbum.CreateCategoryException;
import photoalbum.PhotoAlbumManipulator;
import photoalbum.entities.Category;
import photoalbum.entities.User;

/**
 * Servlet implementation class for Servlet: AddNewCategoryServlet
 * 
 */
public class AddNewCategoryServlet extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PhotoAlbumManipulator edit = new PhotoAlbumManipulator();
		String category = (String) session.getAttribute("currentCategory");
		User user = (User) session.getAttribute("user");
		String newName = (String) request.getParameter("newCategoryName");
		String[] errorsAdd = new String[5];
		Set<Category> allCategory = user.getCategories();
		if (validate(newName, user, errorsAdd)) {
			for (Category cat : allCategory) {
				if (cat.getCatName().equalsIgnoreCase(category)) {
					Set<Category> allCategories = cat.getCategories();
					for (Category allCat: allCategories){
						if (!allCat.getCatName().equalsIgnoreCase(cat.getCatName())){
							System.out.println("v cat:" + category + " e dobawena "+ newName);
							System.out.println("towa e path-" + cat.getPath());
							try {
								System.out.println(cat.getUser());
								edit.addCategory(cat, newName);
								
							} catch (CreateCategoryException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							errorsAdd[0]= "Kategoriq s towa ime sy6testwuwa";
						}
					}
					
				}
			}
			System.out.println("created");
			request.getRequestDispatcher("ShowUser.jsp").forward(request,
					response);
		} else {
			System.out.println(errorsAdd.length);
			session.setAttribute("errors", errorsAdd);
			request.getRequestDispatcher("addCategory.jsp").forward(request,
					response);

		}
	}

	private boolean validate(String newName, User user,
			String[] errors) {

		System.out.println(newName);
		boolean result = true;
		if (!(newName.length() > 2)) {
		errors[1] = "Enter name";
			result = false;
		}
		if (newName.contains("[/.,!?]")) {
			errors[2] = "ne moje da sydyrja /.,!?";
			result = false;
		}

		return result;
	}

}