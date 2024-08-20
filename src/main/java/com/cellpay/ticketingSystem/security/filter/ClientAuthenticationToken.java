package com.cellpay.ticketingSystem.security.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class ClientAuthenticationToken extends AbstractAuthenticationToken {

    private final String clientId;
    private final String clientSecret;

    public ClientAuthenticationToken(final String clientId, final String clientSecret) {
        super(null);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.clientSecret;
    }

    @Override
    public Object getPrincipal() {
        return this.clientId;
    }
}
