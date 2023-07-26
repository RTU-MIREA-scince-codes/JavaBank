package ru.rtu_mirea.javabank.config.userDetails;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rtu_mirea.javabank.entity.User;
import ru.rtu_mirea.javabank.repository.UserRepository;

@Service("userDetailsService")
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User clientUser = userRepository.findByEmail(email);
        if (clientUser == null) {
            throw new UsernameNotFoundException(email);
        }
        UserDetails user = new CustomUserDetails(clientUser);

        return user;
    }
}
