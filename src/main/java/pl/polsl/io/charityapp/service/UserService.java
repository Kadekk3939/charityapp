package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.exceptions.UserNotFoundException;
import pl.polsl.io.charityapp.mappers.UserMapper;
import pl.polsl.io.charityapp.model.dto.read.UserReadModel;
import pl.polsl.io.charityapp.model.dto.write.UserWriteModel;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.model.entity.UserRole;
import pl.polsl.io.charityapp.repository.UserRepository;
import pl.polsl.io.charityapp.repository.UserRoleRepository;
import pl.polsl.io.charityapp.utility.CurrentUserData;

import java.util.Base64;
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

    public User addUser(UserWriteModel userWriteModel){
        User user = userMapper.toEntity(userWriteModel);
        user.setPassword(encoder.encode(userWriteModel.getPassword()));

        Optional<UserRole> role = userRoleRepository.findUserRoleByRoleName(userWriteModel.getRole());

        role.ifPresent(user::setUserRole);
        return userRepository.existsUserByLoginOrEmail(user.getLogin(), user.getEmail())
                ? null : userRepository.save(user);
    }

    public void addBasicUsers(List<String> roles) {
        for (String role : roles) {
            UserWriteModel user = new UserWriteModel();

            user.setFirstName(role);
            user.setLastName(role);
            user.setLogin(role);
            user.setPassword("123");
            user.setEmail(role + "@app.pl");
            user.setRole(role);

            this.addUser(user);
        }
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

    public UserReadModel findUserByUserId(Long userId){
        Optional<User> user = userRepository.findUserByUserId(userId);
        return user.<UserReadModel>map(userMapper::toReadModel).orElse(null);
    }

    public UserReadModel findUserByLogin(String login){
        Optional<User> user = userRepository.findUserByLogin(login);
        return user.<UserReadModel>map(userMapper::toReadModel).orElse(null);
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User by email "+ email + " not found"));
    }

    public User getUserEntityByLogin(String login) {
        Optional<User> user = userRepository.findUserByLogin(login);
        return user.orElse(null);
    }

    public User getLoggedUserEntity() {
        String login = CurrentUserData.getCurrentUserLogin();
        if (login != null) {
            return this.getUserEntityByLogin(login);
        }
        return null;
    }

    public String getUserKey(String login) {
        User user = getUserEntityByLogin(login);
        return String.format("u%04x_", user.getUserId());
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
