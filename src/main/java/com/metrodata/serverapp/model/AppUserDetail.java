package com.metrodata.serverapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppUserDetail implements UserDetails {
  private final User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    /**
     * ROLE_USER
     * CREATE_USER
     * READ_USER
     * UPDATE_USER
     * DELETE_USER
     */

    List<GrantedAuthority> authorities = new ArrayList<>();

    user.getRoles().forEach(role -> {
      String roles = "ROLE_" + role.getName().toUpperCase();
      authorities.add(new SimpleGrantedAuthority(roles));

      role.getPrivileges().forEach(privilege -> {
        String privileges = privilege.getName().toUpperCase();
        authorities.add(new SimpleGrantedAuthority(privileges));
      });
    });

    return authorities;
  }

  @Override
  public @Nullable String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

}
