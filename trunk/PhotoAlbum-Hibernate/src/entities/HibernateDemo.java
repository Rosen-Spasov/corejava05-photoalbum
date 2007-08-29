package entities;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

@SuppressWarnings("unchecked")
public class HibernateDemo {

	public static void main(String[] args) {
		// Establish Hibernate session and start a transaction
		Session session = HibernateUtils.startSession();
		System.out.println("Started new Hibernate session and transaction.");
		System.out.println();
		
		try {
			// Display all users
			System.out.println("List of users:");
			List<User> allUsers = getAllUsers(session);
			for (User us : allUsers) {
				System.out.printf("Id=%d, FirstName=%s, LastName=%s, " +
					"username=%s \n", 
					us.getUserId(), us.getFirstName(), us.getLastName(),
					us.getUsername());
			}
			System.out.println();
			
			

			
			
			
			
//			// Flush all pending changes. This posts them to the database
//			session.flush();
//			System.out.println("Pending changes posted to the database.");
//			System.out.println();
//			
//			// Refresh the new department from the database
//			// This updates its list of courses
//			session.refresh(newDept);
//			System.out.printf("Courses in department %s = %s\n",
//				newDept.getName(), newDept.getCourses().size());
//			System.out.println();
//			
//			// Assign the new professor to the new department
//			newProf.getDepartments().add(newDept);
//			session.update(newProf);
//			System.out.printf("Professor %d assigned to department %d\n",
//				newProf.getPersonId(), newDept.getDeptId());
//			System.out.println();
//	
//			// Flush all pending changes. This posts them to the database
//			session.flush();
//			System.out.println("Pending changes posted to the database.");
//			System.out.println();
//			
//			// Refresh the new professor from the database
//			// This updates its list of courses
//			session.refresh(newProf);
//			System.out.printf("Courses by professor %s %s = %s\n",
//				newProf.getFirstName(), newProf.getLastName(), 
//				newProf.getCourses().size());
//			System.out.printf("Professor %s %s is in %s departments\n",
//				newProf.getFirstName(), newProf.getLastName(), 
//				newProf.getDepartments().size());
//			System.out.println();
//			
//			// Add one more new course
//			Course newTestCourse = 
//				addNewCourse(session, "New Test Course", newDept, newProf);
//			System.out.println("Added new course. CourseId=" + 
//				newTestCourse.getCourseId());
//			System.out.println();
//			
//			// Update the course information in the database
//			newTestCourse.setName("Updated%_\\New\\ Test Course");
//			session.update(newTestCourse);
//			System.out.println("Course name changed to " + newTestCourse.getName());
//			System.out.println();
//			
//			// Display all courses by given course prefix
//			// This will cause the updates of the courses to be posted to the database
//			System.out.println("List of all courses by given prefix:");
//			List<Course> filteredCourses = getCoursesByNamePrefix(session, "Updated%_\\");
//			for (Course course : filteredCourses) {
//				System.out.printf(
//					"CourseId=%d, CourseName=%s, DeptName=%s ProfName=%s %s " +
//					"StudentsCount=%s\n", course.getCourseId(), course.getName(),
//					course.getDepartment().getName(), course.getProfessor().getFirstName(),
//					course.getProfessor().getLastName(), course.getStudents().size());
//			}
//			System.out.println();
//			
//			// Display all courses by given criteria
//			System.out.println("List of all courses by given criteria:");
//			Criteria coursesCriteria =
//				session.createCriteria(Course.class)
//				.add( Expression.in("Name", new String[] { "Java", "C#", "C++" } ) )
//				.createAlias( "Professor", "prof" )
//				.add( Expression.eq("prof.LastName", "Sali" ) )
//				.addOrder( Order.asc("Name") );
//			List<Course> coursesByCriteria = coursesCriteria.list();
//
//			for (Course course : coursesByCriteria) {
//				System.out.printf(
//					"CourseId=%d, CourseName=%s, DeptName=%s ProfName=%s %s " +
//					"StudentsCount=%s\n", course.getCourseId(), course.getName(),
//					course.getDepartment().getName(), course.getProfessor().getFirstName(),
//					course.getProfessor().getLastName(), course.getStudents().size());
//			}
//			System.out.println();
//			
//			// Add one more new course for deleting
//			Course newCourseForDeletion = 
//				addNewCourse(session, "Test Course for Deletion", newDept, newProf);
//			System.out.println("Added new course. CourseId=" + 
//				newCourseForDeletion.getCourseId());
//			System.out.println();
//			
//			// Delete the course that we just added
//			session.delete(newCourseForDeletion);
//			System.out.printf("Deleted course %d.\n", newCourseForDeletion.getCourseId());
//			System.out.println();
//			
//			// Display details about course #5
//			Course course5 = getCourseByPrimaryKey(session, 5);
//			System.out.printf("Course %d:\n", course5.getCourseId());
//			System.out.printf("  Name: %s\n", course5.getName());
//			System.out.printf("  Professor: %s %s\n",
//				course5.getProfessor().getFirstName(),
//				course5.getProfessor().getLastName());
//			System.out.println();
//			
//			// Modify the name of course #5
//			course5.setName("Course " + new Random().nextLong());
//			session.update(course5);
//			System.out.println("Course #5 is now named: " + course5.getName());
//			System.out.println();
//			
//			// Display all persons (students and professors)
//			System.out.println("List of all persons (both students and professors):");
//			List<Person> allPersons = getAllPersons(session);
//			for (Person p : allPersons) {
//				System.out.printf("PersonId=%s ", p.getPersonId());
//				System.out.printf("Name=%s %s ", p.getFirstName(), p.getLastName());
//				if (p instanceof Student) {
//					Student student = (Student) p;				
//					System.out.println("(type)=Student FacultyNumber=" +
//						student.getFacultyNumber());
//				} else if (p instanceof Professor) {
//					Professor prof = (Professor) p;
//					System.out.println("(type)=Professor Title=" + prof.getTitle());
//				} else {
//					System.out.println("Unknown person!");
//				}
//			}
//			System.out.println();
			
			// Everything succesfully passed. Now commit the Hibernate transaction
			// This will cause all pending chages to be posted
			// and the databse transaction to be commited
			HibernateUtils.commit(session);
			System.out.println("Commited the Hibernate transaction.");
			System.out.println();
			
		} catch (RuntimeException ex) {
			HibernateUtils.rollback(session);
			System.out.println("The Hibernate transaction aborted due to exception.");
			System.out.println();
			throw ex;
		}
	}



	private static List<User> getAllUsers(Session session) {
		Query allUsersQuery = session.createQuery("from User");
		List<User> allUsers = allUsersQuery.list();
		return allUsers;
	}


	

}
