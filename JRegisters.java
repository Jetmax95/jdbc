import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class JRegisters {

	
	Scanner scan = new Scanner(System.in);
	
	public JRegisters(){
		
	}
	
	public void registerStudent(Connection connection){
		int student_id;
		int title_id;
		String forename;
		String lastName;
		String dob;
		
		try {
		
		Statement stmt = connection.createStatement();
		
		System.out.println("Please Enter A Student ID : ");
		student_id = scan.nextInt();
		System.out.println("Choose A Title For the Student :");
		System.out.println("0 : Mr | 1 : Ms | 2 : Mrs | 3 : Dr | 4 : Professor");
		title_id = scan.nextInt();
		System.out.println("Enter Student Name : ");
		forename = scan.next();
		System.out.println("Enter Student LastName : (Leave empty if there is none) ");
		lastName = scan.next();
		System.out.println("Enter date of birth : (FORMAT : YYYY-MM-DD");
		dob = scan.next();
		
		String student_info = "INSERT INTO Student"
	  				+ " VALUES ( " + student_id + "," + title_id + "," + "'"+forename+"'" + "," + "'"+ lastName +"'"+"," + "'" +dob+"'" +  ")";
		stmt.executeUpdate(student_info);
		
		
		}catch(SQLException E){
			System.err.println("Something went wrong with registering the student (Double check if Data is correct ex. Student ID might already exist.)");
		}
	}
	
	public void registerTutor(Connection connection){
		
		int student_id;
		int lecturer_id;
		try{
	
		Statement stmt = connection.createStatement();
		
		ResultSet set = stmt.executeQuery("SELECT lecturerid , forename , familyname FROM Lecturer;");
				
		System.out.println("Enter The Student's ID : (Make sure it exists , else operation will not complete");
		student_id = scan.nextInt();
		while (set.next()){
		
			System.out.println("LID : " + " " + set.getString("lecturerid") + " " + set.getString("forename" ) + " " + set.getString("familyname"));
			
		}
		
		
		System.out.println("Enter The Lecturer's ID : ");
		lecturer_id = scan.nextInt();
		
		String tutor = "INSERT INTO Tutor"
				+ " VALUES (" + student_id + "," + lecturer_id + ")";
	
		stmt.executeUpdate(tutor);
		System.out.println("Tutor Successfully added for Student.");
		} catch (SQLException e) {
			System.err.println("Something Went Wrong With Your Input ... Please Try again. (Student probably has a Tutor already , or invalid tutor ID");
		}
	}
}
