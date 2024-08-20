package com.cellpay.ticketingSystem.security.service;

import com.cellpay.ticketingSystem.security.entity.Roles;
import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.security.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Value("${default.role}")
    private String defaultRole;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Set<Roles> roles = userInfo.getRoles();
        if (roles == null || roles.isEmpty()) {
            Roles superAdminRole = new Roles();
            superAdminRole.setRole(defaultRole);
            roles = new HashSet<>();
            roles.add(superAdminRole);
            userInfo.setRoles(roles);
            userInfoRepository.save(userInfo);
        }

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(data -> new SimpleGrantedAuthority("ROLE_" + data.getRole()))
                .toList();

        return User.withUsername(userInfo.getUsername())
                .password(userInfo.getPassword())
                .authorities(authorities)
                .build();
    }
}
