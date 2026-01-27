package com.pooja.blogProject.service;

import com.pooja.blogProject.model.User;
import com.pooja.blogProject.model.UserPrincipal;
import com.pooja.blogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null)
        {
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found");
        }


        return new UserPrincipal(user);
    }
}
