import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertStudent {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student_db";
        String user = "root";
        String pass = "Priya@1024"; // if you set a password, put it here

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            System.out.print("Enter roll number: ");
            String roll = scanner.nextLine();

            System.out.print("Enter department: ");
            String dept = scanner.nextLine();

            System.out.print("Enter marks: ");
            int marks = scanner.nextInt();

            // 1. Connect
            Connection conn = DriverManager.getConnection(url, user, pass);

            // 2. Insert Query
            String sql = "INSERT INTO students (name, roll_no, department, marks) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 3. Set values
            stmt.setString(1, name);
            stmt.setString(2, roll);
            stmt.setString(3, dept);
            stmt.setInt(4, marks);

            // 4. Execute
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student inserted successfully!");
            } else {
                System.out.println("⚠️ Something went wrong.");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
