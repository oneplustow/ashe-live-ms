package cn.oneplustow.ac.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author cc
 */
public class MultiUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String clientType;

    public MultiUsernamePasswordAuthenticationToken(Object principal, Object credentials,String clientType) {
        super(principal, credentials);
        this.clientType = clientType;
    }

    public MultiUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public String getClientType() {
        return clientType;
    }
}
