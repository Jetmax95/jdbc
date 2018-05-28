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

import javax.swing.plaf.synth.SynthSeparatorUI;


public class JReporter {

	public JReporter(){
		
	}
	
	public void studentReport(Connection connection){
		
		try {
			Statement stmt = connection.createStatement();
			Statement stmt2 = connection.createStatement();
			Statement stmt3 = connection.createStatement();
			
			int studentid;
			Scanner scan = new Scanner(System.in);
			System.out.println("Please Enter the Student's ID that you want a report for : ");
			studentid = scan.nextInt();
			
			ResultSet studentInfo = stmt.executeQuery("SELECT titles.titlestring , student.forename , student.familyname , student.dateofbirth , student.studentID , studentregistration.yearofstudy "
													+ ", registrationtype.description , studentcontact.emailaddress , studentcontact.postaladdress" +
													" FROM student,titles,studentregistration,registrationtype , studentcontact " +
													" WHERE student.titleid = titles.titleid " +
													" AND student.studentid = "+ studentid +
													" AND student.studentid = studentregistration.studentid" +
													" AND studentregistration.registrationtypeid = registrationtype.registrationtypeid" +
													" AND studentcontact.studentID =student.studentid;");
			
			ResultSet kinInfo = stmt2.executeQuery("SELECT nextofkincontact.name , nextofkincontact.emailaddress , nextofkincontact.postaladdress " +
												  " FROM student,studentcontact , nextofkincontact" +
												  " WHERE student.studentid = " + studentid +
												  "	AND studentcontact.studentID =student.studentid " +
												  "	AND nextofkincontact.studentid = student.studentid;");
			
			ResultSet tutorInfo = stmt3.executeQuery("SELECT lecturer.forename , lecturer.familyname" +
													 " FROM student ,tutor, lecturer " +
													 " WHERE student.studentid= " + studentid +
													 "AND tutor.lecturerid = lecturer.lecturerid " +
													 "AND tutor.studentid = student.studentid;");
			
			
			while(studentInfo.next()){
			
			System.out.println("");
			System.out.println("");
				
			System.out.println("============= STUDENT INFORMATION ===============");
			System.out.println("Student Name : " + studentInfo.getString("titleString") + " " + studentInfo.getString("forename") + " " + studentInfo.getString("familyname"));
			System.out.println("Date of Birth : " + studentInfo.getString("dateOfBirth"));
			System.out.println("Student ID : " + studentInfo.getString("studentid"));
			System.out.println("Year of Study : " + studentInfo.getString("yearofstudy"));
			System.out.println("Registration Type : " + studentInfo.getString("description"));
			System.out.println("Student E-mail Address : " + studentInfo.getString("emailAddress"));
			System.out.println("Postal Address : " + studentInfo.getString("postalAddress"));
			}
			while(kinInfo.next()){
			System.out.println("=========== EMERGENCY CONTACT INFORMATION : =================");
			System.out.println("Name : " + kinInfo.getString("name"));
			System.out.println("Email Address : " + kinInfo.getString("emailaddress"));
			System.out.println("Postal Address : " + kinInfo.getString("postaladdress"));
			}
			while(tutorInfo.next()){
			System.out.println("========== TUTOR INFORMATION ===========");
			System.out.println("Name : " + tutorInfo.getString("forename") + " " + tutorInfo.getString("familyname"));
			}
			
			
			
			
		} catch (SQLException e) {
			System.err.println("Something went wrong with the student report (Perhaps Data is missing or corrupted.)");
		}
		
	}
	
	public void lecturerReport(Connection connection){
		try {
			Statement stmt = connection.createStatement();
			Statement stmt2 = connection.createStatement();
			Statement stmt3 = connection.createStatement();
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter the Lecturer ID that you want the report for : ");
			ResultSet set = stmt.executeQuery("SELECT lecturerid , forename , familyname FROM Lecturer;");
			while(set.next()){
				System.out.println("LID : " + " " + set.getString("lecturerid") + " " + set.getString("forename" ) + " " + set.getString("familyname"));
			}
			
			int lid = scan.nextInt();
			
			for(int i = 1 ; i < 6 ; i ++ ){
			
			ResultSet table = stmt.executeQuery("SELECT student.studentid " +
												 "  FROM Lecturer,student,studentregistration , tutor" +
												 "	WHERE lecturer.lecturerid = " + lid +
												 "  AND studentregistration.yearofstudy = " + i + 
												 "	AND lecturer.lecturerid = tutor.lecturerid " +
												 "	AND student.studentid = tutor.studentid " +
												 "	AND Student.studentid = studentregistration.studentid;");
			
			
			System.out.println("YEAR OF STUDY : " + i);
			System.out.println("====================");
			while(table.next()){
				
				int studentid = table.getInt("studentid");
				ResultSet studentInfo = stmt2.executeQuery("SELECT titles.titlestring , student.forename , student.familyname , student.dateofbirth , student.studentID  "
						+ ", registrationtype.description , studentcontact.emailaddress , studentcontact.postaladdress" +
						" FROM student,titles,studentregistration,registrationtype , studentcontact " +
						" WHERE student.titleid = titles.titleid " +
						" AND student.studentid = "+ studentid +
						" AND student.studentid = studentregistration.studentid" +
						" AND studentregistration.registrationtypeid = registrationtype.registrationtypeid" +
						" AND studentcontact.studentID =student.studentid;");
				while(studentInfo.next()){
				
				ResultSet kinInfo = stmt3.executeQuery("SELECT nextofkincontact.name , nextofkincontact.emailaddress , nextofkincontact.postaladdress " +
								 " FROM student,studentcontact , nextofkincontact" +
								 " WHERE student.studentid = " + studentid +
								 "	AND studentcontact.studentID =student.studentid " +
								 "	AND nextofkincontact.studentid = student.studentid;");

				System.out.println("============= STUDENT INFORMATION ===============");
				System.out.println("Student Name : " + studentInfo.getString("titleString") + " " + studentInfo.getString("forename") + " " + studentInfo.getString("familyname"));
				System.out.println("Student ID : " + studentInfo.getString("studentid"));
				System.out.println("Registration Type : " + studentInfo.getString("description"));
				System.out.println("Date of Birth : " + studentInfo.getString("dateOfBirth"));
				System.out.println("Student E-mail Address : " + studentInfo.getString("emailAddress"));
				System.out.println("Postal Address : " + studentInfo.getString("postalAddress"));
				System.out.println("=========== EMERGENCY CONTACT INFORMATION : =================");
				
				while(kinInfo.next()){
					System.out.println("Name : " + kinInfo.getString("name"));
					System.out.println("Email Address : " + kinInfo.getString("emailaddress"));
					System.out.println("Postal Address : " + kinInfo.getString("postaladdress"));
				}
			
				System.out.println("");
				System.out.println("");
				System.out.println("");
				
				}
				
				
				
				
				
						
			}
			
			
			
			}
			
		} catch (SQLException e) {
			System.err.println("Something went wrong , I am sorry");
			
		}
	}
}
