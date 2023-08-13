package com.example.quiz1.Controller;


import com.example.quiz1.ApiResponse.ApiResponse;
import com.example.quiz1.Model.TeacherModel;
import com.example.quiz1.Servies.TeacherServies;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    final private TeacherServies teacherServies;

    @GetMapping("/get")
    public ResponseEntity getTeachers(){
        ArrayList<TeacherModel> teachers=teacherServies.getTeachers();
        return ResponseEntity.status(200).body(teachers);
    }

    @PostMapping ("/add")
    public ResponseEntity addTeacher(@RequestBody @Valid TeacherModel teacher, Errors errors){

        if (errors.hasErrors())
        {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        teacherServies.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher Added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@RequestBody @Valid TeacherModel teacher, @PathVariable String id, Errors errors)
    {
        if (errors.hasErrors())
        {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=teacherServies.updateTeacher(teacher,id);

        if(!isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("Teacher id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Teacher updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id)
    {

        boolean isDeleted=teacherServies.deleteTeacher(id);
        if(!isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("Teacher id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Teacher deleted"));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity findTeacher(@PathVariable String id)
    {

        TeacherModel teacher=teacherServies.findTeacher(id);
        if(teacher==null){
            return ResponseEntity.status(400).body(new ApiResponse("Teacher id not found"));
        }
        return ResponseEntity.status(200).body(teacher);
    }
}
