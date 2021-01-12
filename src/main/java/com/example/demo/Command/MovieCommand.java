package com.example.demo.Command;

import com.example.demo.model.Genre;
import com.example.demo.model.Viewer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class MovieCommand {

    private Long id;
    private Long ViewerId;

    @NotBlank
    private String title;

    @NotBlank
    private String director;

    @Min(10)
    @Max(240)
    private Long duration;

    @URL
    private String source;
    private Genre genre;

}
