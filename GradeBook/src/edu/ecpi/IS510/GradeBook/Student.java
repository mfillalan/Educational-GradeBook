package edu.ecpi.IS510.GradeBook;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "students")
public class Student extends Person{
	@DatabaseField protected long studentID;
	
	public Student(){
	}
	
	public long getStudentID(){
		return studentID;
	}
	
	public void setStudentID(long studentID){
		this.studentID = studentID;
	}
	
	public String toString(){
		String returnString = "";
		
		returnString =  "Name: " + firstName + " " + lastName + "\n";
		returnString += "Student ID: " + studentID + "\n";
		returnString += "Address: " + address + "\n";
		returnString += "Phone: " + phone + "\n";
		returnString += "E-mail: " + email + "\n";
		
		return returnString;
	}
}
