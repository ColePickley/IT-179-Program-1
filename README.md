# IT-179-Program-1
Student Filing System
IT-179
Program 1
You will write a program that will read and process a .csv file that holds the grades of all students in a class. (See Attached file).
Your program should follow the below requirements:
  1. Classes in the package ilstu.edu:
    • MainClass:
        Will have the main method:
        i. It will control the flow of your program.
        ii. It will prompt the user to select one of the following options (these options will keep being displayed after each selection until we exit the program):
          1. Enter the file name to process.
          2. Print a list of all students.
            a. This will print out all the student names to the console.
          3. Generate a report card for a specific student.
            a. If selected it will ask the user to enter the name of the student
            b. It will then call the writeFile method to generate the report card.
          4. Display statistics of the class.
            a. It will print out the average of the class, the number of students who received a D grade or below and the percentage of students who got a B (i.e 20% of students got a B)
          5. Exit
    • StudentReport
       i. In your class you will have the following instance variables:
          1. fileName: a String that will hold the name of the file we want to read.
          2. grades: a double 2d array that will hold the grades for all the students.
          3. students: a 1d String array that will hold the names of all the students.
          4. evaluatedItems: a 1d String array that will hold the names of all the graded items in the file.
       ii. In your class you will have the following methods:
          1. A constructor that will take the file name and instantiate the arrays.
          2. readFile:
            • it will read the .csv file and store the values in the arrays we have. -You are only allowed to use the reading technique shown in reading structured files video here:
              https://youtu.be/AFYfkKZ6pHU?si=IUEoRZgiIK4-bncK
          3. writeFile:
            • it will generate a report card for the selected student and save it
            as a .txt file. The name of the file should be the student’s name.
            • Your report file could have any formatting, but it should include
            the following:
              i. The student’s name.
              ii. Their grade in every exam, assignment with the appropriate label. For example: Exam one: 50.
              iii. Their Total.
              iv. Their letter grades. (90 and above A, 80-89 B,70-79 C, 60-69 D, below 60 F)
          4. You can add any helper methods and variables you find suitable.
2. Design requirements:
  • Follow all the coding conventions used in IT-168 including but not limited to: naming
  conventions, comments, ...etc
  • The csv file should be placed directly inside your project. The path of the file in your
  code should only be the file name.
  • Your program should work for any number of students we might have in the file up to
  100 students.
  • The number of graded items is always the same. The name of these items could be
  different from one file to another. The sum of the graded items is out of 100.
3. Grading criteria:
  • MainClass(30%)
  • StudentReport(70%)
    i. Variables (5%)
    ii. Constructor (10%)
    iii. Reading the file properly and storing the data in the arrays (25%)
    iv. Displaying the output to console and writing the output file properly (30%)
      • Deductions
      • Late (-10% per calendar day)
      • (-40) Syntax Errors
      • (-30) Runtime Errors
      • (-10) Style and Organization
