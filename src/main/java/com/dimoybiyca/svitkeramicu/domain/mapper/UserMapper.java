package com.dimoybiyca.svitkeramicu.domain.mapper;

import com.dimoybiyca.svitkeramicu.domain.dto.CreateUserRequest;
import com.dimoybiyca.svitkeramicu.domain.dto.UserView;
import com.dimoybiyca.svitkeramicu.model.User;
import com.dimoybiyca.svitkeramicu.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleService roleService;

    public User toObject(CreateUserRequest request) {
        User user = new User();

        user.setFullName(request.fullName());
        user.setUsername(request.username());
        user.setPhone(request.phone());
        user.setRole(roleService.readByName("USER"));

        return user;
    }

    public UserView toView(User user) {

        return new UserView(
                user.getId(),
                user.getUsername(),
                user.getFullName()
        );
    }
}
