package com.dimoybiyca.svitkeramicu.service;

import com.dimoybiyca.svitkeramicu.model.Role;
import com.dimoybiyca.svitkeramicu.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public Role readByName(String name) {
        return roleRepo.findByName(name)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                format("Role with name %s not found", name)
                        ));
    }
}
