package com.example.demo;

import com.example.demo.model.Genre;
import com.example.demo.model.Movie;
import com.example.demo.model.Viewer;
import com.example.demo.repositories.ViewerRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final ViewerRepository viewerRepository;
    private final PasswordEncoder passwordEncoder;

    public BootStrapMySQL(ViewerRepository viewerRepository, PasswordEncoder passwordEncoder) {
        this.viewerRepository = viewerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Viewer jan = new Viewer();
        jan.setEmail("jan.witek88@gmail.com");
        jan.setUsername("Jan");
        jan.setPassword(passwordEncoder.encode("password"));


        Movie swotr = new Movie();
        swotr.setViewer(jan);
        swotr.setTitle("Star Wars");
        swotr.setSource("https://www.youtube.com/");
        swotr.setGenre(Genre.SCIFI);
        swotr.setDuration(140L);
        swotr.setDirector("Gorge Lucas");
        jan.addMovie(swotr);

        Movie chaser = new Movie();
        chaser.setViewer(jan);
        chaser.setTitle("I saw the devil");
        chaser.setSource("https://www.kissasian.sh");
        chaser.setGenre(Genre.THRILLER);
        chaser.setDirector("Na Hong Jin");
        jan.addMovie(chaser);

        viewerRepository.save(jan);

        Viewer natalia = new Viewer();
        natalia.setEmail("natalia.krukar@gmail.com");
        natalia.setUsername("Natalia");

        Movie call = new Movie();
        call.setViewer(natalia);
        call.setTitle("The call");
        call.setSource("https://www.Netflix.com");
        call.setGenre(Genre.THRILLER);
        call.setDuration(120L);
        call.setDirector("Park Chan Wook");
        natalia.addMovie(call);
        viewerRepository.save(natalia);
    }
}
