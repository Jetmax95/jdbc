import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class JDBC {

	public static void main(String args[]){
		
		JDBCConnection JConnection = new JDBCConnection();
		TableManagement manager = new TableManagement();
		PopulateTables populate = new PopulateTables();
		JRegisters registers = new JRegisters();
		JDBC runner = new JDBC();
		JReporter reporter = new JReporter();
		
		JConnection.SQLConnect();
		
		
		Scanner scan = new Scanner(System.in);
		
		
		
		runner.printMenu();
		
		
		int input = -1;
		
		
		while(input != 0){
			input = scan.nextInt();
			switch(input){
					case 1: manager.createTables(JConnection.getConnection());
							System.out.println("Created tables successfully.");
							runner.printMenu();
						break;
					case 2: manager.dropTables(JConnection.getConnection());
							System.out.println("Dropped all the tables successfully.");
							runner.printMenu();
						break;
					case 3: populate.Populate(JConnection.getConnection());
							System.out.println("Populated the tables successfully.");
							runner.printMenu();
						break;
					case 4: registers.registerStudent(JConnection.getConnection());
							System.out.println("Student Registered Successfully.");
							runner.printMenu();
							break;
					case 5 : registers.registerTutor(JConnection.getConnection());
							System.out.println("");
							runner.printMenu();
							break;
					case 6 : reporter.studentReport(JConnection.getConnection());
							runner.printMenu();
							break;
					case 7 : reporter.lecturerReport(JConnection.getConnection());
							runner.printMenu();
							break;
					case 8: System.out.println("See you around");
							System.exit(0);
							break;
					default : System.out.println("Invalid Input , please try again");
							runner.printMenu();
							break;
				}
		}
		scan.close();
		System.out.println("See you around");
	}
	
	public void printMenu(){
		System.out.println("======= Database Controler ======= ");
		System.out.println("1 ) Create the Tables");
		System.out.println("2 ) Drop the Tables");
		System.out.println("3 ) Populate The Tables");
		System.out.println("4 ) Register A New Student");
		System.out.println("5 ) Add A New Tutor For A Student");
		System.out.println("6 ) Get a Student Report");
		System.out.println("7 ) Get a Lecturer Report");
		System.out.println("8) Exit");
		
	}
}
