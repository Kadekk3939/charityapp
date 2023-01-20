package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.model.entity.UserRole;
import pl.polsl.io.charityapp.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public Long getUserRoleIdByRoleName(String roleName) {
        Optional<UserRole> role = userRoleRepository.findUserRoleByRoleName(roleName);
        return role.map(UserRole::getRoleId).orElse(null);
    }

    public void addBasicRoles(List<String> roles) {
        for (String name : roles) {
            if (getUserRoleIdByRoleName(name) == null) {
                UserRole role = new UserRole();
                role.setRoleName(name);
                userRoleRepository.save(role);
            }

        }
    }

}
