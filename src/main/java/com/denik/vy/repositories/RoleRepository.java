package com.denik.vy.repositories;

import com.denik.vy.models.Role;
import com.denik.vy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /*@Query("select new Role(r.id, r.name, r.users) from Role r join User u ON r.id = u.")
    Set<Role> users();*/
}
