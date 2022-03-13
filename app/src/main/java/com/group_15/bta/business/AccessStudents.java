package com.group_15.bta.business;

import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.StudentList;

import java.util.ArrayList;

public class AccessStudents implements StudentList {
    private static final AccessStudents ourInstance = new AccessStudents();
    public ArrayList<Student> studentList = new ArrayList<>();
    private AccessStudents() {
        Student one = new Student("12", "12", "Jane Doe");
        Student two = new Student("13", "13", "John Doe");
        this.studentList.add(one);
        this.studentList.add(two);
    }

    public static AccessStudents getInstance(){
        return ourInstance;
    }

    public Student getStudent(int position){
       return studentList.get(position);
    }

    public ArrayList<Student> getStudentList(){
        return this.studentList;
    }

    public void insertStudent(Student currentStudent){
        studentList.add(currentStudent);
    }

    public void deleteStudent(int position){
        studentList.remove(position);
    }
}