package com.dimoybiyca.svitkeramicu.security;

import com.dimoybiyca.svitkeramicu.domain.dto.CreateUserRequest;
import com.dimoybiyca.svitkeramicu.domain.dto.UserView;
import com.dimoybiyca.svitkeramicu.domain.mapper.UserMapper;
import com.dimoybiyca.svitkeramicu.model.User;
import com.dimoybiyca.svitkeramicu.repository.UserRepo;
import com.dimoybiyca.svitkeramicu.service.RoleService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;

import static java.lang.String.format;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    private UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomUserDetailsService(
            UserRepo userRepo,
            BCryptPasswordEncoder passwordEncoder,
            RoleService roleService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = new UserMapper(roleService);
    }

    @Transactional
    public UserView create(CreateUserRequest request) {
        if (userRepo.findByUsername(request.username()).isPresent()) {
            throw new ValidationException(
                    format("User with email %s already exist", request.username()));
        }
        if (!request.password().equals(request.rePassword())) {
            throw new ValidationException("Passwords don't match!");
        }

        var user = userMapper.toObject(request);
        System.out.println(request.password());
        user.setPassword(passwordEncoder.encode(request.password()));

        user = userRepo.save(user);

        return userMapper.toView(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(
                () ->
                    new UsernameNotFoundException(
                        format("User with email - %s, not found", username)));

        return new CustomUserDetails(user);
    }
}
