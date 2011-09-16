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

@DatabaseTable (tableName = "assignments")
public class Assignment implements Serializable{
	private static final long serialVersionUID = 729937877153222955L;
	@DatabaseField(id = true) protected int assignmentID;
	@DatabaseField(dataType=DataType.SERIALIZABLE) protected Hashtable<Long, Submission> submissions;
	@DatabaseField protected Date dueDate;
	@DatabaseField protected float weight;
	@DatabaseField protected String title;
	@DatabaseField protected String description;
	
	public Assignment(String title, String description, Date dueDate, float weight){
		submissions = new Hashtable<Long, Submission>();
		this.dueDate = dueDate;
		this.weight = weight;
		this.title = title;
		this.description = description;
	}
	
	public Assignment(Date dueDate, float weight){
		this("", "", dueDate, weight);
	}
	
	public Assignment(){
		this(null, 0.0f);
	}
	
	public Submission getSubmission(long studentID){
		return submissions.get(Long.valueOf(studentID));
	}

	public Collection<Submission> getSubmissions(){
		return submissions.values();
	}

	public void addSubmission(Submission submission){
		submissions.put(submission.getStudentID(), submission);
	}
	
	public Date getDueDate(){
		return dueDate;
	}
	
	public void setDueDate(Date dueDate){
		this.dueDate = dueDate;
	}
	
	public float getWeight(){
		return weight;
	}
	
	public void setWeight(float weight){
		this.weight = weight;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getDescription(){
		return description;
	}
	
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
