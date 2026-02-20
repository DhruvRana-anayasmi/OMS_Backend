package com.example.OMS.service;

import com.example.OMS.entity.User;
import com.example.OMS.entity.principle.UserPrinciple;
import com.example.OMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=repo.findByUsername(username);
        if(user==null){
            System.out.println("404 user not found");
            throw new UsernameNotFoundException("User 404");
        }
        return new UserPrinciple(user);
    }
}
