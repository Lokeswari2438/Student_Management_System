package com.sms.util;

import com.sms.model.Student;
import com.sms.model.Teacher;

import java.io.*;
import java.util.*;

/**
 * Handles all File I/O — saves and loads Student and Teacher records as CSV.
 */
public class FileUtil {

    private static final String STUDENTS_FILE = "data/students.csv";
    private static final String TEACHERS_FILE = "data/teachers.csv";

    // ───────────────────────── Student I/O ─────────────────────────

    public static void saveStudents(List<Student> students) throws IOException {
        ensureDataDirectory();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student s : students) {
                writer.write(s.toCSV());
                writer.newLine();
            }
        }
    }

    public static List<Student> loadStudents() throws IOException {
        List<Student> students = new ArrayList<>();
        File file = new File(STUDENTS_FILE);
        if (!file.exists()) return students;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    students.add(Student.fromCSV(line.trim()));
                }
            }
        }
        return students;
    }

    // ───────────────────────── Teacher I/O ─────────────────────────

    public static void saveTeachers(List<Teacher> teachers) throws IOException {
        ensureDataDirectory();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEACHERS_FILE))) {
            for (Teacher t : teachers) {
                writer.write(t.toCSV());
                writer.newLine();
            }
        }
    }

    public static List<Teacher> loadTeachers() throws IOException {
        List<Teacher> teachers = new ArrayList<>();
        File file = new File(TEACHERS_FILE);
        if (!file.exists()) return teachers;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    teachers.add(Teacher.fromCSV(line.trim()));
                }
            }
        }
        return teachers;
    }

    private static void ensureDataDirectory() {
        File dir = new File("data");
        if (!dir.exists()) dir.mkdirs();
    }
}
