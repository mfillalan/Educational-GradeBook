package edu.ecpi.IS510.GradeBook;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "teachers")
public class Teacher extends Employee {
	@DatabaseField(dataType=DataType.SERIALIZABLE) protected Course[] courseList;
	@DatabaseField protected int courseCount;
	
	public Teacher(){
		courseList = new Course[4];
		courseCount = 0;
	}
	
	public void addCourse(Course course){
		if(course != null && !isFull() && !contains(course)){
			int index = 0;
			while(courseList[index] != null){
				index++;
			}
			courseList[index] = course;
		}
	}
	
	public void removeCourse(Course course){
		//TODO: Implement this method
	}
	
	public Course[] getCourseList(){
		return courseList;
	}
	
	protected boolean isFull(){
		return courseCount >= 4;
	}
	
	protected boolean isEmpty(){
		return courseCount <= 0;
	}
	
	protected boolean contains(Course course){
		boolean found = false;
		int index = 0;
		while(index < 4 && !found){
			if(courseList[index] != null){
				found = (course == courseList[index]);
			}
			index++;
		}
		return found;
	}
	
	public String toString(){
		String returnString = "";
		
		returnString =  "Name: " + firstName + " " + lastName + "\n";
		returnString += "Employee ID: " + employeeID + "\n";
		returnString += "Address: " + address + "\n";
		returnString += "Phone: " + phone + "\n";
		returnString += "E-mail: " + email + "\n";
		returnString += firstName + " is currently teaching the following courses: ";
		//Add each course to returnString.
		for( int i = 0; i < courseList.length; i++){
			//Add a comma if this isn't the last course.
			if( (i+1) != courseList.length)			{
				returnString += (i+1) + ": " + courseList[i].getTitle() + ", ";
			}
			//Don't add a comma if this is the last course.
			else{
				returnString += (i+1) + ": " + courseList[i].getTitle();
			}
		}
		return returnString;
	}
}