package edu.ecpi.IS510.GradeBook;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
Student object used to add a student id as an extension of Person to create a Student object. 
*/

@DatabaseTable(tableName = "students")
public class Student extends Person{
	@DatabaseField(generatedId = true) protected long studentID;
		
	/**
	 * @author Jim Esposito
	 * Used to store course information for students in order to calculate GPA
	 */
	protected HashMap<String, Course> courses;
	
	public Student(String firstName, String lastName, String address, String phone, String email) {
		super(firstName, lastName, address, phone, email);
		courses = new HashMap<String, Course>();
	}
	
	public Student() {
		super();
		courses = new HashMap<String, Course>();
	}

	/**
	 * adds a course to the courses hash table
	 * 
	 * @param Course
	 */
	public void addCourse(Course course) throws Exception {
		if(!courses.containsKey(course.getCourseNumber())){
			courses.put(course.getCourseNumber(), course);
		} else {
			throw new Exception("Course already added to Student");
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public long getStudentID(){
		return studentID;
	}
	
	/**
	 * 
	 * @param studentID
	 */
	public void setStudentID(long studentID){
		this.studentID = studentID;
	}
	
	/**
	 * This function will return the GPA points for a given final grade
	 * 		90 = 4.0
	 * 		80 = 3.0
	 * 		70 = 2.0
	 * 		60 = 1.0
	 * 		59 and less = 0.0
	 * @param grade
	 * @return
	 */
	private float CalculateGPAPoints(float grade){
		if(grade>=90){
				return 4.0f;
		} else if(grade>=80){
				return 3.0f;
		} else if(grade>=70){
				return 2.0f;
		} else if(grade>=60){
				return 1.0f;
		}
		return 0.0f;
	}
	
	/**
	 * This function will loop through all of the students courses and return a GPA 
	 * @return
	 */
	public float calculateGPA(){
		float totalCreditPoints=0.0f;
		float totalCredits=0.0f;
		float GPA=0.0f;
		Collection<Course> courseColl = courses.values();
		Course currentCourse = new Course();
		Iterator<Course>c = courseColl.iterator();
	    while (c.hasNext()) {
	    	currentCourse = c.next();
	    	if (currentCourse.credits>0){
		    	totalCredits += currentCourse.credits;
		    	totalCreditPoints += currentCourse.credits * this.CalculateGPAPoints(currentCourse.getFinalGrade(this));
	    	}
	    }
	    GPA = totalCreditPoints/totalCredits;
		return GPA;
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
