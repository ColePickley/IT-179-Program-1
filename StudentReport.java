/*
 * Created on: August 28, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Stores and outputs student/class data
 * 
 * @author Cole Pickley
 */
public class StudentReport {
	private String fileName;
	private int numStudents;
	private double[][] grades;
	private String[] students;
	private String[] evaluatedItems;
	
	/**
	 * Constructor for StudentReport class
	 * 
	 * Sets fileName and numStudents
	 * 
	 * Runs the following local methods:
	 * getNumStudents(), setStudents(), setEvaluatedItems(), and readFile()
	 * 
	 * @param fileName
	 */
	public StudentReport(String fileName) {
		this.fileName = fileName;
		students = new String[100];
		numStudents = 0;
		this.setStudents();
		this.setEvaluatedItems();
		this.readFile();
	}
	
	/**
	 * Sets students
	 */
	public void setStudents() {
		try {
			Scanner scan = new Scanner(new File(fileName));
			scan.useDelimiter(",");
			scan.nextLine();
			while (scan.hasNext()) {
				students[numStudents] = scan.next();
				scan.nextLine();
				numStudents++;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets evaluatedItems
	 */
	public void setEvaluatedItems() {
		evaluatedItems = new String[8];
		try {
			Scanner scan = new Scanner(new File(fileName));
			scan.useDelimiter(",");
			scan.next();
			for (int i = 0; i < evaluatedItems.length - 1; i++) {
				evaluatedItems[i] = scan.next();
			}
			scan.skip(",");
			evaluatedItems[evaluatedItems.length - 1] = scan.nextLine();
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns students
	 * 
	 * @return String[]
	 */
	public String[] getStudents() {
		return students;
	}
	
	/**
	 * Returns numStudents
	 * 
	 * @return int
	 */
	public int getNumStudents() {
		return numStudents;
	}
	
	/**
	 * Returns the index of a specific student within the students array
	 * 
	 * @param studentName
	 * @return int
	 */
	public int getStudentIndex(String studentName) {
		for (int i = 0; i < numStudents; i++) {
			if (students[i].equals(studentName))
				return i;
		}
		return -1;
	}
	
	/**
	 * Returns the total grade (out of 100) that the student at the given index received
	 * 
	 * @param studentIndex
	 * @return double
	 */
	public double getTotal(int studentIndex) {
		double total = 0;
		for (int i = 0; i < grades[0].length; i++) {
			total += grades[studentIndex][i];
		}
		return total;
	}
	
	/**
	 * Returns the respective letter grade received based on the student's total grade
	 * 
	 * @param total
	 * @return String
	 */
	public String getGrade(double total) {
		if (total >= 90)
			return "A";
		else if (total >= 80)
			return "B";
		else if (total >= 70)
			return "C";
		else if (total >= 60)
			return "D";
		return "F";
	}
	
	/**
	 * Returns the average total grade of the class rounded to the nearest hundredth
	 * Runs the local method getTotal(int studentIndex)
	 * 
	 * @return double
	 */
	public double getClassAverage() {
		double classTotal = 0;
		for (int i = 0; i < numStudents; i++) {
			classTotal += this.getTotal(i);
		}
		return Math.round(classTotal * 100.0 / numStudents) / 100.0;
	}
	
	/**
	 * Returns the number of students with a D grade or below
	 * Runs the local method getTotal(int studentIndex)
	 * 
	 * @return int
	 */
	public int getNumFailing() {
		int numFailing = 0;
		for (int i = 0; i < numStudents; i++) {
			if (this.getTotal(i) < 70)
				numFailing++;
		}
		return numFailing;
	}
	
	/**
	 * Returns the percentage of students within the class that received a B grade rounded to the nearest hundredth
	 * 
	 * @return double
	 */
	public double getBPercentage() {
		int numBs = 0;
		for (int i = 0; i < numStudents; i++) {
			if (this.getTotal(i) >= 80 && this.getTotal(i) < 90)
				numBs++;
		}
		return Math.round((numBs / (double) numStudents) * 10000.0) / 100.0;
	}
	
	/**
	 * Prints out a list of names of all the students in the class
	 */
	public void printStudentNames() {
		for (int i = 0; i < numStudents; i++) {
			System.out.println(students[i]);
		}
	}
	
	/**
	 * Creates a report card text file for the given student
	 * 
	 * @param studentName
	 */
	public void writeFile(String studentName) {
		try {
			FileWriter fw = new FileWriter(studentName + ".txt", false);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("Student Name: " + studentName);
			int studentIndex = this.getStudentIndex(studentName);
			for (int i = 0; i < evaluatedItems.length; i++) {
				pw.println(evaluatedItems[i] + ": " + grades[studentIndex][i]);
			}
			double total = this.getTotal(studentIndex);
			pw.println("Total: " + total);
			pw.println("Grade: " + this.getGrade(total));
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets grades
	 */
	public void readFile() {
		try {
			Scanner scan = new Scanner(new File(fileName));
			scan.useDelimiter(",");
			grades = new double[numStudents][evaluatedItems.length];
			scan.nextLine();
			for (int i = 0; i < grades.length; i++) {
				scan.next();
				for (int j = 0; j < grades[0].length - 1; j++) {
					grades[i][j] = scan.nextDouble();
				}
				scan.skip(",");
				grades[i][grades[0].length - 1] = Double.parseDouble(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
