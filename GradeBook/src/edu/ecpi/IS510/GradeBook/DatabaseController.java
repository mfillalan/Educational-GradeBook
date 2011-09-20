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
    	//TableUtils.dropTable(connectionSource, Student.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Student.class);
        //TableUtils.dropTable(connectionSource, Assignment.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Assignment.class);
        //TableUtils.dropTable(connectionSource, Course.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Course.class);
        //TableUtils.dropTable(connectionSource, Employee.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Employee.class);
        //TableUtils.dropTable(connectionSource, Person.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Person.class);
        //TableUtils.dropTable(connectionSource, Submission.class, true);
        TableUtils.createTableIfNotExists(connectionSource, Submission.class);
        //TableUtils.dropTable(connectionSource, Teacher.class, true);
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
