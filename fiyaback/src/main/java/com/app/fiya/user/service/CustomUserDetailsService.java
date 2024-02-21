package com.app.fiya.user.service;

import com.app.fiya.exception.DniNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String dni) throws DniNotFoundExeption {
        return service.findByDni(dni).orElseThrow(() -> new DniNotFoundExeption(dni));
    }
}
