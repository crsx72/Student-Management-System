Student Management System (SMS)

Overview

The Student Management System (SMS) is a Java-based application designed to manage student records through a graphical user interface (GUI).
The system allows users to perform various operations such as adding, removing, updating, and displaying student information.
It also connects to an SQLite database for persistent storage of student data.

Features

    Student Management: Add, remove, update, and display student records.
    Database Integration: Uses SQLite for storing student information.
    Graphical User Interface: Built using Java Swing for easy interaction.
    Input Validation: Ensures that user inputs are valid (e.g., age must be positive, grade must be between 0.0 and 100.0).
    Average Grade Calculation: Computes and displays the average grade of all students.

Requirements

    Java Development Kit (JDK): Version 21 or higher.

Setup Instructions

    Compilation:
        
        The application is already compiled into a jar file in "out/artifacts/Student_Management_System_jar/Student Management System.jar"
        It can be run with "java -jar Student Management System.jar"
        If you want to compıle yourself make sure SQLite JDBC Driver is installed in your system.
        Than the Student Management System can be compıled with the command "javac src/*.class"

    Database Setup:

        The application will automatically create an SQLite database named students.db in the project directory if it does not already exist.

Usage

Adding a Student

    Enter the student's details in the input fields:

        Student ID: Must be exactly 5 digits.

        Name: Cannot be empty or contain numbers.

        Age: Must be a positive integer.

        Grade: Must be between 0.0 and 100.0.

    Click the "Add Student" button.

Removing a Student

    Enter the student's ID in the Student ID field.

    Click the "Remove Student" button.

Updating a Student

    Enter the student's ID in the Student ID field.

    Update the student's details in the respective fields.

    Click the "Update Student" button.

Displaying All Students

    Click the "Display All Students" button to view all student records in the output area.

Calculating Average Grade

    Click the "Calculate Average" button to compute and display the average grade of all students.

Code Structure

    Student.java: Defines the Student class with attributes and methods for managing student data.

    StudentManager.java: Interface defining the methods for managing student records.

    StudentManagerImpl.java: Implements the StudentManager interface and handles database operations.

    StudentManagementGUI.java: Contains the GUI implementation and event handling for user interactions.

Exception Handling

The application includes robust exception handling to manage:

    SQL Exceptions: Errors during database interactions (e.g., connection failures, SQL syntax errors).

    Input Validation Errors: Ensures that users cannot input invalid data (e.g., non-numeric values for age or grade).

    Logical Errors: Prevents operations on non-existent students (e.g., updating or removing a student that does not exist).

Documentation

    Inline Comments: The code includes inline comments to explain key sections and logic.

    README: This file provides an overview of the application, setup instructions, and usage guidelines.

Final Deliverables

    A fully functional Java application that meets the specified requirements.

    Source code with clear organization and documentation.

    A working SQLite database with student records.
# Student-Management-System
