package Configs;

package com.codefellowship.configs;

import com.codefellowship.repositories.CodeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    CodeUserRepository codeUserRepository;
    ClassLoader classLoader;
    source source;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return codeUserRepository.findByUsername(username);
    }
}
