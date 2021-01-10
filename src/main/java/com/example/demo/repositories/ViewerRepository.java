package com.example.demo.repositories;

import com.example.demo.model.Viewer;
import org.springframework.data.repository.CrudRepository;

public interface ViewerRepository extends CrudRepository<Viewer, Long> {
}
