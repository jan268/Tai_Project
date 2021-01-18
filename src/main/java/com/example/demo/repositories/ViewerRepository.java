package com.example.demo.repositories;

import com.example.demo.model.Viewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ViewerRepository extends CrudRepository<Viewer, Long> {

    Optional<Viewer> findByUsername(String username);
}
