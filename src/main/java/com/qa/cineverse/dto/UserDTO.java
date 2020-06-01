package com.qa.cineverse.dto;

import com.qa.cineverse.controller.UserController;
import com.qa.cineverse.domain.User;
import com.qa.cineverse.validation.AnnotationValidatorCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AnnotationValidatorCreator.PasswordMatches
public class UserDTO implements UserDetails {

    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    private String matchingPassword;
    @Setter @Getter @NotEmpty
    private String forename;
    @Setter @Getter @NotEmpty
    private String surname;
    @Setter @Getter @NotEmpty @AnnotationValidatorCreator.ValidEmail
    private String email;
    @NotEmpty
    private boolean active;
    @NotEmpty
    private List<GrantedAuthority> authorities;

    public UserDTO(User user) {
        this.userName = user.getUserName();
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
        return userName;
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
        return active;
    }

}
