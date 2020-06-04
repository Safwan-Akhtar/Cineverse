package com.qa.cineverse.dto;

import com.qa.cineverse.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@NoArgsConstructor
public class UserDTO implements UserDetails {

    @Setter
    private String username;
    @Setter
    private String password;
    @Setter
    private String matchingPassword;
    @Setter @Getter
    private String forename;
    @Setter @Getter
    private String surname;
    @Setter @Getter
    private String email;
    @Setter
    private boolean active;

    private List<GrantedAuthority> authorities;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.matchingPassword = user.getMatchingPassword ();
        this.forename = user.getForename();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.active = user.isActive();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }

}
