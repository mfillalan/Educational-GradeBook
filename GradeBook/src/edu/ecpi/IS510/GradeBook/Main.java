/**
 * The Main class is the entry point for the GradeBook program
 *  
 * @author Greg Patrick.
 * @version 0.5
 */

package edu.ecpi.IS510.GradeBook;

import java.sql.SQLException;



public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		GradeBook gradeBook = new GradeBook();
		gradeBook.run();
	}
		
}
