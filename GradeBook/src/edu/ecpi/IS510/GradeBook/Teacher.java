package edu.ecpi.IS510.GradeBook;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *  Teacher Class assigns courses to a teacher's courseList which is an array of Courses[4].
 *  
 */

@DatabaseTable(tableName = "teachers")
public class Teacher extends Employee {
	/**
	 * initiates the courseList array and courseCount variable.
	 * courseCount is required when not using a Hashtable or Hashlist or Vector for error checking in arrays. 
	 */
	@DatabaseField(dataType=DataType.SERIALIZABLE) protected Course[] courseList;
	@DatabaseField protected int courseCount;
	
	   /** Creates NULL values for courseList of type Course(array) */   
		public Teacher(){
			courseList = new Course[4];
			courseCount = 0;
		}
		
		/**
		 * Adds a course based on the parameter 
		 * @param course: data passed to the method; data includes "courseNumber, title, credits"
		 */
		public void addCourse(Course course){
			// Input Checking: add Course if not Null and it isn't Full and course doesn't already exist
			if(course != null && !isFull() && !contains(course)){ 
				int index = 0; //increments through the array to find the next empty course
				while(courseList[index] != null){
					index++;
				}
				courseList[index] = course;
			}
		}
		/**
		 * Removes a course from the individual teacher's list of courses
		 * @param course: data passed to the method; data includes "courseNumber, title, credits"
		 */	
		public void removeCourse(Course course){
			int index = 0;
			while(courseList[index] != course){
				index++;
			}
			courseList[index] = new Course();	//TODO: Possible Heap problems
		}
		
	    /** Gets CourseList to manipulate the array; initiates the Course[] array */    
		public Course[] getCourseList(){
			return courseList;
		}
		
	    /** @return TRUE if courseCount if "Full"; reaches the end of the array index */   
		protected boolean isFull(){
			return courseCount >= 4; 
			//TODO: courseCount is never incremented 
		}
		
	    /** @return TRUE if courseCount is "Empty"; the array index is 0 or less */   
		protected boolean isEmpty(){
			return courseCount <= 0;
			//TODO: isEmpty is never called and courseCount is never incremented
		}
		/**
		 * Compares new course against all existing courses; Validates to prevent duplicate courses
		 * @param course: data passed to the method; data includes "courseNumber, title, credits"
		 * @return TRUE if course exists
		 */ 
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