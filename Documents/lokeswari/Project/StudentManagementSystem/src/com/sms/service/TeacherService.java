package com.sms.service;

import com.sms.exception.DuplicateRecordException;
import com.sms.exception.InvalidInputException;
import com.sms.exception.RecordNotFoundException;
import com.sms.model.Teacher;
import com.sms.util.FileUtil;
import com.sms.util.Validator;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service layer for all Teacher CRUD operations.
 */
public class TeacherService {

    private List<Teacher> teachers = new ArrayList<>();

    public TeacherService() {
        loadFromFile();
    }

    // ───────────────────────── CREATE ─────────────────────────

    public void addTeacher(Teacher teacher)
            throws DuplicateRecordException, InvalidInputException {

        Validator.validateName(teacher.getName());
        Validator.validateEmail(teacher.getEmail());
        Validator.validateAge(teacher.getAge());

        if (findById(teacher.getId()) != null) {
            throw new DuplicateRecordException(
                    "Teacher with ID " + teacher.getId() + " already exists.");
        }

        teachers.add(teacher);
        saveToFile();
        System.out.println("✅ Teacher added successfully.");
    }

    // ───────────────────────── READ ─────────────────────────

    public List<Teacher> getAllTeachers() {
        return Collections.unmodifiableList(teachers);
    }

    public Teacher getTeacherById(String id) throws RecordNotFoundException {
        Teacher t = findById(id);
        if (t == null) throw new RecordNotFoundException(
                "No teacher found with ID: " + id);
        return t;
    }

    public List<Teacher> searchByName(String keyword) {
        String kw = keyword.toLowerCase();
        return teachers.stream()
                .filter(t -> t.getName().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    public List<Teacher> getByDepartment(String department) {
        return teachers.stream()
                .filter(t -> t.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    // ───────────────────────── UPDATE ─────────────────────────

    public void updateTeacher(String id, String name, String email,
                              int age, String department, String subject)
            throws RecordNotFoundException, InvalidInputException {

        Teacher teacher = getTeacherById(id);

        Validator.validateName(name);
        Validator.validateEmail(email);
        Validator.validateAge(age);

        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setAge(age);
        teacher.setDepartment(department);
        teacher.setSubject(subject);

        saveToFile();
        System.out.println("✅ Teacher updated successfully.");
    }

    // ───────────────────────── DELETE ─────────────────────────

    public void deleteTeacher(String id) throws RecordNotFoundException {
        Teacher teacher = getTeacherById(id);
        teachers.remove(teacher);
        saveToFile();
        System.out.println("✅ Teacher deleted successfully.");
    }

    // ───────────────────────── HELPERS ─────────────────────────

    private Teacher findById(String id) {
        return teachers.stream()
                .filter(t -> t.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    private void saveToFile() {
        try {
            FileUtil.saveTeachers(teachers);
        } catch (IOException e) {
            System.err.println("⚠️  Warning: Could not save teachers to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try {
            teachers = FileUtil.loadTeachers();
        } catch (IOException e) {
            System.err.println("⚠️  Warning: Could not load teachers from file: " + e.getMessage());
        }
    }

    public int getTotalCount() { return teachers.size(); }
}
