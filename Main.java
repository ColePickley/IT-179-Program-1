/*
 * Created on: August 28, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

import java.io.File;
import java.util.Scanner;

/*
 * Runs and controls the flow of the program
 * 
 * @author Cole Pickley
 */
public class Main {
	
	private static Scanner scan = new Scanner(System.in);
	private static StudentReport studentReport = null;
	private static int scanIn = 0;
	private static boolean inputValid = false;
	private static boolean systemRunning = true;
	private static boolean fileAccessed = false;
	
	/**
	 * Runs the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		while (systemRunning) {
			runStartScreen();
			switch(scanIn) {
				case 1:
					processFile();
					break;
				case 2:
					printStudentNames();
					break;
				case 3:
					generateStudentReportCard();
					break;
				case 4:
					printClassStats();
					break;
				case 5:
					exitProgram();
					break;
				default: System.out.println("Invalid Input");
			}
			inputValid = false;
		}
	}
	
	/**
	 * Runs the start screen and takes a user input for the function they wish the program to run next
	 */
	private static void runStartScreen() {
		printStartScreen();
		while (!inputValid) {
			try {
				scanIn = Integer.parseInt(scan.next());
				scan.nextLine();
				System.out.println();
				inputValid = true;
			} catch (NumberFormatException e) {
				scan.nextLine();
				System.out.println();
				System.out.println("Invalid Input");
				printStartScreen();
			}
		}
		inputValid = false;
	}
	
	/**
	 * Runs the first function of the program:
	 * Takes the file name the user inputs, creates a new StudentReport object, and sets fileAccessed to true
	 */
	private static void processFile() {
		String inFileName = "";
		while (!inputValid) {
			System.out.println("Enter file name:");
			inFileName = scan.next();
			scan.nextLine();
			File f = new File(inFileName);
			if (f.exists())
				inputValid = true;
			else
				System.out.println("File does not exist.");
		}
		studentReport = new StudentReport(inFileName);
		fileAccessed = true;
		System.out.println("File accessed.");
		scanIn = 0;
	}
	
	/**
	 * Runs the second function of the program:
	 * Checks if a file has been accessed
	 * Runs the StudentReport.printStudentNames() method
	 */
	private static void printStudentNames() {
		if (fileAccessed)
			studentReport.printStudentNames();
		else
			System.out.println("File not chosen.");
	}
	
	/**
	 * Runs the third function of the program:
	 * Checks if a file has been accessed and takes the user input for the student whose file the user wants created
	 * Runs the StudentReport.getStudents() method
	 * Runs the StudentReport.writeFile() method
	 */
	private static void generateStudentReportCard() {
		if (fileAccessed) {
			String inFirstName;
			String inLastName;
			String inStudent = "";
			String[] students = studentReport.getStudents();
			int numStudents = studentReport.getNumStudents();
			while (!inputValid) {
				System.out.println("Enter student name (<first> <last>):");
				inFirstName = scan.next();
				inLastName = scan.next();
				scan.nextLine();
				inStudent = inFirstName + " " + inLastName;
				for (int i = 0; i < numStudents; i++) {
					if (inStudent.equals(students[i]))
						inputValid = true;
				}
				if (!inputValid)
					System.out.println("Student does not exist.");
			}
			studentReport.writeFile(inStudent);
			System.out.println("Report card generated.");
		}
		else
			System.out.println("File not chosen.");
	}
	
	/**
	 * Runs the fourth function of the program:
	 * Checks if a file has been accessed and prints out class statistics
	 * Runs the StudentReport.getClassAverage() method
	 * Runs the StudentReport.getNumFailing() method
	 * Runs the StudentReport.getBPercentage() method
	 */
	private static void printClassStats() {
		if (fileAccessed) {
			System.out.println("Class average: " + studentReport.getClassAverage());
			System.out.println("Number of students with a D or below: " + studentReport.getNumFailing());
			System.out.println("Percentage of students with a B: " + studentReport.getBPercentage() + "%");
		}
		else
			System.out.println("File not chosen.");
	}
	
	/**
	 * Runs the fifth function of the program:
	 * Exits the program
	 */
	private static void exitProgram() {
		scan.close();
		systemRunning = false;
		System.out.println("Have a nice day!");
	}
	
	/**
	 * Prints out the list of program functions for the user to chose from
	 */
	private static void printStartScreen() {
		System.out.println();
		System.out.println("Please select one of the following options by typing the corresponding number into the console:");
		System.out.println("1. Enter the file name to process");
		System.out.println("2. Print a list of all students");
		System.out.println("3. Generate a report card for a specific student");
		System.out.println("4. Display statistics of the class");
		System.out.println("5. Exit");
		System.out.println();
	}
}
