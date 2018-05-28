import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Random;
 
 
public class PopulateTables {
 
	String[] titleArray;
	String[] lecturersFirst;
	String[] lecturersSecond;
	String[] registrationTypes;
	
	
    public PopulateTables () {
      
         this.titleArray = new String[5];
         titleArray[0] = "'Mr'";
         titleArray[1] = "'Ms'";
         titleArray[2] = "'Mrs'";
         titleArray[3] = "'Dr'";
         titleArray[4] = "'Professor'";
         
         
 
         
          this.lecturersFirst = new String[5];
          lecturersFirst[0] = "'Mark'";
          lecturersFirst[1] = "'Jon'";
          lecturersFirst[2] = "'Dan'";
          lecturersFirst[3] = "'Volker'";
          lecturersFirst[4] = "'Nick'";
         
          this.lecturersSecond = new String[5];
          lecturersSecond[0] = "'Lee'";
          lecturersSecond[1] = "'Rowe'";
          lecturersSecond[2] = "'Ghica'";
          lecturersSecond[3] = "'Sorge'";
          lecturersSecond[4] = "'Hawes'";
         
          
          this.registrationTypes = new String[4];
          registrationTypes[0] = "'normal'";
          registrationTypes[1] = "'resit'";
          registrationTypes[2] = "'retake'";
          registrationTypes[3] = "'external'";
          
                                              
                 
          }
          
          public void Populate(Connection connection) {
              
              
              try{
            	  Statement stmt = connection.createStatement();
            	  
            	  // == POPULATE THE TITLES TABLE ==//
            	  for(int i = 0; i < titleArray.length; i++){
                      String insert = "INSERT INTO Titles " +
                                                      "VALUES ( " + i + "," + titleArray[i]+ ")" ;
                      stmt.executeUpdate(insert);
            	  }
            	 System.out.println("Titles table populated successfully...");
            	  
            	  // == POPULATE THE LECTURER TABLE ==//
            	 
            	 
            	 Random rn = new Random();
            	  for(int i = 0; i < lecturersFirst.length; i++){
                      String lecturer_info = "INSERT INTO Lecturer"
                                            + " VALUES (" + i + "," + rn.nextInt(4)  
                                            + "," + lecturersFirst[i] + "," + lecturersSecond[i] + ")";
                      stmt.executeUpdate(lecturer_info);
            	  }
            	   String lecturer_info_real = "INSERT INTO Lecturer"
                           + " VALUES (" + 5 + "," + 3 + "," + "'Martin'" + "," + "'Escardo'" + ")";
            	   stmt.executeUpdate(lecturer_info_real);
            	  System.out.println("Lecturer table populated successfully...");
            	  
            	  // == POPULATE THE STUDENT TABLE ==//
            	  int dummy_id = 1333574;
            	  
            	  int month_min = 3;
            	  int month_max = 12;
            	  int day_min = 1;
            	  int day_max = 30;
            	  
            	  String student_real = "INSERT INTO Student"
            			  + " VALUES ( " + 1333573 + "," + 0 + "," + "'Ioannis'" + "," + "'Angelakos'" + "," + "'1995-10-25'" + ")";
            	  stmt.executeUpdate(student_real);
            	  for(int i = 0 ; i < 140 ; i++ ){
            		  
            		  int year = rn.nextInt(1998-1994) + 1994;
                	  int month = rn.nextInt(month_max-month_min+1) +month_min;
                	  int day = rn.nextInt(day_max-day_min + 1) + day_min;
                	  
            		  String student_info = "INSERT INTO Student"
            				  				+ " VALUES ( " + (dummy_id+i) + "," + rn.nextInt(4) + "," + "'FirstName"+i + "'," + "'LastName"+i+"'," + "'"+year+"-"+month+"-"+ day+"'" + ")";
            		  stmt.executeUpdate(student_info);
            	  }
            	  System.out.println("Student table populated successfully");
            	  
            	 //== POPULATE RegistrationType TABLE ==//
            	  
            	  for(int i = 0; i < registrationTypes.length; i++){
            		  String regType = registrationTypes[i];
            		  String regTypeFull = "INSERT INTO RegistrationType" 
            				  				+ " VALUES ( " + i + "," + regType + ")";
            		  stmt.executeUpdate(regTypeFull);
            	  }
            	  
            	  System.out.println("RegistrationType Table populated successfully...");
            	  
            	  //== POLULATE StudentRegistration Table ==//
            	  dummy_id = 1333574;
            	 String student_register_real = "INSERT INTO StudentRegistration" 
				  				+ " VALUES ( " + 1333573 + "," + 2 + "," + 0 + ")";
            	 stmt.executeUpdate(student_register_real);
		
            	  for(int i = 0 ; i < 140 ; i++){
	           		int yearOfStudy = rn.nextInt(5-1)+ 1;
	            	int regTypeId = rn.nextInt(4-0) + 0;            		
           		  	String student_register = "INSERT INTO StudentRegistration" 
           				  				+ " VALUES ( " + (dummy_id+i) + "," + yearOfStudy + "," + regTypeId + ")";
            		  stmt.executeUpdate(student_register);
            	  }
            	  System.out.println("StudentRegistration Table populated successfully...");
            	  
            	  //== POPULATE StudentContact TABLE ==//
            	  dummy_id = 1333574;
            	  
            	  String student_contact_real = "INSERT INTO StudentContact"
				  			+ " VALUES (" + 1333573 + "," +"'ixa373@student.bham.ac.uk'"+","+ "'Victoria Halls Phase 1'" + ")" ;
		  
            	  stmt.executeUpdate(student_contact_real);
            	  for(int i = 0 ; i < 140 ; i++){
            		  
            		  String student_contact = "INSERT INTO StudentContact"
            				  			+ " VALUES (" + (dummy_id+i) + "," +"'email"+i+"@student.bham.ac.uk'"+","+ "'Birmingham Street"+i +" Road High' )" ;
            		  stmt.executeUpdate(student_contact);
            		  
            	  }
            	  System.out.println("Student Contact Table populated successfully...");
            	  
            	  //== Populate NextOfKinContact TABLE ==//
            	  
            	  String real_kin_contact = "INSERT INTO NextOfKinContact"
        				  + " VALUES (" + 1333573 +"," + "'Eleni'"+","+ "'angelako@otenet.gr'" + "," + "'Greece , Athens ,Aristotelous 35'" + " )";
            	  stmt.executeUpdate(real_kin_contact);
        		
            	  dummy_id = 1333574;
            	  for(int i = 0; i < 140 ; i++){
            		  String kin_contact = "INSERT INTO NextOfKinContact"
            				  + " VALUES (" + (dummy_id + i) +"," + "'name " + i +"'" +","+"'email"+i+"@somemail.co.uk'" + "," + "'Some UK address "+i+ ",Some Road' )";
            		  stmt.executeUpdate(kin_contact);
            	  }
            	  System.out.println("NextOfKinContact Table populated successfully...");
            	  
            	  //== Populate LecturerContact TABLE ==//
            	  
            	  for(int i = 0 ; i < 5; i++){
            		  String lec_contact = "INSERT INTO LecturerContact"
            				  + " VALUES ( " + i + "," + "'CS10"+i+"'" + "," + "'lecturerEmail" +i+ "@cs.bham.ac.uk')";
            		 stmt.executeUpdate(lec_contact);
            	  }
            	  String lec_contact_real = "INSERT INTO LecturerContact"
        				  + " VALUES ( " + 5 + "," + "'212'" + "," + "'m.escardo@cs.bham.ac.uk'" + ")";
            	  	stmt.executeUpdate(lec_contact_real);
        		
            	  System.out.println("LecturerContact Table populated successfully...");
            	  
            	  //== Populate Tutor TABLE ==//
            	  
            	  dummy_id = 1333574;
            	  for(int i = 0; i < 50; i++){
            		int lec_id = rn.nextInt(4-0) + 0;
            		
            		  String tutor = "INSERT INTO Tutor"
            				  + " VALUES (" + (dummy_id+i) + "," + lec_id + ")";  
            		  stmt.executeUpdate(tutor);
            	  }
            	  String tutor_real = "INSERT INTO Tutor"
        				  + " VALUES (" + 1333573 + "," + 5 + ")";
            	  stmt.executeUpdate(tutor_real);
        	  
            	  
            	  
            	  System.out.println("Tutor Table Populated Successfully...");
              }
            	  catch(SQLException E){
            		  System.err.println("Tables Already Populated!!");

              	  }
              
          }
}
            	 
              
          

   