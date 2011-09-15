package edu.ecpi.IS510.GradeBook;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;
import java.util.Hashtable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "courses")
public class Course implements Serializable{
	//TODO: Revisit the cycle between students and courses (potential memory leaks)
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	Vector<Student> students;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	Vector<Assignment> assignments;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
	Hashtable<Long,Float> finalGrades;
	@DatabaseField(id = true)
	String courseNumber;
	@DatabaseField
	String title;
	private static final long serialVersionUID = 1L;
	
	public Course(String courseNumber, String title){
		students = new Vector<Student>();
		assignments = new Vector<Assignment>();
		finalGrades = new Hashtable<Long,Float>();
		this.courseNumber = courseNumber;
		this.title = title;		
	}
	
	public Course(){
		this("", "");
	}
	
	public void addStudent(Student student){
		if(!students.contains(student)){
			students.addElement(student);
		}
	}
	
	public void addAssignment(Assignment assignment){
		if(assignment != null){
			assignments.add(assignment);
		}
	}
	
	public void setFinalGrade(Student student, float grade){
		if(student != null){
			finalGrades.put(student.getStudentID(), grade);
		}
	}

	public String getCourseNumber(){
		return courseNumber;
	}
	
	public void setCourseNumber(String courseNumber){
		this.courseNumber = courseNumber;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
	     //always perform the default de-serialization first
	     aInputStream.defaultReadObject();
	}

	
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
	      //perform the default serialization for all non-transient, non-static fields
	      aOutputStream.defaultWriteObject();
   }
}
