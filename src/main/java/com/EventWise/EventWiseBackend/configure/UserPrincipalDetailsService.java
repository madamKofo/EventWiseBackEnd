/*
package com.EventWise.EventWiseBackend.configure;

import com.EventWise.EventWiseBackend.entities.User;
import com.EventWise.EventWiseBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserPrincipalDetailsService  implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByDisplayName(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + s);
        }

        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }
}
*/
