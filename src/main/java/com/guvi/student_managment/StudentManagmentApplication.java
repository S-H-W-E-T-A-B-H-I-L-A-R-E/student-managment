package com.guvi.student_managment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class StudentManagmentApplication {
    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentService studentService =
            new StudentService();
	public static void main(String[] args) {

                studentService.loadFromfile();

                while (true) {

                    displayMenu();

                    int choice = readInt("Enter your choice: ");

                    switch (choice) {

                        case 1:
                            addStudent();
                            break;

                        case 2:
                            viewStudents();
                            break;

                        case 3:
                            searchStudent();
                            break;

                        case 4:
                            updateStudent();
                            break;

                        case 5:
                            deleteStudent();
                            break;

                        case 6:
                            System.out.println("Exiting application...");
                            scanner.close();
                            return;

                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            }

            // Display menu
            private static void displayMenu() {

                System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");

                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");

                System.out.println("====================================");
            }

            // Add student
            private static void addStudent() {

                System.out.println("\n--- Add Student ---");

                int id = readInt("Enter student ID: ");

                if (studentService.findStudentById(id) != null) {

                    System.out.println("Student ID already exists.");
                    return;
                }

                String name = readNonEmptyString("Enter student name: ");

                int age = readPositiveInt("Enter student age: ");

                String course = readNonEmptyString("Enter student course: ");

                Student student = new Student(id,age, name, course);

                if (studentService.addStudent(student)) {

                    System.out.println("Student added successfully.");

                } else {

                    System.out.println("Failed to add student.");
                }
            }

            // View students
            private static void viewStudents() {

                System.out.println("\n--- All Students ---");

                List<Student> students = studentService.getAllStudent();

                if (students.isEmpty()) {

                    System.out.println("No student records found.");
                    return;
                }

                for (Student student : students) {

                    System.out.println(student);
                }
            }

            // Search student
            private static void searchStudent() {

                System.out.println("\n--- Search Student ---");

                int id = readInt("Enter student ID: ");

                Student student = studentService.findStudentById(id);

                if (student != null) {

                    System.out.println("Student found:");
                    System.out.println(student);

                } else {

                    System.out.println("Student not found.");
                }
            }

            // Update student
            private static void updateStudent() {

                System.out.println("\n--- Update Student ---");

                int id = readInt("Enter student ID: ");

                Student existingStudent = studentService.findStudentById(id);

                if (existingStudent == null) {

                    System.out.println("Student not found.");
                    return;
                }

                String name = readNonEmptyString("Enter new name: ");

                int age = readPositiveInt("Enter new age: ");

                String course = readNonEmptyString("Enter new course: ");

                boolean updated = studentService.updateStudent(
                        id,age,name, course
                );

                if (updated) {

                    System.out.println("Student updated successfully.");

                } else {

                    System.out.println("Failed to update student.");
                }
            }

            // Delete student
            private static void deleteStudent() {

                System.out.println("\n--- Delete Student ---");

                int id = readInt("Enter student ID: ");

                Student student = studentService.findStudentById(id);

                if (student == null) {

                    System.out.println("Student not found.");
                    return;
                }

                System.out.println("Student to delete: " + student);

                String confirmation = readNonEmptyString(
                        "Confirm deletion (yes/no): "
                );

                if (!confirmation.equalsIgnoreCase("yes")) {

                    System.out.println("Deletion cancelled.");
                    return;
                }

                boolean deleted = studentService.deleteStudent(id);

                if (deleted) {

                    System.out.println("Student deleted successfully.");

                } else {

                    System.out.println("Failed to delete student.");
                }
            }

            // Read integer input safely
            private static int readInt(String message) {

                while (true) {

                    System.out.print(message);

                    String input = scanner.nextLine().trim();

                    try {

                        return Integer.parseInt(input);

                    } catch (NumberFormatException e) {

                        System.out.println("Please enter a valid integer.");
                    }
                }
            }

            // Read positive integer
            private static int readPositiveInt(String message) {

                while (true) {

                    int value = readInt(message);

                    if (value > 0) {
                        return value;
                    }

                    System.out.println("Value must be greater than zero.");
                }
            }

            // Read non-empty string
            private static String readNonEmptyString(String message) {

                while (true) {

                    System.out.print(message);

                    String input = scanner.nextLine().trim();

                    if (!input.isEmpty()) {
                        return input;
                    }

                    System.out.println("Input cannot be empty.");
                }
            }
        }
