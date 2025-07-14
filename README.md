# 🎓 Student Management System (Java + MySQL)

A simple **Java console application** that connects to **MySQL** using **JDBC** to manage student records.

---

## 📌 Features

- 📋 View all students
- ➕ Add new student
- 🔁 Update student department or marks
- 🗑️ Delete student by roll number
- 🎯 Menu-driven user interface

---

## 🧱 Technologies Used

| Tech           | Purpose                         |
|----------------|---------------------------------|
| Java           | Core logic & user interaction   |
| MySQL          | Relational database             |
| JDBC           | Java-MySQL database connection  |
| Git + GitHub   | Version control & hosting       |
| VS Code        | IDE used to build the project   |

---

## 🛠️ How to Run

1. Clone the repository  
git clone https://github.com/priyanjali1024/StudentManagementSystem.git
cd StudentManagementSystem

2. Compile the app  
    javac -cp ".;mysql-connector-j-9.3.0.jar" SMS.java

3. Run the app  
    java -cp ".;mysql-connector-j-9.3.0.jar" SMS


4. Make sure MySQL is running and `student_db` database is created with a `students` table:
```sql
CREATE DATABASE student_db;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    roll_no VARCHAR(20),
    department VARCHAR(50),
    marks INT
);

🤓 What I Learned
    1.How to connect Java to MySQL using JDBC

    2.Writing parameterized SQL queries using PreparedStatement

    3.Creating a clean console-based user interface

    4.Version control using Git & GitHub

👩‍💻 Author
Priyadharshini
B.Tech ECE Student | Java & Python Learner