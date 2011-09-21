package edu.ecpi.IS510.GradeBook;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;

import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*import com.jgoodies.forms.factories.DefaultComponentFactory;*/
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Hashtable;


public class ManageGradeBook {

	protected JFrame frmIsGradebookApplication;
	
	//Add Person Fields
	protected JTextField firstNameField;
	protected JTextField lastNameField;
	protected JTextField addressField;
	protected JTextField phoneField;
	protected final ButtonGroup personType = new ButtonGroup();
	protected JRadioButton rdbtnNewRadioButton = new JRadioButton("Student");
	protected JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Teacher");
	protected JTextField emailField;
	
	//Add Course Fields
	private int[] creditValues = new int[4];
	protected JTextField courseNameField;
	protected JTextField courseNumberField;

	/**
	 * The class creates the window of the application, places
	 * the fields and labels in the correct places, builds the 
	 * tabs and attaches the correct button listeners
	 * 
	 * @param firstNameField JTextField accepts the first name of the user
	 * @param lastNameField JTextField accepts the last name of the user
	 * @param addressField JTextField accepts the address of the user
	 * @param phoneField JTextField accepts the phone number of the user
	 * @param emailField JTextField accepts the email address of the user
	 * @param rdbtnNewRadioButton JRadioButton choose Student type of Person
	 * @param rdbtnNewRadioButton_1 JRadioButton choose Teacher type of Person
	 * @param personType JTextField accepts the first name of the user
	 * @param courseNameField JTextField accepts the course name
	 * @param courseNumberField JTextField accepts the course number
	 * 
	 * Notes:
	 * This is a test
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageGradeBook window = new ManageGradeBook();
					window.frmIsGradebookApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}/*
	
	

	/**
	 * Create the application.
	 */
	public ManageGradeBook() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frmIsGradebookApplication = new JFrame();
		frmIsGradebookApplication.setTitle("IS510 GradeBook Application");
		frmIsGradebookApplication.setBounds(100, 100, 798, 600);
		frmIsGradebookApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIsGradebookApplication.getContentPane().setLayout(null);
		
		//Simple label for the applicaiton
		JLabel lblWelcomeToThe = new JLabel("IS510 GradeBook Application");
		lblWelcomeToThe.setBounds(20, 23, 254, 14);
		frmIsGradebookApplication.getContentPane().add(lblWelcomeToThe);
		
		//Creates tabbed panel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 46, 752, 505);
		frmIsGradebookApplication.getContentPane().add(tabbedPane);
		
		
		//Add 'People' pane
		JPanel panel = new JPanel();
		tabbedPane.addTab("People", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblThisSectionIs = new JLabel("Add a new person");
		lblThisSectionIs.setBounds(10, 11, 205, 14);
		panel.add(lblThisSectionIs);
		
		
		//****************************   BEGIN PERSON FIELDS *************** /
		firstNameField = new JTextField("Enter Firstname");
		firstNameField.setBounds(79, 33, 205, 20);
		panel.add(firstNameField);
		firstNameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Firstname");
		lblNewLabel.setBounds(10, 36, 46, 14);
		panel.add(lblNewLabel);

		
		lastNameField = new JTextField("Enter Lastname");
		lastNameField.setBounds(79, 61, 205, 20);
		panel.add(lastNameField);
		lastNameField.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Lastname");
		lastNameLabel.setBounds(10, 64, 46, 14);
		panel.add(lastNameLabel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(79, 86, 205, 20);
		panel.add(addressField);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 89, 59, 14);
		panel.add(lblAddress);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(79, 111, 205, 20);
		panel.add(phoneField);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 113, 46, 14);
		panel.add(lblPhone);
		
		rdbtnNewRadioButton.setActionCommand("Student");		
		personType.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(290, 85, 250, 23);
		panel.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1.setActionCommand("Teacher");
		personType.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(290, 60, 250, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(79, 139, 205, 20);
		panel.add(emailField);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(10, 141, 46, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Add Person");
		btnNewButton.setBounds(335, 234, 122, 23);
		panel.add(btnNewButton);
		
		//Add the action listener to the Add Person button and pass in the ACTUAL fields themselves to grant access to the data
		//that will be entered into them
		btnNewButton.addActionListener(new PersonListener(firstNameField, lastNameField, addressField, phoneField, emailField, personType));

		//****************************   END PERSON FIELDS *************** /
		
		

		//****************************   BEGIN COURSE FIELDS *************** /
		//Add 'Courses' pane
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Courses", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Add a new course");
		lblNewLabel_3.setBounds(10, 11, 139, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Course name");
		lblNewLabel_4.setBounds(10, 36, 150, 14);
		panel_1.add(lblNewLabel_4);
		
		courseNameField = new JTextField();
		courseNameField.setBounds(110, 36, 241, 20);
		panel_1.add(courseNameField);
		courseNameField.setColumns(10);

		
		JLabel courseNumberLabel = new JLabel("Course Number");
		courseNumberLabel.setBounds(10, 61, 150, 14);
		panel_1.add(courseNumberLabel);
		
		courseNumberField = new JTextField();
		courseNumberField.setBounds(110, 64, 241, 20);
		panel_1.add(courseNumberField);
		courseNumberField.setColumns(10);
		
		//Create dropdown list of credits
		JComboBox credits = new JComboBox();
		credits.setModel(new DefaultComboBoxModel());
		credits.setBounds(350, 35, 60, 22);
		credits.addItem(1);
		credits.addItem(2);
		credits.addItem(3);
		credits.addItem(4);
		panel_1.add(credits);
		
		JButton btnNewButton_1 = new JButton("Add Course");
		panel_1.add(btnNewButton_1);
		btnNewButton_1.setBounds(331, 234, 126, 23);
		
		//Add 'Add Course' action listener
		btnNewButton_1.addActionListener(new CourseListener(courseNameField, courseNumberField, credits));	
		//****************************   END COURSE FIELDS *************** /
		
		//Add 'Assignments' pane
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Assignments", null, panel_2, null);
		panel_2.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel());
		comboBox.setBounds(10, 35, 231, 20);
		panel_2.add(comboBox);
		
		Student currentStudent = new Student();
		String fullName = "";
		for(int i = 0; i < GradeBook.students.size(); i++){
			currentStudent = GradeBook.students.get(i);
			fullName = currentStudent.getFirstName() + " " + currentStudent.getLastName();
			comboBox.addItem(fullName);
		}
		
		/*
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Student1", "Student2", "Student3", "Student4", "Student5"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(31, 66, 210, 98);
		panel_2.add(list);
		
		JButton btnApplyStudentsTo = new JButton("Add students to class");
		btnApplyStudentsTo.setBounds(559, 423, 178, 23);
		panel_2.add(btnApplyStudentsTo);
		
		JLabel lblUseCtrlclickTo = new JLabel("Use Ctrl+click to add multiple students");
		lblUseCtrlclickTo.setBounds(31, 175, 260, 14);
		panel_2.add(lblUseCtrlclickTo);*/
	}

}


