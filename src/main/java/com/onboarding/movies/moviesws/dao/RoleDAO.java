package com.onboarding.movies.moviesws.dao;

import com.onboarding.movies.moviesws.model.MovieEntity;
import com.onboarding.movies.moviesws.model.RoleEntity;

import java.util.List;

public interface RoleDAO {

    List<RoleEntity> getRoles(Integer movieId);

    List<RoleEntity> createRoles(List<RoleEntity> roles);

    List<RoleEntity> updateRoles(List<RoleEntity> roles);

    void deleteRoles(Integer movieId);
}
