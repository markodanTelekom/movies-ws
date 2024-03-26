package com.onboarding.movies.moviesws.dao;

import com.onboarding.movies.moviesws.model.MovieEntity;
import com.onboarding.movies.moviesws.model.RoleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO{

    private EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<RoleEntity> getRoles(Integer movieId) {
        TypedQuery<RoleEntity> query = entityManager.createQuery("from RoleEntity where movieId = :data", RoleEntity.class);
        query.setParameter("data", movieId);

        List<RoleEntity> roles = query.getResultList();

        return roles;
    }

    @Override
    public List<RoleEntity> createRoles(List<RoleEntity> roles) {
        for(RoleEntity roleEntity: roles) {
            entityManager.persist(roleEntity);
        }

        return roles;
    }

    @Override
    public List<RoleEntity> updateRoles(List<RoleEntity> roles) {
        for(RoleEntity roleEntity: roles) {
            entityManager.merge(roleEntity);
        }

        return roles;
    }

    @Override
    public void deleteRoles(Integer movieId) {
        List<RoleEntity> roles = getRoles(movieId);

        for(RoleEntity roleEntity: roles) {
            entityManager.remove(roleEntity);
        }
    }
}
