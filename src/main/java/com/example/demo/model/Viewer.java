package com.example.demo.model;

import com.example.demo.Validation.UniqueUsername;
import com.example.demo.student.Student;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.example.demo.security.ApplicationUserRole.STUDENT;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Viewer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    private String username;

    @NotNull
    @Email
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viewer")
    private Set<Movie> movies = new HashSet<>();

    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public Viewer addMovie(Movie movie){
        movie.setViewer(this);
        this.movies.add(movie);
        return this;
    }

    public Viewer(@NotEmpty @NotNull String username, @NotNull @Email String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return STUDENT.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
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
