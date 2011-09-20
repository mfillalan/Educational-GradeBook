package edu.ecpi.IS510.GradeBook;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Vector;
import java.util.Hashtable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
Course object is used to store each student's final grades for a specific course number and title
The course object contains Student objects and Assignment objects. Students and assignments must be added to courses in order to assign final grades.
*/

@DatabaseTable(tableName = "courses")
public class Course implements Serializable{
	//TODO: Revisit the cycle between students and courses (potential memory leaks)
	@DatabaseField(dataType=DataType.SERIALIZABLE) Vector<Student> students;
	@DatabaseField(dataType=DataType.SERIALIZABLE) Vector<Assignment> assignments;
	@DatabaseField(dataType=DataType.SERIALIZABLE) Hashtable<Long,Float> finalGrades;
	@DatabaseField(id = true) String courseNumber;
	@DatabaseField String title;
	@DatabaseField int credits;
	private static final long serialVersionUID = 1L;
	
	/**
    <p>
    Course constructor to create new course objects
    @param <b>courseNumber</b>  	String used as a name representation for the course.
    @param <b>title</b> 			String used as a title for the course.
    */    
	public Course(String courseNumber, String title, int credits){
		students = new Vector<Student>();
		assignments = new Vector<Assignment>();
		finalGrades = new Hashtable<Long,Float>();
		this.courseNumber = courseNumber;
		this.title = title;		
		this.credits = credits;
	}
	
	/**
    <p>
    Generic Course constructor to create new course objects
    */    
	public Course(){
		this("", "",0);
	}
	
    /**
    <p>
    Add Student object to this course. Will not add Student object if it has been added to the course already.
    @param <b>student</b>  	Method expects Student object to be passed in.
    */    
	public void addStudent(Student student) throws Exception {
		if(!students.contains(student)){
			students.addElement(student);
			student.addCourse(this);
		}else{
			throw new Exception("Student already associated with this course");
		}
	}
	
    /**
    <p>
    Add Assignment object to this course. Will not add Assignment object if it has been added to the course already.
    @param <b>assignment</b>  	Method expects Assignment object to be passed in.
    */    
	public void addAssignment(Assignment assignment) throws Exception {
		if(!assignments.contains(assignment)){
			assignments.add(assignment);
		}else{
			throw new Exception("Assignment already added to this course");
		}
	}
	
    /**
    <p>
    This method will assign the students final grade to this course. Student must have been added to course.
    @param <b>student</b>  	Method expects Student object to be passed in.
    @param <b>grade</b>		Method expects grade to be submitted as a float value. <b>Grade should be entered as a decimal value between 0 and 1</b>
    */    
	public void setFinalGrade(Student student, float grade){
		if(student != null){
			finalGrades.put(student.getStudentID(), grade);
		}
	}
	
    /**
    <p>
    This method will return the students final grade for this course. Student must have been added to course and final grade set.
    @param <b>student</b>  	Method expects Student object to be passed in.
    @param <b>grade</b>		Method expects grade to be submitted as a float value. <b>Grade should be entered as a decimal value between 0 and 1</b>
    @return <b>float</b>	Final grade
    */    
	public float getFinalGrade(Student student) {
		if(student!=null){
			Float result = finalGrades.get(student.getStudentID());
			if (result!=null){
				return result.floatValue();
			}
		}
		return -1.0f;
	}

	/**
	 * 
	 * @return
	 */
    public Integer getCredits() {
		return credits;
	}

    /**
     * 
     * @param credits
     */
	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	//(Michael Fillalan 9/14/2011 6:18pm) Edited, but still needs testing for output.
	/**
	   calculateFinalGrades will iterate through all assignments for this course and then calculate grades 
	   based on student submissions for those assignments.
	   @author Jim
	*/
	public void calculateFinalGrades(){
		Hashtable<Long,Submission> submissions;
		Hashtable<Long,Float> gradeSubtotal = new Hashtable<Long,Float>();
		Assignment currentAssignment = new Assignment();
		Iterator<Assignment>a = assignments.iterator();
	    while (a.hasNext()) {
	    	currentAssignment = (Assignment)a.next();
	    	submissions = currentAssignment.submissions;
		    Student currentStudent = new Student();
		    Iterator<Student> s = students.iterator();
		    while (s.hasNext()) {
		      currentStudent = (Student)s.next();
		      Submission sObj=submissions.get(currentStudent.studentID);
		      if(sObj!=null){
			      if(!gradeSubtotal.containsKey(sObj.studentID)){
			    	  gradeSubtotal.put(sObj.studentID, sObj.grade*currentAssignment.weight);
			      }else{
			    	  gradeSubtotal.put(sObj.studentID, gradeSubtotal.get(sObj.studentID)+(sObj.grade*currentAssignment.weight)); 
			      }
		      }
		    }
	    }
	    Student currentStudent = new Student();
	    Iterator<Student> s = students.iterator(); 
	    while (s.hasNext()) {
		    currentStudent = (Student)s.next();
		    setFinalGrade(currentStudent, gradeSubtotal.get(currentStudent.studentID)*100);
		}
	}

	/**
	   Overload calculateFinalGrades method. If StudentID is passed in, it will calculate this one students final
	   grade for this course and return that grade in a float to the caller.
       @param <b>StudentID</b>  	Pass in a long value for the student ID.
       @return <b>Float</b>			Final grade calculation for StudentID
	*/
	public float calculateFinalGrades(long StudentID){
		Hashtable<Long,Submission> submissions;
		Hashtable<Long,Float> gradeSubtotal = new Hashtable<Long,Float>();
		Assignment currentAssignment = new Assignment();
		Iterator<Assignment>a = assignments.iterator();
	    while (a.hasNext()) {
	    	currentAssignment = (Assignment)a.next();
	    	submissions = currentAssignment.submissions;
		    Student currentStudent = new Student();
		    Iterator<Student> s = students.iterator();
		    while (s.hasNext()) {
		      currentStudent = (Student)s.next();
		      if(StudentID==currentStudent.studentID){
		    	  	Submission sObj=submissions.get(currentStudent.studentID);
		    	  	if(sObj!=null){
						if (!gradeSubtotal.containsKey(sObj.studentID)) {
							gradeSubtotal.put(sObj.studentID, sObj.grade
									* currentAssignment.weight);
						} else {
							gradeSubtotal
									.put(sObj.studentID,
											gradeSubtotal.get(sObj.studentID)
													+ (sObj.grade * currentAssignment.weight));
						}
		    	  	}
		      }
		    }
	    }
	    if(gradeSubtotal.get(StudentID)==null){
	    	return 0.00f;
	    }
		return (gradeSubtotal.get(StudentID)*100);
	}

	/**
	   Returns courseNumber to caller
	   @return <b>String</b>	courseNumber
	*/
	public String getCourseNumber(){
		return courseNumber;
	}
	
	/**
	   Sets courseNumber for this course
       @param <b>courseNumber</b>  	String value representing the course number.
	*/
	public void setCourseNumber(String courseNumber){
		this.courseNumber = courseNumber;
	}
	
	/**
	   Returns course title to caller
	   @return <b>String</b>	String value representing the course title.
	*/
	public String getTitle(){
		return title;
	}
	
	/**
	   Sets course title
       @param <b>title</b>  	String value representing the course title.
	*/
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
	
	public String toString(){
		return "Course number: " + courseNumber + " Course title: " + title;
	}
}
