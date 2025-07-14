import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateStudent {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student_db";
        String user = "root";
        String pass = "Priya@1024";

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter roll number to update: ");
            String roll = scanner.nextLine();

            System.out.print("Enter new department: ");
            String dept = scanner.nextLine();

            System.out.print("Enter new marks: ");
            int marks = scanner.nextInt();

            Connection conn = DriverManager.getConnection(url, user, pass);

            String sql = "UPDATE students SET department = ?, marks = ? WHERE roll_no = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, dept);
            stmt.setInt(2, marks);
            stmt.setString(3, roll);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student updated successfully!");
            } else {
                System.out.println("⚠️ Roll number not found.");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
