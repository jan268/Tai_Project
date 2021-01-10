package com.example.demo.Command;

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
public class ViewerCommand {

    private Long id;

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    private Set<MovieCommand> movies = new HashSet<>();
}
