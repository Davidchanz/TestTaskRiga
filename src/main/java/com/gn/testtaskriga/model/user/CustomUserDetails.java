package com.gn.testtaskriga.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CustomUserDetails implements UserDetails {

    private Long id;

    private String password;

    private String login;

    private List<GrantedAuthority> authorities = new ArrayList<>();

    public CustomUserDetails(User user){
        this.id = user.getId();
        this.password = user.getPassword();
        this.login = user.getLogin();
        this.addAuthorities();
        this.authorities.addAll(getAuthorities(user.getAuthorities()));
    }

    private List<GrantedAuthority> getAuthorities(String authorities){
        if (authorities == null)
            return new ArrayList<>();
        String[] s = authorities.split(" ");
        List<GrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String authority : s) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority.toUpperCase()));
        }
        return simpleGrantedAuthorities;
    }

    public void addAuthorities(){
        this.authorities.add(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CustomUserDetails that = (CustomUserDetails) obj;
        return Objects.equals(getId(), that.getId());
    }
}
