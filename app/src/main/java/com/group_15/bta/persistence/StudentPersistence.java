package com.group_15.bta.persistence;

import java.util.ArrayList;

import com.group_15.bta.business.AccessStudents;
import com.group_15.bta.objects.Student;

public interface StudentPersistence {
    static AccessStudents getInstance() {
        return null;
    }

    ArrayList<Student> getStudentList();

    Student getStudent(int position);

    void insertStudent(Student currentStudent);

    void deleteStudent(int position);
}