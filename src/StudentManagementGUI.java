import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Main GUI class for the Student Management System
public class StudentManagementGUI extends JFrame {
    private JTextField studentIdField, nameField, ageField, gradeField; // Input fields
    private JTextArea outputArea; // Output area for displaying messages and student records
    private StudentManagerImpl studentManager; // Instance of StudentManagerImpl for database operations

    // Constructor to initialize the GUI
    public StudentManagementGUI() {
        studentManager = new StudentManagerImpl();

        setTitle("Student Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Output Panel
        JPanel outputPanel = createOutputPanel();
        add(outputPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method to create the input panel with labels and text fields
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        panel.add(studentIdField);

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        panel.add(gradeField);

        return panel;
    }

    // Method to create the output panel with a scrollable text area
    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Method to create the button panel with action listeners
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout());

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        panel.add(addButton);

        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
        panel.add(removeButton);

        JButton updateButton = new JButton("Update Student");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        panel.add(updateButton);

        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });
        panel.add(displayButton);

        JButton averageButton = new JButton("Calculate Average");
        averageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverage();
            }
        });
        panel.add(averageButton);

        return panel;
    }

    // Method to handle adding a student
    private void addStudent() {
        try {
            String studentID = studentIdField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            // Input validation
            if (studentID == null || studentID.trim().isEmpty()) {
                outputArea.setText("Error: Student ID cannot be empty.");
                return;
            }
            if (!studentID.matches("\\d{5}")) {
                outputArea.setText("Error: Student ID must contain exactly 5 digits.");
                return;
            }
            if (name == null || name.trim().isEmpty()) {
                outputArea.setText("Error: Name cannot be empty.");
                return;
            }
            if (name.matches(".*\\d.*")) {
                outputArea.setText("Error: Name cannot contain numbers.");
                return;
            }
            if (age <= 0) {
                outputArea.setText("Error: Age must be positive.");
                return;
            }
            if (grade < 0.0 || grade > 100.0) {
                outputArea.setText("Error: Grade must be between 0.0 and 100.0.");
                return;
            }

            // Create a new Student object and add it to the database
            Student student = new Student(name, age, grade, studentID);
            studentManager.addStudent(student);
            outputArea.setText("Student added successfully.");
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Invalid input format.");
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    // Method to handle removing a student
    private void removeStudent() {
        try {
            String studentID = studentIdField.getText();
            if (studentID == null || studentID.trim().isEmpty()) {
                outputArea.setText("Error: Student ID cannot be empty.");
                return;
            }
            if (!studentID.matches("\\d{5}")) {
                outputArea.setText("Error: Student ID must contain exactly 5 digits.");
                return;
            }

            // Check if the student exists before attempting to remove
            List<Student> students = studentManager.displayAllStudents();
            boolean studentExists = false;
            for (Student student : students) {
                if (student.getStudentID().equals(studentID)) {
                    studentExists = true;
                    break;
                }
            }

            if (!studentExists) {
                outputArea.setText("Error: Student with ID " + studentID + " does not exist.");
                return;
            }

            // Remove the student from the database
            studentManager.removeStudent(studentID);
            outputArea.setText("Student removed successfully.");
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    // Method to handle updating a student
    private void updateStudent() {
        try {
            String studentID = studentIdField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            double grade = Double.parseDouble(gradeField.getText());

            // Input validation
            if (studentID == null || studentID.trim().isEmpty()) {
                outputArea.setText("Error: Student ID cannot be empty.");
                return;
            }
            if (!studentID.matches("\\d{5}")) {
                outputArea.setText("Error: Student ID must contain exactly 5 digits.");
                return;
            }
            if (name == null || name.trim().isEmpty()) {
                outputArea.setText("Error: Name cannot be empty.");
                return;
            }
            if (name.matches(".*\\d.*")) {
                outputArea.setText("Error: Name cannot contain numbers.");
                return;
            }
            if (age <= 0) {
                outputArea.setText("Error: Age must be positive.");
                return;
            }
            if (grade < 0.0 || grade > 100.0) {
                outputArea.setText("Error: Grade must be between 0.0 and 100.0.");
                return;
            }

            // Check if the student exists before attempting to update
            List<Student> students = studentManager.displayAllStudents();
            boolean studentExists = false;
            for (Student student : students) {
                if (student.getStudentID().equals(studentID)) {
                    studentExists = true;
                    break;
                }
            }

            if (!studentExists) {
                outputArea.setText("Error: Student with ID " + studentID + " does not exist.");
                return;
            }

            // Update the student's details in the database
            Student student = new Student(name, age, grade, studentID);
            studentManager.updateStudent(student);
            outputArea.setText("Student updated successfully.");
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Invalid input format.");
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    // Method to display all students in the output area
    private void displayAllStudents() {
        List<Student> students = studentManager.displayAllStudents();
        StringBuilder sb = new StringBuilder();
        for (Student student : students) {
            sb.append(student).append("\n");
        }
        outputArea.setText(sb.toString());
    }

    // Method to calculate and display the average grade of all students
    private void calculateAverage() {
        double average = studentManager.calculateAverageGrade();
        outputArea.setText("Average Grade: " + average);
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementGUI().setVisible(true);
            }
        });
    }
}