package com.onboarding.movies.moviesws.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
@IdClass(RoleId.class)
public class RoleEntity {

    @Id
    @Column(name = "movie_id")
    private Integer movieId;

    @Id
    @Column(name = "actor_id")
    private Integer actorId;

    @Column(name = "role_name")
    private String roleName;

    //@ManyToOne
    //@JoinColumn(name = "movie_id", nullable = false)
    //private MovieEntity movie;

    public RoleEntity() {

    }

    public RoleEntity(Integer movieId, Integer actorId, String roleName) {
        this.movieId = movieId;
        this.actorId = actorId;
        this.roleName = roleName;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "movieId=" + movieId +
                ", actorId=" + actorId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
