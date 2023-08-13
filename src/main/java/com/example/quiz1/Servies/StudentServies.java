package com.example.quiz1.Servies;


import com.example.quiz1.Model.StudentModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentServies {

    private ArrayList<StudentModel> students = new ArrayList<StudentModel>();

    public ArrayList<StudentModel> getStudents(){
        return students;
    }

    public void addStudent(StudentModel student){
        students.add(student);
    }

    public boolean updateStudent(StudentModel student, String id){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equalsIgnoreCase(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equalsIgnoreCase(id)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public StudentModel findStudent(String name){

        StudentModel student=null;
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getName().equalsIgnoreCase(name)) {
                student=students.get(i);
                return student;
            }
        }
        return student;
    }

}
