package com.sms.main;

import com.sms.exception.DuplicateRecordException;
import com.sms.exception.InvalidInputException;
import com.sms.exception.RecordNotFoundException;
import com.sms.model.Student;
import com.sms.model.Teacher;
import com.sms.service.StudentService;
import com.sms.service.TeacherService;

import java.util.List;
import java.util.Scanner;

/**
 * Entry point — Console-based Student Management System.
 *
 * Features:
 *  - OOP  : Abstraction, Inheritance, Polymorphism (Person → Student / Teacher)
 *  - CRUD : Add / View / Update / Delete for Students & Teachers
 *  - File I/O : Persistent CSV storage via FileUtil
 *  - Exceptions : Custom checked exceptions for bad input, duplicates, not-found
 */
public class Main {

    private static final StudentService studentService = new StudentService();
    private static final TeacherService teacherService = new TeacherService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║    STUDENT MANAGEMENT SYSTEM  v1.0       ║");
        System.out.println("╚══════════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> teacherMenu();
                case 3 -> printSummary();
                case 0 -> { running = false; System.out.println("\n👋 Goodbye!"); }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    // ─────────────────── MAIN MENU ───────────────────

    private static void printMainMenu() {
        System.out.println("\n┌─────────────────────────┐");
        System.out.println("│       MAIN MENU         │");
        System.out.println("├─────────────────────────┤");
        System.out.println("│  1. Student Management  │");
        System.out.println("│  2. Teacher Management  │");
        System.out.println("│  3. Summary             │");
        System.out.println("│  0. Exit                │");
        System.out.println("└─────────────────────────┘");
    }

    // ─────────────────── STUDENT MENU ───────────────────

    private static void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n── Student Management ──");
            System.out.println("  1. Add Student");
            System.out.println("  2. View All Students");
            System.out.println("  3. Search Student by ID");
            System.out.println("  4. Search Student by Name");
            System.out.println("  5. Update Student");
            System.out.println("  6. Delete Student");
            System.out.println("  0. Back");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudentById();
                case 4 -> searchStudentByName();
                case 5 -> updateStudent();
                case 6 -> deleteStudent();
                case 0 -> back = true;
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void addStudent() {
        System.out.println("\n── Add New Student ──");
        try {
            String id        = readString("Student ID   : ");
            String name      = readString("Name         : ");
            String email     = readString("Email        : ");
            int    age       = readInt("Age          : ");
            String course    = readString("Course       : ");
            double gpa       = readDouble("GPA (0-4.0)  : ");
            int    yearLevel = readInt("Year Level   : ");

            Student student = new Student(id, name, email, age, course, gpa, yearLevel);
            studentService.addStudent(student);

        } catch (DuplicateRecordException | InvalidInputException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void viewAllStudents() {
        List<Student> list = studentService.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("⚠️  No students found.");
            return;
        }
        System.out.println("\n── All Students (" + list.size() + ") ──");
        list.forEach(System.out::println);
    }

    private static void searchStudentById() {
        String id = readString("Enter Student ID: ");
        try {
            System.out.println(studentService.getStudentById(id));
        } catch (RecordNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void searchStudentByName() {
        String keyword = readString("Enter name keyword: ");
        List<Student> results = studentService.searchByName(keyword);
        if (results.isEmpty()) System.out.println("No students matched.");
        else results.forEach(System.out::println);
    }

    private static void updateStudent() {
        String id = readString("Enter Student ID to update: ");
        try {
            studentService.getStudentById(id); // verify exists first
            System.out.println("Enter new details (leave blank to keep existing):");
            String name      = readString("Name         : ");
            String email     = readString("Email        : ");
            int    age       = readInt("Age          : ");
            String course    = readString("Course       : ");
            double gpa       = readDouble("GPA (0-4.0)  : ");
            int    yearLevel = readInt("Year Level   : ");
            studentService.updateStudent(id, name, email, age, course, gpa, yearLevel);
        } catch (RecordNotFoundException | InvalidInputException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void deleteStudent() {
        String id = readString("Enter Student ID to delete: ");
        System.out.print("Are you sure? (yes/no): ");
        if ("yes".equalsIgnoreCase(scanner.nextLine().trim())) {
            try {
                studentService.deleteStudent(id);
            } catch (RecordNotFoundException e) {
                System.out.println("❌ " + e.getMessage());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    // ─────────────────── TEACHER MENU ───────────────────

    private static void teacherMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n── Teacher Management ──");
            System.out.println("  1. Add Teacher");
            System.out.println("  2. View All Teachers");
            System.out.println("  3. Search Teacher by ID");
            System.out.println("  4. Update Teacher");
            System.out.println("  5. Delete Teacher");
            System.out.println("  0. Back");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addTeacher();
                case 2 -> viewAllTeachers();
                case 3 -> searchTeacherById();
                case 4 -> updateTeacher();
                case 5 -> deleteTeacher();
                case 0 -> back = true;
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void addTeacher() {
        System.out.println("\n── Add New Teacher ──");
        try {
            String id         = readString("Teacher ID   : ");
            String name       = readString("Name         : ");
            String email      = readString("Email        : ");
            int    age        = readInt("Age          : ");
            String department = readString("Department   : ");
            String subject    = readString("Subject      : ");

            Teacher teacher = new Teacher(id, name, email, age, department, subject);
            teacherService.addTeacher(teacher);

        } catch (DuplicateRecordException | InvalidInputException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void viewAllTeachers() {
        List<Teacher> list = teacherService.getAllTeachers();
        if (list.isEmpty()) {
            System.out.println("⚠️  No teachers found.");
            return;
        }
        System.out.println("\n── All Teachers (" + list.size() + ") ──");
        list.forEach(System.out::println);
    }

    private static void searchTeacherById() {
        String id = readString("Enter Teacher ID: ");
        try {
            System.out.println(teacherService.getTeacherById(id));
        } catch (RecordNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void updateTeacher() {
        String id = readString("Enter Teacher ID to update: ");
        try {
            teacherService.getTeacherById(id);
            String name       = readString("Name         : ");
            String email      = readString("Email        : ");
            int    age        = readInt("Age          : ");
            String department = readString("Department   : ");
            String subject    = readString("Subject      : ");
            teacherService.updateTeacher(id, name, email, age, department, subject);
        } catch (RecordNotFoundException | InvalidInputException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void deleteTeacher() {
        String id = readString("Enter Teacher ID to delete: ");
        System.out.print("Are you sure? (yes/no): ");
        if ("yes".equalsIgnoreCase(scanner.nextLine().trim())) {
            try {
                teacherService.deleteTeacher(id);
            } catch (RecordNotFoundException e) {
                System.out.println("❌ " + e.getMessage());
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    // ─────────────────── SUMMARY ───────────────────

    private static void printSummary() {
        System.out.println("\n── System Summary ──");
        System.out.println("  Total Students : " + studentService.getTotalCount());
        System.out.println("  Total Teachers : " + teacherService.getTotalCount());
    }

    // ─────────────────── INPUT HELPERS ───────────────────

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid number.");
            }
        }
    }
}
