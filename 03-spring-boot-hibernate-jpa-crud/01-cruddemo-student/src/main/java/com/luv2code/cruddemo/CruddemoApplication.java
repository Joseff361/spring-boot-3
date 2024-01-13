package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    /**
     * This bean will be executed after the spring container loads all the beans
     */
    @Bean
    // INJECTING THE BEAN...
    // A @Bean annotated method can have an arbitrary number of parameters describing the dependencies required to build
    // that bean. The resolution mechanism is pretty much identical to constructor-based dependency injection.
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            // createStudent(studentDAO);
            createMultipleStudents(studentDAO);
            // readStudent(studentDAO);
            // queryForStudents(studentDAO);
            // queryForStudentsByLastName(studentDAO);
            // updateStudent(studentDAO);
            // deleteStudent(studentDAO);
            // deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all students...");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        Integer studentId = 3;
        System.out.println("Deleting student id: " + studentId);
        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDAO studentDAO) {
        int studentId = 1;
        System.out.println("Getting student with id: " + studentId);
        Optional<Student> student = studentDAO.findById(studentId);

        if (student.isPresent()) {
            System.out.println("Updating student...");
            student.get().setFirstName("John");
            studentDAO.update(student.get());

            System.out.println("Updated student: " + student);
        } else {
            System.out.println("Student not found");
        }
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findByLastName("Doe");

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findAll();

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        int id = tempStudent.getId();
        System.out.println("Saved student. Generated id: " + id);

        System.out.println("Retrieving student with id:" + id);
        Optional<Student> student = studentDAO.findById(id);

        if (student.isPresent()) {
            System.out.println("Found the student: " + student.get().toString());
        } else {
            System.out.println("Student not found");
        }
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        System.out.println("Creating 3 student objects...");
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
        Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

        System.out.println("Saving the students...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

    private void createStudent(StudentDAO studentDAO) {
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }
}
