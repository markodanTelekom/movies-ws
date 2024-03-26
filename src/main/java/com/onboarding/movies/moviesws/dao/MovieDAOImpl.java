package com.onboarding.movies.moviesws.dao;

import com.onboarding.movies.moviesws.model.MovieEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MovieDAOImpl implements MovieDAO{

    private EntityManager entityManager;

    @Autowired
    public MovieDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MovieEntity getMovieById(Integer theMovieId) {
        return entityManager.find(MovieEntity.class, theMovieId);
    }

    @Override
    public List<MovieEntity> getMoviesByName(String theName) {
        TypedQuery<MovieEntity> query = entityManager.createQuery("from MovieEntity where name = :data", MovieEntity.class);
        query.setParameter("data", theName);

        List<MovieEntity> movies = query.getResultList();

        return movies;
    }

    @Override
    public List<MovieEntity> getMovies() {
        TypedQuery<MovieEntity> query = entityManager.createQuery("from MovieEntity", MovieEntity.class);

        List<MovieEntity> movies = query.getResultList();

        return movies;
    }

    @Override
    public MovieEntity createMovie(MovieEntity theMovieEntity) {
        entityManager.persist(theMovieEntity);

        return theMovieEntity;
    }

    @Override
    public MovieEntity updateMovie(MovieEntity theMovieEntity) {
        return entityManager.merge(theMovieEntity);
    }

    @Override
    public void deleteMovie(Integer theMovieId) {
        MovieEntity tempMovieEntity = entityManager.find(MovieEntity.class, theMovieId);

        entityManager.remove(tempMovieEntity);
    }
}
