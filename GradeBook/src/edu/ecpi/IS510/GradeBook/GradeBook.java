package edu.ecpi.IS510.GradeBook;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class GradeBook {
	String databaseUrl = "jdbc:h2:~/GradeBook";
	String username = "sa";
	String password = "";	
    protected ConnectionSource connectionSource;
	protected static DatabaseController dbController;
	
	protected static List<Student> students;
	protected static List<Assignment> assignments;
	protected static List<Course> courses;
	protected static List<Employee> employees;
	protected static List<Person> people;
	protected static List<Teacher> teachers;	
	
	public GradeBook(){
	}
	
	
	public void run() throws SQLException{
		connectionSource = new JdbcConnectionSource(databaseUrl, username, password);
		dbController = new DatabaseController(connectionSource);
		
        students = dbController.studentDao.queryForAll();   
        assignments = dbController.assignmentDao.queryForAll();
        courses = dbController.courseDao.queryForAll();
        employees = dbController.employeeDao.queryForAll();
        people = dbController.personDao.queryForAll();
        teachers = dbController.teacherDao.queryForAll();
        
        System.out.println(students);
        
        try{
			ManageGradeBook window = new ManageGradeBook();
			window.frmIsGradebookApplication.setVisible(true);
		} 
        catch (Exception e) {
			e.printStackTrace();
		}        
	}
	
	private static void printList(List<?> list){
		Iterator<?> iterator = list.iterator();
		while(iterator.hasNext()){
			Object s = iterator.next();
			System.out.println(s.toString());
		}
	}
	
	protected static void updateStudents() throws SQLException
	{
		students = dbController.studentDao.queryForAll();   
	}
}
