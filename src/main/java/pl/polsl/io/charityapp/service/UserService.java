package pl.polsl.io.charityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.io.charityapp.exceptions.UserNotFoundException;
import pl.polsl.io.charityapp.model.entity.User;
import pl.polsl.io.charityapp.repository.UserRepository;
import pl.polsl.io.charityapp.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.encoder = encoder;
    }


    public User addUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUserRole(1L);
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
