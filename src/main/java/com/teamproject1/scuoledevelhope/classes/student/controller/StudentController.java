package com.teamproject1.scuoledevelhope.classes.student.controller;

import com.bananapilot.samplespringauthenticationframework.filtes.annotations.FloorLevelAuthorization;
import com.teamproject1.scuoledevelhope.classes.student.Student;
import com.teamproject1.scuoledevelhope.classes.student.service.StudentService;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseElement;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseList;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @FloorLevelAuthorization(floorRole = "TUTOR")
    @GetMapping("/findAll")
    public BaseResponseList<Student> findAll() {
        return studentService.findAll();
    }

    @FloorLevelAuthorization(floorRole = "TUTOR")
    @GetMapping("/findById")
    public BaseResponseElement<Student> findById(@RequestParam String id) {
        return studentService.findById(id);
    }

    @FloorLevelAuthorization(floorRole = "COORDINATOR")
    @DeleteMapping("/deleteById")
    public BaseResponseElement<Student> delete(@RequestParam String id) {

        return studentService.deleteById(UUID.fromString(id));
    }
}
