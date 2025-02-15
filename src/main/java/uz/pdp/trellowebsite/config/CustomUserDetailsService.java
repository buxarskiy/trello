package uz.pdp.trellowebsite.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.trellowebsite.service.UserService;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    public final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUserName(username);
    }
}
