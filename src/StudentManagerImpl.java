import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implementation of the StudentManager interface
public class StudentManagerImpl implements StudentManager {
    private Connection connection; // Database connection object

    // Constructor to initialize the database connection and create the table if it doesn't exist
    public StudentManagerImpl() {
        connectToDatabase();
        createTableIfNotExists();
    }

    // Method to establish a connection to the SQLite database
    private void connectToDatabase() {
        try {
            // Connect to the SQLite database (creates the database if it doesn't exist)
            connection = DriverManager.getConnection("jdbc:sqlite:students.db");
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    // Method to create the 'students' table if it doesn't already exist
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "name TEXT, " +
                "age INTEGER, " +
                "grade REAL, " +
                "studentID TEXT PRIMARY KEY)";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql); // Execute the SQL statement to create the table
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    // Method to add a new student to the database
    @Override
    public void addStudent(Student student) {
        String checkSql = "SELECT studentID FROM students WHERE studentID = ?";
        String insertSql = "INSERT INTO students (name, age, grade, studentID) VALUES (?, ?, ?, ?)";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            // Check if the studentID already exists in the database
            checkStmt.setString(1, student.getStudentID());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // If the studentID exists, throw an exception
                throw new SQLException("Student ID already exists.");
            }

            // Insert the new student into the database
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                insertStmt.setString(1, student.getName());
                insertStmt.setInt(2, student.getAge());
                insertStmt.setDouble(3, student.getGrade());
                insertStmt.setString(4, student.getStudentID());
                insertStmt.executeUpdate();
                System.out.println("Student added successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            throw new RuntimeException("Error adding student: " + e.getMessage());
        }
    }

    // Method to remove a student from the database by their ID
    @Override
    public void removeStudent(String studentID) {
        String sql = "DELETE FROM students WHERE studentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, studentID);
            int rowsDeleted = pstmt.executeUpdate(); // Execute the delete operation
            if (rowsDeleted > 0) {
                System.out.println("Student removed successfully.");
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error removing student: " + e.getMessage());
        }
    }

    // Method to update an existing student's details in the database
    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setDouble(3, student.getGrade());
            pstmt.setString(4, student.getStudentID());
            int rowsUpdated = pstmt.executeUpdate(); // Execute the update operation
            if (rowsUpdated > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
    }

    // Method to retrieve and return a list of all students from the database
    @Override
    public List<Student> displayAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // Iterate through the result set and create Student objects
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double grade = rs.getDouble("grade");
                String studentID = rs.getString("studentID");
                students.add(new Student(name, age, grade, studentID));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    // Method to calculate and return the average grade of all students
    @Override
    public double calculateAverageGrade() {
        double average = 0.0;
        String sql = "SELECT AVG(grade) AS average FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                average = rs.getDouble("average"); // Retrieve the average grade from the result set
            }
        } catch (SQLException e) {
            System.err.println("Error calculating average grade: " + e.getMessage());
        }
        return average;
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}