package com.carsmoviesinventory.app.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.carsmoviesinventory.app.Repositories.CarsMoviesRepository;
import com.carsmoviesinventory.app.Entities.CarsMoviesEntity;

import java.util.*;

@Service
public class CarsMoviesService{

    private final CarsMoviesRepository carsMoviesRepository;

    public CarsMoviesService(CarsMoviesRepository carsMoviesRepository) {
        this.carsMoviesRepository = carsMoviesRepository;
    }

    public ResponseEntity<?> getAllMovies(Pageable pageable) {
        Page<CarsMoviesEntity> movies = carsMoviesRepository.findAll(pageable);
        return getResponseEntity(movies);
    }

    public ResponseEntity<?> getMoviesById(UUID id) {
        Optional<CarsMoviesEntity> movie = carsMoviesRepository.findById(id);
        if (movie.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("Status", String.format("Movie with ID %s not found.", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(Collections.singletonMap("Movie", movie.get()));
    }


    public ResponseEntity<?> getMoviesByName(String movieName, Pageable pageable) {
        Page<CarsMoviesEntity> movies = carsMoviesRepository.findAllByCarMovieNameContaining(movieName, pageable);
        return getResponseEntity(movies);
    }

    private ResponseEntity<?> getResponseEntity(Page<CarsMoviesEntity> movies) {
        Map<String, Object> response = new HashMap<>();
        response.put("TotalElements", movies.getTotalElements());
        response.put("TotalPages", movies.getTotalPages());
        response.put("CurrentPage", movies.getNumber());
        response.put("NumberOfElements", movies.getNumberOfElements());
        response.put("Movies", movies.getContent());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> addMovie(CarsMoviesEntity movieToAdd) {
        Page<CarsMoviesEntity> movie = carsMoviesRepository.findAllByCarMovieNameContaining(
                movieToAdd.getCarMovieName(),
                Pageable.unpaged());
        if (movie.getTotalElements() > 0) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Movie already exists with %d coincidences.", movie.getTotalElements())), HttpStatus.CONFLICT);
        } else {
            CarsMoviesEntity savedMovie = carsMoviesRepository.save(movieToAdd);
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Added Movie with ID %s", savedMovie.getId())), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> updateMovie(UUID id, CarsMoviesEntity movieToUpdate) {
        Optional<CarsMoviesEntity> movie = carsMoviesRepository.findById(id);
        if (movie.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Movie with ID %s not found.", id)), HttpStatus.NOT_FOUND);
        }
        CarsMoviesEntity existingMovie = movie.get();

        existingMovie.setCarMovieName(movieToUpdate.getCarMovieName());
        existingMovie.setCarMovieYear(movieToUpdate.getCarMovieYear());
        existingMovie.setDuration(movieToUpdate.getDuration());

        carsMoviesRepository.save(existingMovie);

        return ResponseEntity.ok(Collections.singletonMap("Status", String.format("Updated Movie with ID %s", existingMovie.getId())));
    }

    public ResponseEntity<?> deleteMovie(UUID id) {
        Optional<CarsMoviesEntity> movie = carsMoviesRepository.findById(id);
        if (movie.isEmpty()) {
            return new ResponseEntity<>(Collections.singletonMap("Status", String.format("Movie with ID %s doesn't exist.", id)),HttpStatus.NOT_FOUND);
        }
        carsMoviesRepository.deleteById(id);
        return ResponseEntity.ok(Collections.singletonMap("Status", String.format("Deleted Movie with ID %s", id)));
    }

}