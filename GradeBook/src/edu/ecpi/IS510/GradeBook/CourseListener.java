package edu.ecpi.IS510.GradeBook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;


/*
 * This class listens for a button to be pressed and
 * accepts 2 parameters in it's constructor which it,
 * in turn, passes to the 'actionPerformed' method for parsing,
 * validation (not done yet), and posting to the database
 */

class CourseListener implements ActionListener {
	JTextField courseName, courseNumber;
	JComboBox credits;
	long personId;
	CourseListener(JTextField courseNameField, JTextField courseNumberField, JComboBox credits) {
	  this.courseName = courseNameField;
	  this.courseNumber = courseNumberField;
	  this.credits = credits;
  }

  public void actionPerformed(ActionEvent e) {
	  Course newCourse = new Course();
	  newCourse.courseNumber = courseNumber.getText();
	  newCourse.title = courseName.getText();
	  newCourse.credits = credits.getSelectedIndex() + 1;
	  //System.out.println("Adding: " + newCourse.title + "-" + newCourse.courseNumber);

	  try {
		GradeBook.dbController.courseDao.createOrUpdate(newCourse);
		//GradeBook.updateStudents();
        System.out.println(GradeBook.courses);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  }
}