package com.example.carros.api.security;

import com.example.carros.domain.User;
import com.example.carros.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //com.example.carros.domain.User user = userRep.findByLogin(username);
        User user = userRep.findByLogin(username);

        if (user == null)
            throw  new UsernameNotFoundException("user not found");

        return  user;
        //return User.withUsername(username).password(user.getSenha()).roles("USER").build();

        /*if (username.equals("user")){
            return User.withUsername(username)
                    .password(encoder.encode( "user")).roles("USER").build();
        }
        else if (username.equals("admin")){
            return User.withUsername(username).password(encoder.encode("admin")).roles("USER","ADMIN").build();
        }

        throw new UsernameNotFoundException("user not fount");

         */
    }
}
