package edu.ecpi.IS510.GradeBook;

import java.sql.SQLException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseController {
	
    protected ConnectionSource connectionSource;    
    public Dao<Student, String> studentDao;
    public Dao<Assignment, String> assignmentDao;
    public Dao<Course, String> courseDao;
    public Dao<Employee, String> employeeDao;
    public Dao<Person, String> personDao;
    public Dao<Teacher, String> teacherDao;
    
    public DatabaseController(ConnectionSource connectionSource) throws SQLException{
    	this.connectionSource = new JdbcConnectionSource();
    	this.connectionSource = connectionSource;
    	
    	createDAOS();
    	createTables(); 
    }
    
    protected void createTables() throws SQLException {
    	//Create tables if they don't exist.
        TableUtils.createTableIfNotExists(connectionSource, Student.class);
        TableUtils.createTableIfNotExists(connectionSource, Assignment.class);
        TableUtils.createTableIfNotExists(connectionSource, Course.class);
        TableUtils.createTableIfNotExists(connectionSource, Employee.class);
        TableUtils.createTableIfNotExists(connectionSource, Person.class);
        TableUtils.createTableIfNotExists(connectionSource, Submission.class);
        TableUtils.createTableIfNotExists(connectionSource, Teacher.class); 		
	}

	protected void createDAOS() throws SQLException{
    	//Create DAO's.
        studentDao = DaoManager.createDao(connectionSource, Student.class);        
        assignmentDao = DaoManager.createDao(connectionSource, Assignment.class);        
        courseDao = DaoManager.createDao(connectionSource, Course.class);
        employeeDao = DaoManager.createDao(connectionSource, Employee.class);
        personDao = DaoManager.createDao(connectionSource, Person.class);
        teacherDao = DaoManager.createDao(connectionSource, Teacher.class);
    }
}
