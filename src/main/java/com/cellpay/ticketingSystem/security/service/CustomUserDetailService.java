package com.cellpay.ticketingSystem.security.service;

import com.cellpay.ticketingSystem.security.entity.CustomUserDetails;
import com.cellpay.ticketingSystem.security.entity.Roles;
import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.security.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUsername(username);
        Set<Roles> roles = userInfo.getRoles();
        List<? extends SimpleGrantedAuthority> mylist = roles.stream()
                .map(data -> new SimpleGrantedAuthority("ROLE_" + data.getRole())).toList();
        return User.withUsername(userInfo.getUsername()).password(userInfo.getPassword())
                .authorities(mylist).build();

//        UserInfo userInfo = userInfoRepository.findByUsername(username);
//        CustomUserDetails userDetails = null;
//        if (userInfo != null) {
//            userDetails = new CustomUserDetails();
//            userDetails.setUser(userInfo);
//        } else {
//            throw new UsernameNotFoundException("User not exist with name: " + username);
//        }
//        return userDetails;
//        return userInfo.map(CustomUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
