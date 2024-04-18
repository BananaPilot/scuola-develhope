package com.teamproject1.scuoledevelhope.classes.role.controller;

import com.bananapilot.samplespringauthenticationframework.filtes.annotations.FloorLevelAuthorization;
import com.bananapilot.samplespringauthenticationframework.filtes.annotations.NoAuthorization;
import com.teamproject1.scuoledevelhope.classes.role.Role;
import com.teamproject1.scuoledevelhope.classes.role.service.RoleService;
import com.teamproject1.scuoledevelhope.classes.user.User;
import com.teamproject1.scuoledevelhope.types.dtos.BaseResponseElement;
import com.teamproject1.scuoledevelhope.types.dtos.RoleUsername;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @FloorLevelAuthorization(floorRole = "ADMIN")
    @PutMapping("/add")
    public BaseResponseElement<User> addRole(@RequestBody RoleUsername roleUsername) {
        return roleService.addRole(roleUsername);
    }

    @FloorLevelAuthorization(floorRole = "ADMIN")
    @DeleteMapping("/add")
    public BaseResponseElement<User> deleteRole(@RequestBody RoleUsername roleUsername) {
        return roleService.deleteRole(roleUsername);
    }
}
