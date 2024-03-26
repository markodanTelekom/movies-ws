package com.onboarding.movies.moviesws.model;

import java.io.Serializable;
import java.util.Objects;

public class RoleId implements Serializable {

    private Integer movieId;

    private Integer actorId;

    public RoleId() { }

    public RoleId(Integer movieId, Integer actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleId roleId)) return false;
        return Objects.equals(movieId, roleId.movieId) && Objects.equals(actorId, roleId.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, actorId);
    }


}
