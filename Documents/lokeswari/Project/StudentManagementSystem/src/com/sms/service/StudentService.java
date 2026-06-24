package com.sms.service;

import com.sms.exception.DuplicateRecordException;
import com.sms.exception.InvalidInputException;
import com.sms.exception.RecordNotFoundException;
import com.sms.model.Student;
import com.sms.util.FileUtil;
import com.sms.util.Validator;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service layer for all Student CRUD operations.
 */
public class StudentService {

    private List<Student> students = new ArrayList<>();

    public StudentService() {
        loadFromFile();
    }

    // ───────────────────────── CREATE ─────────────────────────

    public void addStudent(Student student)
            throws DuplicateRecordException, InvalidInputException {

        Validator.validateName(student.getName());
        Validator.validateEmail(student.getEmail());
        Validator.validateAge(student.getAge());
        Validator.validateGpa(student.getGpa());
        Validator.validateYearLevel(student.getYearLevel());

        if (findById(student.getId()) != null) {
            throw new DuplicateRecordException(
                    "Student with ID " + student.getId() + " already exists.");
        }

        students.add(student);
        saveToFile();
        System.out.println("✅ Student added successfully.");
    }

    // ───────────────────────── READ ─────────────────────────

    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(students);
    }

    public Student getStudentById(String id) throws RecordNotFoundException {
        Student s = findById(id);
        if (s == null) throw new RecordNotFoundException(
                "No student found with ID: " + id);
        return s;
    }

    public List<Student> searchByName(String keyword) {
        String kw = keyword.toLowerCase();
        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByCourse(String course) {
        return students.stream()
                .filter(s -> s.getCourse().equalsIgnoreCase(course))
                .collect(Collectors.toList());
    }

    // ───────────────────────── UPDATE ─────────────────────────

    public void updateStudent(String id, String name, String email,
                              int age, String course, double gpa, int yearLevel)
            throws RecordNotFoundException, InvalidInputException {

        Student student = getStudentById(id);

        Validator.validateName(name);
        Validator.validateEmail(email);
        Validator.validateAge(age);
        Validator.validateGpa(gpa);
        Validator.validateYearLevel(yearLevel);

        student.setName(name);
        student.setEmail(email);
        student.setAge(age);
        student.setCourse(course);
        student.setGpa(gpa);
        student.setYearLevel(yearLevel);

        saveToFile();
        System.out.println("✅ Student updated successfully.");
    }

    // ───────────────────────── DELETE ─────────────────────────

    public void deleteStudent(String id) throws RecordNotFoundException {
        Student student = getStudentById(id);
        students.remove(student);
        saveToFile();
        System.out.println("✅ Student deleted successfully.");
    }

    // ───────────────────────── HELPERS ─────────────────────────

    private Student findById(String id) {
        return students.stream()
                .filter(s -> s.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    private void saveToFile() {
        try {
            FileUtil.saveStudents(students);
        } catch (IOException e) {
            System.err.println("⚠️  Warning: Could not save students to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try {
            students = FileUtil.loadStudents();
        } catch (IOException e) {
            System.err.println("⚠️  Warning: Could not load students from file: " + e.getMessage());
        }
    }

    public int getTotalCount() { return students.size(); }
}
