package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.exceptions.UserNotFoundException;
import pl.polsl.io.charityapp.mappers.UserMapper;
import pl.polsl.io.charityapp.model.dto.write.UserWriteModel;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.model.entity.UserRole;
import pl.polsl.io.charityapp.repository.UserRepository;
import pl.polsl.io.charityapp.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder encoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    //TODO: change to toEntity()

    public User addUser(UserWriteModel userWriteModel){
//        User user = new User();
//        userMapper.updateUserFromDto(userWriteModel, user);
        User user = userMapper.toEntity(userWriteModel);
        user.setPassword(encoder.encode(userWriteModel.getPassword()));

        Optional<UserRole> role = userRoleRepository.findUserRoleByRoleName(userWriteModel.getRole());

        role.ifPresent(user::setUserRole);
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserByUserId(Long userId){
        userRepository.deleteById(userId);
    }

    public User findUserByUserId(Long userId){
        return userRepository.findUserByUserId(userId)
                .orElseThrow(()->new UserNotFoundException("User by id "+ userId + " not found"));
    }

    public User findUserByLogin(String login){
        return userRepository.findUserByLogin(login)
                .orElseThrow(()->new UserNotFoundException("User by login "+ login + " not found"));
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User by email "+ email + " not found"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByLogin(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return user.get();
    }
}
