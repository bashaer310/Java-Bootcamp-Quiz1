package com.example.quiz1.Model;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherModel {

    @NotEmpty(message = "Id should not empty")
    private String id;
    @NotEmpty(message = "Name should not empty")
    private String name;
    @NotNull(message = "Salary should not empty")
    private Double salary;
}
