package com.cellpay.ticketingSystem.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class ApplicationHelper {

    public static boolean isAuthenticated() {
        log.info("isAuthenticated");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            log.info("isAuthenticated FALSE");
            return false;
        }
        log.info(""+authentication.isAuthenticated());
        return authentication.isAuthenticated();
    }
}
