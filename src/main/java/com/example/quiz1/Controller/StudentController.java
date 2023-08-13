package com.example.quiz1.Controller;


import com.example.quiz1.ApiResponse.ApiResponse;
import com.example.quiz1.Model.StudentModel;
import com.example.quiz1.Model.TeacherModel;
import com.example.quiz1.Servies.StudentServies;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    final private StudentServies studentServies;

    @GetMapping("/get")
    public ResponseEntity getStudents(){
        ArrayList<StudentModel> students=studentServies.getStudents();
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid StudentModel student, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        studentServies.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@RequestBody @Valid StudentModel student, Errors errors,@PathVariable String id){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=studentServies.updateStudent(student,id);
        if(!isUpdated){
            return ResponseEntity.status(400).body(new ApiResponse("Student id not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteStudent(@PathVariable String id){

        boolean isDeleted=studentServies.deleteStudent(id);
        if(!isDeleted){
            return ResponseEntity.status(400).body(new ApiResponse("Student name not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));
    }

    @GetMapping("/find/{name}")
    public ResponseEntity findStudent(@PathVariable String name)
    {
        StudentModel student=studentServies.findStudent(name);
        if(student==null){
            return ResponseEntity.status(400).body(new ApiResponse("Student id not found"));
        }
        return ResponseEntity.status(200).body(student);
    }

}
