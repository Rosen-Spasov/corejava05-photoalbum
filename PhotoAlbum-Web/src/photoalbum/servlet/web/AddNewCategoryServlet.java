package photoalbum.servlet.web;

import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

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
		System.out.println(category);
		if (validate(newName, user, errorsAdd)) {
			if (category.equalsIgnoreCase("allPictures")){
				try {
					System.out.println("Dobawqme q w glawnata:");
					edit.addCategory(user, newName);
					request.getRequestDispatcher("ShowAllPictuers?param=allPictures").forward(request,response);
				} catch (CreateCategoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
			for (Category cat : allCategory) {
				if (cat.getCatName().equalsIgnoreCase(category)) {
					System.out.println("vleze");
					if (categoryNameExist(cat,newName)){
							System.out.println("v cat:" + category + " e dobawena "+ newName);
							System.out.println("towa e path-" + cat.getPath());
							try {
								System.out.println(cat.getUser());
								edit.addCategory(cat.getParent(), newName);
								session.setAttribute("user", user);
								System.out.println("created");
								request.getRequestDispatcher("ShowAllPictuers?param=allPictures").forward(request,response);
							} catch (CreateCategoryException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}}
						else{
							errorsAdd[0]= "Kategoriq s towa ime sy6testwuwa";
							session.setAttribute("errorsAdd", errorsAdd);
							request.getRequestDispatcher("addCategory.jsp").forward(request,response);
						}
					}
			}
		}
			
			
		} else {
			System.out.println(errorsAdd.length);
			session.setAttribute("errorsAdd", errorsAdd);
			request.getRequestDispatcher("addCategory.jsp").forward(request, response);

		}
	}

	private boolean categoryNameExist(Category cat, String newName) {
		boolean check = true;
		
		Category parent = cat.getParentCategory();
		if (parent!= null){
		Set<Category> allCategories = parent.getCategories();
		for (Category allCat: allCategories){
			System.out.println(cat.getCatName());
			System.out.println("tezi sa categoriite ->"+allCat.getCatName());
			if (allCat.getCatName().equalsIgnoreCase(newName)){
				System.out.println("false");
				check = false;
			}
		}
		}
		return check;
	}

	private boolean validate(String newName, User user,
			String[] errors) {

//		System.out.println(newName);
		boolean result = true;
		if (!(newName.length() > 2)) {
		errors[1] = "Enter name";
			result = false;
		}
	
		
		if (newName.matches("[\\\\/.*,!?\"<>|]+")) {
			errors[2] = "ne moje da sydyrja \\/.*,!?\"<>|";
			result = false;
		}

		return result;
	}

}