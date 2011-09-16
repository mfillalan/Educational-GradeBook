/**
 * The Main class is the entry point for the GradeBook program
 *  
 * @author Greg Patrick.
 * @version 0.5
 */

package edu.ecpi.IS510.GradeBook;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import com.j256.ormlite.jdbc.*;
import com.j256.ormlite.support.*;

public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		//TODO Auto-generated method stub
		
		String databaseUrl = "jdbc:h2:~/GradeBook";
		String username = "sa";
		String password = "";
	    ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl, username, password);
		DatabaseController dbController = new DatabaseController(connectionSource);
		
		List<Student> students;
		List<Assignment> assignments;
		List<Course> courses;
		List<Employee> employees;
		List<Person> people;
		List<Teacher> teachers;
		
        students = dbController.studentDao.queryForAll();        
        assignments = dbController.assignmentDao.queryForAll();
        courses = dbController.courseDao.queryForAll();
        employees = dbController.employeeDao.queryForAll();
        people = dbController.personDao.queryForAll();
        teachers = dbController.teacherDao.queryForAll();
		
		printList(students);
		printList(assignments);
		printList(courses);
		printList(employees);
		printList(people);
		printList(teachers);
	}
	
	private static void printList(List list){
		Iterator<Object> iterator = list.iterator();
		while(iterator.hasNext()){
			Object s = iterator.next();
			System.out.println(s.toString());
		}
	}
}
