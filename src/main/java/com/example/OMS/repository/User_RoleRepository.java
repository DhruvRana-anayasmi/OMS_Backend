package com.example.OMS.repository;

import com.example.OMS.entity.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_RoleRepository extends JpaRepository<User_Role, Long> {

}
