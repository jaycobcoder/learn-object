package com.example.object_procedure.persistence;

import com.example.object_procedure.domain.Movie;

public interface MovieDAO {
    Movie selectMovie(Long movieId);

    void insert(Movie movie);
}