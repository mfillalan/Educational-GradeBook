/**
 * The Main class is the entry point for the GradeBook program
 *  
 * @author Greg Patrick.
 * @version 0.5
 */

package edu.ecpi.IS510.GradeBook;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.j256.ormlite.dao.*;
import com.j256.ormlite.jdbc.*;
import com.j256.ormlite.support.*;
import com.j256.ormlite.table.*;

public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		//TODO Auto-generated method stub	
		
		List<Student> students;
		List<Assignment> assignments;
		List<Course> courses;
		List<Employee> employees;
		List<Person> people;
		List<Teacher> teachers;
		
		//Create our database URL.
        String databaseUrl = "jdbc:h2:~/GradeBook";
        
        //Create a connection source for our database.
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl, "sa", "");

        //Create DAO's
        Dao<Student, String> studentDao = DaoManager.createDao(connectionSource, Student.class);
        students = studentDao.queryForAll();
        Dao<Assignment, String> assignmentDao = DaoManager.createDao(connectionSource, Assignment.class);
        assignments = assignmentDao.queryForAll();
        Dao<Course, String> courseDao = DaoManager.createDao(connectionSource, Course.class);
        courses = courseDao.queryForAll();
        Dao<Employee, String> employeeDao = DaoManager.createDao(connectionSource, Employee.class);
        employees = employeeDao.queryForAll();
        Dao<Person, String> personDao = DaoManager.createDao(connectionSource, Person.class);
        people = personDao.queryForAll();        
        Dao<Teacher, String> teacherDao = DaoManager.createDao(connectionSource, Teacher.class);
        teachers = teacherDao.queryForAll();
        
        //Create tables if they don't exist.
        TableUtils.createTableIfNotExists(connectionSource, Student.class);
        TableUtils.createTableIfNotExists(connectionSource, Assignment.class);
        TableUtils.createTableIfNotExists(connectionSource, Course.class);
        TableUtils.createTableIfNotExists(connectionSource, Employee.class);
        TableUtils.createTableIfNotExists(connectionSource, Person.class);
        TableUtils.createTableIfNotExists(connectionSource, Submission.class);
        TableUtils.createTableIfNotExists(connectionSource, Teacher.class); 

		//System.out.println("Assignment = " + a1.getDueDate() + ". Weight = " + a1.getWeight() + ". Title = " + a1.getTitle() + ". Description = " + a1.getDescription());
		
		Iterator<Student> studentIterator = students.iterator();
		while(studentIterator.hasNext()){
			Student s = studentIterator.next();
			System.out.println(s.toString());
		}
		
		Iterator<Assignment> assignmentIterator = assignments.iterator();
		while(assignmentIterator.hasNext()){
			Assignment s = assignmentIterator.next();
			System.out.println(s.toString());
		}
	}
}
