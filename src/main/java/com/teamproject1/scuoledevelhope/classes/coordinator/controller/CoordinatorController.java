package com.teamproject1.scuoledevelhope.classes.coordinator.controller;

import com.bananapilot.samplespringauthenticationframework.filtes.annotations.FloorLevelAuthorization;
import com.teamproject1.scuoledevelhope.classes.coordinator.Coordinator;
import com.teamproject1.scuoledevelhope.classes.coordinator.service.CoordinatorService;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseElement;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseList;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/coordinator")
public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    @FloorLevelAuthorization(floorRole = "ADMIN")
    @GetMapping("/get-all")
    public BaseResponseList<Coordinator> findAll() {
        return coordinatorService.findAll();
    }

    @FloorLevelAuthorization(floorRole = "ADMIN")
    @GetMapping("/get-by-id")
    public BaseResponseElement<Coordinator> findById(@RequestParam UUID id) {
        return coordinatorService.findById(id);
    }

    @FloorLevelAuthorization(floorRole = "ADMIN")
    @PostMapping("/save")
    public BaseResponseElement<Coordinator> save(@RequestBody Coordinator coordinator) {
        return coordinatorService.save(coordinator);
    }

    @FloorLevelAuthorization(floorRole = "ADMIN")
    @DeleteMapping("/delete-by-id")
    public BaseResponseElement<Coordinator> delete(@RequestParam UUID id) {
        return coordinatorService.deleteById(id);
    }

    @FloorLevelAuthorization(floorRole = "ADMIN")
    @PostMapping("/save")
    public ResponseEntity<BaseResponseElement<Coordinator>> save(@Valid @RequestBody Coordinator coordinator, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Errore di validazione: ");
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(new BaseResponseElement<>(null, errorMessage.toString(), false));
        }

        BaseResponseElement<Coordinator> response = coordinatorService.save(coordinator);

        return ResponseEntity.ok(response);
    }
}
