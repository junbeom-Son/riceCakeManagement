package com.passage.management.service;

import com.passage.management.domain.User;
import com.passage.management.dto.LoginDTO;
import com.passage.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User registerMember(User user) {
        if (userRepository.existsByLoginId(user.getLoginId())) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * login method
     * 1. find User by login id
     * 2. if a user is found by the login id, compare the raw password to the encrypted password
     * 3. if it matches, return the user object
     * 4. otherwise, return null
     * @param loginDTO
     * @return user or null
     */
    public User login(LoginDTO loginDTO) {
        User user = userRepository.findByLoginId(loginDTO.getLoginId());
        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long userId = Long.parseLong(username);
        return userRepository.findById(userId).orElse(null);
    }
}
