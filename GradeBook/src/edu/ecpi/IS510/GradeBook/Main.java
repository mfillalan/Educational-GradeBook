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
		
		//Create our database URL.
        String databaseUrl = "jdbc:h2:~/GradeBook";
        
        //Create a connection source for our database.
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl, "sa", "");

        //Create DAO's
        Dao<Student, String> studentDao = DaoManager.createDao(connectionSource, Student.class);
        Dao<Assignment, String> assignmentDao = DaoManager.createDao(connectionSource, Assignment.class);
        Dao<Course, String> courseDao = DaoManager.createDao(connectionSource, Course.class);
        Dao<Employee, String> employeeDao = DaoManager.createDao(connectionSource, Employee.class);
        Dao<Person, String> personDao = DaoManager.createDao(connectionSource, Person.class);
        Dao<Submission, String> submissionDao = DaoManager.createDao(connectionSource, Submission.class);
        Dao<Teacher, String> teacherDao = DaoManager.createDao(connectionSource, Teacher.class);
        
        //Create tables if they don't exist.
        TableUtils.createTableIfNotExists(connectionSource, Student.class);
        TableUtils.createTableIfNotExists(connectionSource, Assignment.class);
        TableUtils.createTableIfNotExists(connectionSource, Course.class);
        TableUtils.createTableIfNotExists(connectionSource, Employee.class);
        TableUtils.createTableIfNotExists(connectionSource, Person.class);
        TableUtils.createTableIfNotExists(connectionSource, Submission.class);
        TableUtils.createTableIfNotExists(connectionSource, Teacher.class);
        
        Student student1 = new Student();
        student1.setFirstName("Brian");
        student1.setLastName("Griffin");
        studentDao.createOrUpdate(student1);
		
		Course course = new Course("IS510", "Object-Oriented Programming");
		Assignment a1 = new Assignment("Homework 1", "Do some stuff", new Date(), 0.3f);
		Submission s1 = new Submission(12345, new Date(), 20);
		Submission s2 = new Submission(54321, new Date(), 80);

		a1.addSubmission(s1);
		a1.addSubmission(s2);
		course.addAssignment(a1);
		courseDao.createOrUpdate(course);
		assignmentDao.createOrUpdate(a1);
		submissionDao.createOrUpdate(s1);
		submissionDao.createOrUpdate(s2);

		System.out.println("Assignment = " + a1.getDueDate() + ". Weight = " + a1.getWeight() + ". Title = " + a1.getTitle() + ". Description = " + a1.getDescription());
		
		Iterator<Submission> submissions = a1.getSubmissions().iterator();
		while(submissions.hasNext()){
			Submission s = submissions.next();
			System.out.println("StudentID = " + s.getStudentID() + ". Date Submitted = " + s.getDateSubmitted() + ". Grade = " + s.getGrade() + ".");
		}
	}
}
