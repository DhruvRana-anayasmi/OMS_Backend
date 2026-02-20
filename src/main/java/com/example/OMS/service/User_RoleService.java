package com.example.OMS.service;
import com.example.OMS.entity.Role;
import com.example.OMS.entity.User_Role;
import com.example.OMS.exception.RoleAlreadyAssignedException;
import com.example.OMS.repository.RoleRepository;
import com.example.OMS.repository.User_RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class User_RoleService {

    @Autowired
    User_RoleRepository repo;
    @Autowired
    RoleRepository roleRepo;
    public User_Role assignRole(User_Role role) throws RoleAlreadyAssignedException, RoleNotFoundException {
        User_Role foundrole=repo.findById(role.getUserId()).orElseThrow(()->new RuntimeException("User not found"));
        List<Role> matchedRoles = roleRepo.findAll().stream()
                .filter(r -> Objects.equals(role.getRoleId(), r.getRoleId()))
                .toList();

        if(matchedRoles.isEmpty()){
            throw new RoleNotFoundException();
        }

        if(foundrole.getRoleId()==role.getRoleId()){
            throw new RoleAlreadyAssignedException("Role already assigned to this user");
        }

        return repo.save(role);
    }
}
