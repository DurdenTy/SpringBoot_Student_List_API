package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentService {

    private final StudentRepository studentRepositoy;

    @Autowired
    public StudentService(StudentRepository studentRepositoy){
        this.studentRepositoy = studentRepositoy;
    }


    public List<Student> getStudents(){

        return studentRepositoy.findAll();

    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail =
        studentRepositoy.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepositoy.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepositoy.existsById(id);
        if(!exists){
            throw new IllegalStateException(
              "student with id" + id + " does not exists"
            );

        }
        studentRepositoy.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {

        Student student = studentRepositoy.findById(id).orElseThrow(()-> new IllegalStateException(
                "student with id" + id + "does not exists"
        ));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            student.setEmail(email);
        }

    }
}
