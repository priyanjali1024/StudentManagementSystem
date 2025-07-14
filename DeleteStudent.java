import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteStudent {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student_db";
        String user = "root";
        String pass = "Priya@1024";

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter roll number to delete: ");
            String roll = scanner.nextLine();

            Connection conn = DriverManager.getConnection(url, user, pass);

            String sql = "DELETE FROM students WHERE roll_no = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, roll);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("🗑️ Student deleted successfully!");
            } else {
                System.out.println("⚠️ Roll number not found.");
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
