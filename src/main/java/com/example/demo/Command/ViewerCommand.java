package com.example.demo.Command;

import com.example.demo.Validation.UniqueEmail;
import com.example.demo.Validation.UniqueUsername;
import com.example.demo.Validation.UniqueViewer;
import com.example.demo.Validation.ValidPassword;
import com.example.demo.model.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@UniqueViewer
public class ViewerCommand {

    private Long id;

    @NotBlank
    //@UniqueUsername
    private String username;

    @NotBlank
    @ValidPassword
    private String password;

    @Email
    @NotBlank
    //@UniqueEmail
    private String email;

    private Set<MovieCommand> movies = new HashSet<>();
}
