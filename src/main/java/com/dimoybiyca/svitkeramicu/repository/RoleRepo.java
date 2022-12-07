package com.dimoybiyca.svitkeramicu.repository;

import com.dimoybiyca.svitkeramicu.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
