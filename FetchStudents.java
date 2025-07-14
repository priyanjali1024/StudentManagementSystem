import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FetchStudents {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student_db";  // your DB name
        String user = "root";                                    // your MySQL username
        String pass = "Priya@1024";                                        // your password if any

        try {
            // Connect to DB
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Connected to database.\n");

            // Create SQL statement and execute
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(query);

            // Print table header
            System.out.println("🎓 Student Records:");
            System.out.println("---------------------------------------------------------------");
            System.out.printf("| %-3s | %-15s | %-10s | %-10s | %-5s |\n", "ID", "Name", "Roll No", "Dept", "Marks");
            System.out.println("---------------------------------------------------------------");
            // Loop through results
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String roll = rs.getString("roll_no");
                String dept = rs.getString("department");
                int marks = rs.getInt("marks");

                System.out.printf("| %-3d | %-15s | %-10s | %-10s | %-5d |\n", id, name, roll, dept, marks);
            }

            // Close connection
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
