package com.teamproject1.scuoledevelhope.classes.coordinator.controller;

import com.teamproject1.scuoledevelhope.classes.coordinator.Coordinator;
import com.teamproject1.scuoledevelhope.classes.coordinator.service.CoordinatorService;
import com.teamproject1.scuoledevelhope.classes.school.School;
import com.teamproject1.scuoledevelhope.types.BaseResponseElement;
import com.teamproject1.scuoledevelhope.types.BaseResponseList;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/coordinator")
public class CoordinatorController {

    CoordinatorService coordinatorService;
    @GetMapping("/get-all")
    public BaseResponseList<Coordinator> findAll(){
        return coordinatorService.findAll();
    }

    @GetMapping("/get-by-id")
    public BaseResponseElement<Coordinator> findById(@RequestParam UUID id){
        return coordinatorService.findById(id);
    }

    @PostMapping("/save")
    public BaseResponseElement<Coordinator> save(@RequestBody Coordinator coordinator){
        return coordinatorService.save(coordinator);
    }

    @DeleteMapping("/delete-by-id")
    public BaseResponseElement<Coordinator> delete(@RequestParam UUID id){
        return coordinatorService.deleteById(id);
    }
}