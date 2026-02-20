package com.example.OMS.service;

import com.example.OMS.entity.Role;
import com.example.OMS.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository repo;

    public Role createRole(Role role){
        return repo.save(role);
    }

}
