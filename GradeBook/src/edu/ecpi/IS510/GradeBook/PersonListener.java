package edu.ecpi.IS510.GradeBook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JTextField;


/*
 * This class listens for a button to be pressed and
 * accepts 6 parameters in it's constructor which it,
 * in turn, passes to the 'actionPerformed' method for parsing,
 * validation (not done yet), and posting to the database
 */
class PersonListener implements ActionListener {
	
	//These fields will be used to store the field from the form
	JTextField firstName, lastName, address, email, phone, id;
	ButtonGroup personType;
	
	/*
	 * Here we create pointers to be able to refer to the fields in the form when
	 * the 'Add Person' button is clicked
	 */
	PersonListener(JTextField firstNameField, JTextField lastNameField, JTextField addressField, JTextField phoneField, JTextField emailField, ButtonGroup personType) {
	  this.firstName = firstNameField;
	  this.lastName = lastNameField;
	  this.address = addressField;
	  this.email = emailField;
	  this.phone = phoneField;
	  this.personType = personType;
  }
	
	

  /*
   * (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   * This function accesses the data in the form fields via the established
   * pointer references and creates new instances of Student or Teacher
   * and adds them to the database
   */
  public void actionPerformed(ActionEvent e) {
	  String selectedButton = personType.getSelection().getActionCommand(); 
	  System.out.println(personType.getSelection().getActionCommand());
	  if(e.getActionCommand().equals("Add Person")){
		  if(selectedButton == "Student"){
			  Student newStudent = new Student();
			  newStudent.setFirstName(this.firstName.getText());
			  newStudent.setLastName(this.lastName.getText());
			  newStudent.setAddress(this.address.getText());
			  newStudent.setEmail(this.email.getText());
			  newStudent.setPhone(this.phone.getText());
			  try {
				GradeBook.dbController.studentDao.createOrUpdate(newStudent);
				GradeBook.updateStudents();
		        System.out.println(GradeBook.students);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }
		  else{
			  Teacher newTeacher = new Teacher();
			  
			  //'getText()' pulls the data from the form field and returns a string
			  newTeacher.setFirstName(this.firstName.getText());
			  newTeacher.setLastName(this.lastName.getText());
			  newTeacher.setAddress(this.address.getText());
			  newTeacher.setEmail(this.email.getText());
			  newTeacher.setPhone(this.phone.getText());
			  try {
				GradeBook.dbController.teacherDao.createOrUpdate(newTeacher);
				//GradeBook.updateStudents();
		        System.out.println(GradeBook.teachers);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  }
	  }
  }
}