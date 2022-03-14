package com.group_15.bta.persistence.HSQLDB;

import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;
import com.group_15.bta.persistence.StudentPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentPersistenceHSQLDB implements StudentPersistence {
    private String dbPath;

    public StudentPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Student fromResultSet(final ResultSet rs) throws SQLException {
        final String userName = rs.getString("STUDENTID");
        final String password = rs.getString("PASSWORD");
        final String name = rs.getString("NAME");
        return new Student(userName, password, name);
    }

    @Override
    public ArrayList<Student> getStudentList() {
        ArrayList<Student> students = new ArrayList<>();

        try (Connection newConnection = connection()) {
            final Statement statement = newConnection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENTS");
            while (resultSet.next()) {
                final Student student = fromResultSet(resultSet);
                students.add(student);
            }
            statement.close();
            resultSet.close();
        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }

        return students;
    }


    @Override
    public void insertStudent(Student currentStudent) {

        try (final Connection newConnection = connection()) {
            final PreparedStatement statement = newConnection.prepareStatement("INSERT INTO STUDENTS VALUES(?, ?, ?)");
            statement.setString(1, currentStudent.getStudentID());
            statement.setString(2, currentStudent.getPassword());
            statement.setString(3, currentStudent.getID());
            statement.executeUpdate();

            StudentSectionPersistenceHSQLDB studentSectionInserter = new StudentSectionPersistenceHSQLDB(dbPath);
            ArrayList<StudentSection> sections = studentSectionInserter.getSectionList();
            for (int i = 0; i < sections.size(); i++) {
                if (sections.get(i).getAssociatedStudent().equals(currentStudent.getStudentID())) {
                    studentSectionInserter.insertSection(sections.get(i));
                }
            }

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

    @Override
    public void deleteStudent(Student toRemove) {
        try (final Connection newConnection = connection()) {
            PreparedStatement statement = newConnection.prepareStatement("DELETE FROM STUDENTSECTIONS WHERE STUDENTID = ?");
            statement.setString(1, toRemove.getStudentID());
            statement.executeUpdate();
            statement = newConnection.prepareStatement("DELETE FROM STUDENTS WHERE STUDENTID = ?");
            statement.setString(1, toRemove.getStudentID());
            statement.executeUpdate();

        } catch (final SQLException newException) {
            throw new PersistenceException(newException);
        }
    }

}
