package com.idrp.backend.service;

import com.idrp.backend.entity.Admin;
import com.idrp.backend.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomAdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found with email: " + username));

        return new User(
                admin.getEmail(),
                admin.getPassword(),
                admin.getActive(),
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ROLE_" + admin.getRole().name()))
        );
    }
}