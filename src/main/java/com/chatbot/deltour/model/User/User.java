package com.chatbot.deltour.model.User;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Headers.PseudoHeaderName.AUTHORITY;

@Getter
@Setter
@Document(collection="user")
public class User implements UserDetails {

    @Id
    private String ID;

    private String PASSWORD;
    private String NAME;
    private String AUTHORITY;
    private boolean ENABLED;

    private String email;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(AUTHORITY));
        return auth;
    }

    @Override
    public String getPassword() {
        return PASSWORD;
    }

    @Override
    public String getUsername() {
        return ID;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String name) {
        NAME = name;
    }

}
