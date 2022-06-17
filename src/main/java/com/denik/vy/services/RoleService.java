package com.denik.vy.services;

import com.denik.vy.models.Role;
import com.denik.vy.models.User;
import com.denik.vy.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public List<Role> roles(){
        return roleRepository.findAll().stream().map(w-> new Role(w.getId(), w.getName().substring(5), w.getUsers())).collect(Collectors.toList());
    }
    public Role role(int roleId){
        return roleRepository.findById(roleId).orElse(null);
    }

}
