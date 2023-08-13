package com.example.quiz1.Servies;


import com.example.quiz1.Model.TeacherModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherServies {

    private ArrayList<TeacherModel> teachers= new ArrayList<TeacherModel>();

    public ArrayList<TeacherModel> getTeachers(){
        return teachers;
    }

    public void addTeacher(TeacherModel teacher){
        teachers.add(teacher);
    }

    public boolean updateTeacher(TeacherModel teacher, String id){
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equalsIgnoreCase(id)){
                teachers.set(i,teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(String id){
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equalsIgnoreCase(id)){
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    public TeacherModel findTeacher(String id){
        TeacherModel teacher=null;
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equalsIgnoreCase(id)){
                teacher=teachers.get(i);
                return teacher;
            }
        }
        return teacher;
    }

}
