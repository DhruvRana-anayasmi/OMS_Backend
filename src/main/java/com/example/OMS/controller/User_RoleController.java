package com.example.OMS.controller;
import com.example.OMS.entity.User_Role;
import com.example.OMS.exception.RoleAlreadyAssignedException;
import com.example.OMS.service.User_RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@RestController
@CrossOrigin("*")
public class User_RoleController {

    @Autowired
    private User_RoleService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("assign")
    public User_Role assignRole(@RequestBody User_Role role) throws RoleAlreadyAssignedException, RoleNotFoundException {
        return service.assignRole(role);
    }
}
