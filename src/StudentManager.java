import java.util.List;

// Interface defining the methods for managing student records
public interface StudentManager {
    // Method to add a new student
    void addStudent(Student student);

    // Method to remove a student by their ID
    void removeStudent(String studentID);

    // Method to update an existing student's details
    void updateStudent(Student student);

    // Method to retrieve and return a list of all students
    List<Student> displayAllStudents();

    // Method to calculate and return the average grade of all students
    double calculateAverageGrade();
}