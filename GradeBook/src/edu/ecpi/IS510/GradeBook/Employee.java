package edu.ecpi.IS510.GradeBook;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "employees")
public class Employee extends Person {
	@DatabaseField
	protected long employeeID;
		
	public long getEmployeeID(){
		return employeeID;
	}
	
	public void setEmployeeID(long employeeID){
		this.employeeID = employeeID;
	}
}
