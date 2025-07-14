import java.sql.*;
import java.util.Scanner;

public class SMS {
    static final String URL = "jdbc:mysql://localhost:3306/student_db";
    static final String USER = "root";
    static final String PASS = "Priya@1024";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n📚 Student Management System");
            System.out.println("1. View all students");
            System.out.println("2. Add student");
            System.out.println("3. Update student");
            System.out.println("4. Delete student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // ✅ This line is important!


            switch (choice) {
                case 1 -> viewStudents();
                case 2 -> addStudent(scanner);
                case 3 -> updateStudent(scanner);
                case 4 -> deleteStudent(scanner);
                case 5 -> {
                    System.out.println("👋 Exiting program. Bye Priyaaa!");
                    return;
                }
                default -> System.out.println("⚠️ Invalid choice. Try again.");
            }
        }
    }

    static void viewStudents() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            System.out.println("---------------------------------------------------------------");
            System.out.printf("| %-3s | %-15s | %-10s | %-10s | %-5s |\n", "ID", "Name", "Roll No", "Dept", "Marks");
            System.out.println("---------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("| %-3d | %-15s | %-10s | %-10s | %-5d |\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("department"),
                        rs.getInt("marks"));
            }

        } catch (Exception e) {
            System.out.println("❌ Error viewing students: " + e.getMessage());
        }
    }

    static void addStudent(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter roll number: ");
            String roll = scanner.nextLine();
            System.out.print("Enter department: ");
            String dept = scanner.nextLine();
            System.out.print("Enter marks: ");
            int marks = scanner.nextInt();
            scanner.nextLine(); // consume newline

            String sql = "INSERT INTO students (name, roll_no, department, marks) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, roll);
            stmt.setString(3, dept);
            stmt.setInt(4, marks);

            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Student added!" : "⚠️ Failed to add.");

        } catch (Exception e) {
            System.out.println("❌ Error adding student: " + e.getMessage());
        }
    }

    static void updateStudent(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.print("Enter roll number to update: ");
            String roll = scanner.nextLine();
            System.out.print("Enter new department: ");
            String dept = scanner.nextLine();
            System.out.print("Enter new marks: ");
            int marks = scanner.nextInt();
            scanner.nextLine(); // consume newline

            String sql = "UPDATE students SET department = ?, marks = ? WHERE roll_no = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dept);
            stmt.setInt(2, marks);
            stmt.setString(3, roll);

            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Student updated!" : "⚠️ Roll not found.");

        } catch (Exception e) {
            System.out.println("❌ Error updating student: " + e.getMessage());
        }
    }

    static void deleteStudent(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.print("Enter roll number to delete: ");
            String roll = scanner.nextLine();

            String sql = "DELETE FROM students WHERE roll_no = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, roll);

            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "🗑️ Student deleted!" : "⚠️ Roll not found.");

        } catch (Exception e) {
            System.out.println("❌ Error deleting student: " + e.getMessage());
        }
    }
}
