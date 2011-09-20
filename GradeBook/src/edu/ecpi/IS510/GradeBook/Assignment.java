package edu.ecpi.IS510.GradeBook;

import java.util.Hashtable;
import java.util.Date;
import java.util.Collection;
import java.util.Iterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
Assignment object used to store each assignment with title due date description of the assignment and grade weight applied to each assignment @author Richard
*/

@DatabaseTable (tableName = "assignments")
public class Assignment implements Serializable{
	private static final long serialVersionUID = 729937877153222955L;
	@DatabaseField(id = true) protected int assignmentID;
	@DatabaseField(dataType=DataType.SERIALIZABLE) protected Hashtable<Long, Submission> submissions;
	@DatabaseField protected Date dueDate;
	@DatabaseField protected float weight;
	@DatabaseField protected String title;
	@DatabaseField protected String description;
	
	/**
	 * 
	 * @param title String used as a name representation for the assignment.
	 * @param description Date the date that assignment is due
	 * @param dueDate String Description of the assignment given
	 * @param weight Float value averaged into final grade for this assignment
	 */
	public Assignment(String title, String description, Date dueDate, float weight){
		submissions = new Hashtable<Long, Submission>();
		this.dueDate = dueDate;
		this.weight = weight;
		this.title = title;
		this.description = description;
	}
	/**
	 * Assignment convenience constructor that creates a assignment object
	 *   
	 * @param dueDate Date the date that assignment is due.
	 * @param weight Float value averaged into final grade for this assignment
	 */
	public Assignment(Date dueDate, float weight){
		this("", "", dueDate, weight);
	}
	/**
	 * Assignment convenience constructor that creates a assignment object
	 * 
	 * @param none;
	 */
	public Assignment(){
		this(null, 0.0f);
	}
	/**
	 * adds a submission to the submission hash table
	 * 
	 * @param submission
	 */
	public void addSubmission(Submission submission){
		submissions.put(submission.getStudentID(), submission);
	}
	/**
	 * 
	 * @param studentID
	 * @return value of
	 */
	public Submission getSubmission(long studentID){
		return submissions.get(Long.valueOf(studentID));
	}
	/**
	 * 
	 * @return submissions values
	 */
	public Collection<Submission> getSubmissions(){
		return submissions.values();
	}
	/**
	 * 
	 * @return dueDate
	 */
	public Date getDueDate(){
		return dueDate;
	}
	/**
	 * 
	 * @param dueDate sets dueDate;
	 */
	public void setDueDate(Date dueDate){
		this.dueDate = dueDate;
	}
	/**
	 * 
	 * @return weight
	 */
	public float getWeight(){
		return weight;
	}
	/**
	 * 
	 * @param weight sets grade weight;
	 */
	public void setWeight(float weight){
		this.weight = weight;
	}
	/**
	 * 
	 * @return title
	 */
	public String getTitle(){
		return title;
	}
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	 * 
	 * @return description
	 */
	public String getDescription(){
		return description;
	}
	/**
	 * 
	 * @param description set description
	 */
	public void setDescription(String description){
		this.description = description;
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
		String returnString = "";
		
		returnString =  "Assignment: " + title + "\n";
		returnString += "Due on: " + dueDate + "\n";
		returnString += "Weight: " + weight + "\n";
		returnString += "Description: " + description + "\n";
		returnString += "Submissions: " +  "\n";
		
		Iterator<Submission> submissionIterator = submissions.values().iterator();
		while(submissionIterator.hasNext()){
			Submission s = submissionIterator.next();
			returnString += s.toString();
		}
		
		return returnString;
	}
}
