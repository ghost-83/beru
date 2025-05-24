package ru.ghost.beru.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ghost.beru.conf.AppUser;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BeruUserDetailsService implements UserDetailsService {

    private final AppUser appUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUser.getUsers().stream()
                .filter(user -> user.username().equals(username))
                .findFirst()
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.username(),
                        user.password(),
                        Stream.of(user.roles().split(","))
                                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.trim()))
                                .toList()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
