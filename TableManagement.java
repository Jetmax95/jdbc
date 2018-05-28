	

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
    import java.sql.Statement;
     
    public class TableManagement {
           
            String student;
            String titles;
            String lecturer;
            String studentReg;
            String registrationtype;
            String studentContact;
            String nextOfKin;
            String lecturerContact;
            String tutor;
            String dropAll;
           
            public TableManagement(){
                   
                    this.student =  "CREATE TABLE Student " +
				                    " (studentID INTEGER , " +
				                    " titleID INTEGER NOT NULL,    " +
				                    " forename CHAR(20) NOT NULL, " +
				                    " familyName CHAR(20), " +
				                    " dateOfBirth Date CHECK (dateOfBirth < current_date) ," +
				                    " PRIMARY KEY ( studentID ) ," +
				                    " FOREIGN KEY (titleID) REFERENCES Titles(titleID) " +
				                    " ON DELETE CASCADE " +
				                    " ON UPDATE CASCADE) ";
				                   
                    this.titles =   "CREATE TABLE Titles " +
                                    " ( titleID INTEGER ," +
                                    "  titleString Char(10) NOT NULL," +
                                    "  PRIMARY KEY (titleID)) ";
                   
                     this.lecturer = "CREATE TABLE Lecturer "+
                                     "(lecturerID INTEGER ,"+
			                         "titleID INTEGER NOT NULL," +
			                         "foreName CHAR(20) NOT NULL," +
			                         "familyName CHAR(20)," +
			                         "PRIMARY KEY (lecturerID),"+
			                         "FOREIGN KEY (titleID) REFERENCES Titles(titleID)"+
			                         "ON DELETE CASCADE " +
			                         "ON UPDATE CASCADE)";
			           
                  this.studentReg = "CREATE TABLE StudentRegistration" +
                                    "( studentID INTEGER NOT NULL, " +
                                    " yearOfStudy INTEGER CHECK (yearOfStudy > 0 AND yearOfStudy < 6)," +
                                    " registrationTypeID INTEGER," +
                                    " FOREIGN KEY (studentID) REFERENCES Student(studentID) " +
                                    " ON DELETE CASCADE " +
                                    " ON UPDATE CASCADE ," +
                                    " FOREIGN KEY (registrationTypeID) REFERENCES RegistrationType(registrationTypeID)  " +
                                    " ON DELETE CASCADE " +
                                    " ON UPDATE CASCADE) ";
 
                    this.registrationtype = "CREATE TABLE RegistrationType" +
                                                                     "(registrationTypeID INTEGER NOT NULL ," +
                                                                     " description CHAR (30) NOT NULL ," +
                                                                     " PRIMARY KEY (registrationTypeID))";
                   
                    this.studentContact = "CREATE TABLE StudentContact" +
                                                               "(studentID INTEGER NOT NULL, " +
                                                               " eMailAddress CHAR(70) NOT NULL ," +
                                                               " postalAddress CHAR(70) NOT NULL," +
                                                               "FOREIGN KEY(studentID) REFERENCES Student(studentID) "+
                                                               "ON DELETE CASCADE " +
                                                               "ON UPDATE CASCADE)";
                   
                    this.nextOfKin =  "CREATE TABLE NextOfKinContact "+
                                                       "(studentID INTEGER NOT NULL," +
                                                       "name CHAR(20) NOT NULL ," +
                                                       "emailAddress CHAR(70) , " +
                                                       "postalAddress CHAR(70), " +
                                                       "FOREIGN KEY(studentID) REFERENCES Student(studentID) " +
                                                       "ON DELETE CASCADE " +
                                                       "ON UPDATE CASCADE)";
                   
                    this.lecturerContact = "CREATE TABLE LecturerContact " +
                                                                    "(lecturerID INTEGER NOT NULL , " +
                                                                    "office CHAR(20) NOT NULL ," +
                                                                    "emailAddress CHAR(60) NOT NULL , " +
                                                                    "FOREIGN KEY (lecturerID) REFERENCES Lecturer(lecturerID) " +
                                                                    "ON DELETE CASCADE " +
                                                                    "ON UPDATE CASCADE)" ;
                   
                    this.tutor = "CREATE TABLE Tutor " +
                                              "(studentID INTEGER UNIQUE," +
                                              "lecturerID INTEGER NOT NULL," +
                                              "FOREIGN KEY(studentID) REFERENCES Student(studentID) "+
                                              "ON DELETE CASCADE " +
                                              "ON UPDATE CASCADE ," +
                                              "FOREIGN KEY(lecturerID) REFERENCES Lecturer(lecturerID)" +
                                              "ON DELETE CASCADE " +
                                              "ON UPDATE CASCADE)";
                   
                    this.dropAll = " DROP TABLE Student,Titles,Lecturer, StudentRegistration" +
                                            ",RegistrationType , StudentContact, NextOfKinContact, LecturerContact , Tutor";
     
            }
           
            public void createTables(Connection connection) {
           
           
            try{
                   
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(titles);
                    stmt.executeUpdate(registrationtype);
                    stmt.executeUpdate(student);
                    stmt.executeUpdate(lecturer);
                    stmt.executeUpdate(studentReg);
                    stmt.executeUpdate(studentContact);
                    stmt.executeUpdate(nextOfKin);
                    stmt.executeUpdate(lecturerContact);
                    stmt.executeUpdate(tutor);
                    }
           
            catch(SQLException E){
                    E.printStackTrace();
            }
           
         }
           
            public void dropTables(Connection connection){
                    try {
                            Statement stmt = connection.createStatement();
                            stmt.executeUpdate(dropAll);
                    }
                    catch(SQLException E){
                            System.err.println("Something went wrong on operation : DropTables");;
                    }
            }
           
    }

