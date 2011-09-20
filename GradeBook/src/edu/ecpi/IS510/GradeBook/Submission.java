package edu.ecpi.IS510.GradeBook;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *  Submission Class is a collection of data about the student's individual assignment to be used for Value data in a Hashtable.
 *  
 */

@DatabaseTable(tableName = "submissions")
public class Submission implements Serializable{
	private static final long serialVersionUID = -368784444373150198L;
	@DatabaseField(generatedId = true) long submissionID;
	@DatabaseField long studentID;
	@DatabaseField float grade;
	@DatabaseField Date dateSubmitted;
	
	/**
	 * Default Submission call if all information is known when submitting data.
	 * @param studentID: ID of the student, used to associate a student with a specific graded assignment
	 * @param dateSubmitted: Date of the submitted and graded assignment
	 * @param grade: Grade of the submitted assignment  
	 */
	public Submission(long studentID, Date dateSubmitted, float grade){
		this.studentID = studentID;
		this.grade = grade;
		this.dateSubmitted = dateSubmitted;
	}
	
	/**
	 * Calls the above constructor if no information is known when submitting data.
	 */
	public Submission(){
		this(0, null, 0);
	}
	
	/** @return studentID: Gets studentID */
	public long getStudentID(){
		return studentID;
	}
	
	/** @param studentID: Sets studentID */
	public void setStudentID(long studentID){
		this.studentID = studentID;
	}
	
	/** @return grade: Gets grade */
	public float getGrade(){
		return grade;
	}
	
	/** @param grade: Sets grade */
	public void setGrade(float grade){
		this.grade = grade;
	}
	
	/** @return dateSubmitted: Gets dateSubmitted */
	public Date getDateSubmitted(){
		return dateSubmitted;
	}
	
	/** @param dateSubmitted: Sets dateSubmitted */
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
	
	public String toString(){
		return "StudentID = " + studentID + " Date Submitted = " + dateSubmitted + " Grade = " + grade + "\n";
	}
}
