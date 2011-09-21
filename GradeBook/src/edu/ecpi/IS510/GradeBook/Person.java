package edu.ecpi.IS510.GradeBook;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
* Person object used to define the main attributes a person will contain when created.
*/

@DatabaseTable(tableName = "people")
public class Person {
	@DatabaseField protected String firstName;
	@DatabaseField protected String lastName;
	@DatabaseField protected String address;
	@DatabaseField protected String phone;
	@DatabaseField protected String email;
	
	public Person(String firstName, String lastName, String address, String phone, String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	public Person() {
		this("", "", "", "", "");
	}
	
	/**
	    * <p>
	    * Returns the person's first name.
	    *
	    *
	    * @return the person's first name in a String
	    */
		public String getFirstName(){
			return firstName;
		}
		
		/**
		 * <p>
		 * Sets the first name for the person.
		 *
		 *
		 * @param firstName String of the person's first name
		 */
		public void setFirstName(String firstName){
			this.firstName = firstName;
		}
		
		/**
		 * <p>
		 * Get the person's last name.
		 * 
		 *
		 * @return the person's last name in a String
		 */
		public String getLastName(){
			return lastName;
		}
		
		/**
		 * <p>
		 * Sets the last name of the person.
		 * 
		 * @param lastName String of the person's last name
		 */
		public void setLastName(String lastName){
			this.lastName = lastName;
		}
		
		/**
		 * <p>
		 * Gets the assigned address to the person.
		 * 
		 * @return the person's address
		 */
		public String getAddress(){
			return address;
		}
		
		/**
		 * <p>
		 * Set the address for the person
		 * 
		 * @param address String of the person's address
		 */
		public void setAddress(String address){
			this.address = address;
		}
		
		/**
		 * <p>
		 * Get the assigned phone number to the person
		 * 
		 * @return String of the person's phone number
		 */
		public String getPhone(){
			return phone;
		}
		
		/**
		 * <p>
		 * Set a phone number to the person
		 * 
		 * @param phone String of the person's phone number
		 */
		public void setPhone(String phone){
			this.phone = phone;
		}
		
		/**
		 * <p>
		 * Get the assigned e-mail to the person
		 * 
		 * @return String of the person's E-mail
		 */
		public String getEmail(){
			return email;
		}
		
		/**
		 * <p>
		 * Set the E-mail to the person
		 * 
		 * @param email String of the person's E-mail
		 */
		public void setEmail(String email){
			this.email = email;
		}
}
