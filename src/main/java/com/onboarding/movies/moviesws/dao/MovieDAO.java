package com.onboarding.movies.moviesws.dao;

import com.onboarding.movies.moviesws.model.MovieEntity;

import java.util.List;

public interface MovieDAO {

    MovieEntity getMovieById(Integer movieId);

    List<MovieEntity> getMoviesByName(String name);

    List<MovieEntity> getMovies();

    MovieEntity createMovie(MovieEntity movieEntity);

    MovieEntity updateMovie(MovieEntity movieEntity);

    void deleteMovie(Integer movieId);
}
