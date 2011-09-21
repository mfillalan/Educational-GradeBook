package edu.ecpi.IS510.GradeBook;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
Employee object is used to store employeeID.
This class extends Person which contains name, address, phone, email, etc.
*/

@DatabaseTable(tableName = "employees")
public class Employee extends Person {
	@DatabaseField(generatedId = true) protected long employeeID;
		
	/**
	   Returns employeeID
	   @return long	- Employee ID
	*/
	public long getEmployeeID(){
		return employeeID;
	}
	
	/**
	   Set employeeID
	   @param employeeID	long
	*/
	public void setEmployeeID(long employeeID){
		this.employeeID = employeeID;
	}
}
