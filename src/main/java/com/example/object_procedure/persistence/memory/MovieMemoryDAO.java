package com.example.object_procedure.persistence.memory;

import com.example.object_procedure.domain.Movie;
import com.example.object_procedure.persistence.MovieDAO;

public class MovieMemoryDAO extends InMemoryDAO<Movie> implements MovieDAO {

    @Override
    public Movie selectMovie(Long movieId) {
        return findOne(movie -> movie.getId().equals(movieId));
    }
}