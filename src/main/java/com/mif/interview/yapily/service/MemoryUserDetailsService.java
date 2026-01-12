package com.mif.interview.yapily.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * In the real world, there should be some database-backed user storage.
 * For demo purposes, replacing it with simple hard-coded users just to
 * show spring security functionality
 */
@Service
public class MemoryUserDetailsService implements UserDetailsService {

    private final Map<String, UserDetails> users = new ConcurrentHashMap<>();

    public MemoryUserDetailsService(PasswordEncoder encoder) {
        // Create Admin
        users.put("admin", User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build());

        // Create User
        users.put("user", User.withUsername("user")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }
}