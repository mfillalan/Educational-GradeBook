package edu.ecpi.IS510.GradeBook;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "submissions")
public class Submission implements Serializable{
	private static final long serialVersionUID = -368784444373150198L;
	@DatabaseField(generatedId = true)
	long submissionID;
	@DatabaseField
	long studentID;
	@DatabaseField
	float grade;
	@DatabaseField
	Date dateSubmitted;
	
	public Submission(long studentID, Date dateSubmitted, float grade){
		this.studentID = studentID;
		this.grade = grade;
		this.dateSubmitted = dateSubmitted;
	}
	
	public Submission(){
		this(0, null, 0);
	}
	
	public long getStudentID(){
		return studentID;
	}
	
	public void setStudentID(long studentID){
		this.studentID = studentID;
	}
	
	public float getGrade(){
		return grade;
	}
	
	public void setGrade(float grade){
		this.grade = grade;
	}
	
	public Date getDateSubmitted(){
		return dateSubmitted;
	}
	
	public void setDateSubmitted(Date dateSubmitted){
		this.dateSubmitted = dateSubmitted;
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
