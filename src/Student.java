public class Student {
    private String name;
    private int age;
    private double grade;
    private String studentID;

    // Constructor to initialize the Student object with name, age, grade, and studentID
    public Student(String name, int age, double grade, String studentID) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.studentID = studentID;
    }

    // Getter and Setter methods for each attribute

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Override toString() method to provide a formatted string representation of the Student object
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Age: %d, Grade: %.2f", studentID, name, age, grade);
    }
}