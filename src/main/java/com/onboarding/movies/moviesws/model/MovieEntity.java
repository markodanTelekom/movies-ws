package com.onboarding.movies.moviesws.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private Integer genreId;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "language")
    private String language;

    @Column(name = "director")
    private String director;

    @Column(name = "runtime")
    private String runtime;

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    private Integer year;

    @Column(name = "movie_image_name")
    private String movieImageName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private double rating;

    //@OneToMany(mappedBy = "movie")
    //private List<RoleEntity> roles;

    public MovieEntity() {

    }

    public MovieEntity(String name, Integer genreId, Date releaseDate, String language, String director, String runtime, String description, Integer year, String movieImageName, String comment, double rating) {
        this.name = name;
        this.genreId = genreId;
        this.releaseDate = releaseDate;
        this.language = language;
        this.director = director;
        this.runtime = runtime;
        this.description = description;
        this.year = year;
        this.movieImageName = movieImageName;
        this.comment = comment;
        this.rating = rating;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMovieImageName() {
        return movieImageName;
    }

    public void setMovieImageName(String movieImageName) {
        this.movieImageName = movieImageName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", genreId=" + genreId +
                ", releaseDate=" + releaseDate +
                ", language='" + language + '\'' +
                ", director='" + director + '\'' +
                ", runtime='" + runtime + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", movieImageName='" + movieImageName + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}
