package com.onboarding.movies.moviesws.controller;

import com.onboarding.movies.moviesws.helper.Helper;
import com.onboarding.movies.moviesws.model.GenreDTO;
import com.onboarding.movies.moviesws.model.MovieDTORequest;
import com.onboarding.movies.moviesws.model.MovieDTOResponse;
import com.onboarding.movies.moviesws.service.MovieService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    Helper helper;

    private static final Logger logger = LogManager.getLogger(MovieController.class);

    @GetMapping(value = "/all")
    public ResponseEntity<List<MovieDTOResponse>> getMovies() {
        logger.info("Method getMovies started! ");

        List<MovieDTOResponse> movieDTOResponseList = movieService.getMovies();
        for (MovieDTOResponse movieDTOResponse: movieDTOResponseList) {
            movieDTOResponse.setHref(WebMvcLinkBuilder.linkTo(methodOn(MovieController.class).getMovieById(movieDTOResponse.getMovieId())).toUriComponentsBuilder().toUriString());
        }
        logger.info("API response - getMovies -> " + helper.convertObjectToString(movieDTOResponseList));

        logger.info("Method getMovies finished! ");
        return new ResponseEntity<>(movieDTOResponseList, HttpStatus.OK);
    }

    @GetMapping(value = "/{movieId:[0-9]+}")
    public ResponseEntity<MovieDTOResponse> getMovieById(@PathVariable("movieId") Integer movieId) {
        logger.info("Method getMovieById " + movieId + " started! ");

        MovieDTOResponse movieDTOResponse = movieService.getMovieById(movieId);
        movieDTOResponse.setHref(WebMvcLinkBuilder.linkTo(methodOn(MovieController.class).getMovieById(movieDTOResponse.getMovieId())).toUriComponentsBuilder().toUriString());
        logger.info("API response - getMovieById -> " + helper.convertObjectToString(movieDTOResponse));

        logger.info("Method getMovieById " + movieId + " finished! ");
        return new ResponseEntity<>(movieDTOResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTOResponse>> getMoviesByName(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
        logger.info("Method getMovieByName " + name + " started! ");

        List<MovieDTOResponse> movieDTOResponseList = movieService.getMoviesByName(name);
        for (MovieDTOResponse movieDTOResponse: movieDTOResponseList) {
            movieDTOResponse.setHref(WebMvcLinkBuilder.linkTo(methodOn(MovieController.class).getMovieById(movieDTOResponse.getMovieId())).toUriComponentsBuilder().toUriString());
        }
        logger.info("API response - getMovieByName -> " + helper.convertObjectToString(movieDTOResponseList));

        logger.info("Method getMovieByName by name " + name + " finished! ");
        return new ResponseEntity<>(movieDTOResponseList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieDTOResponse> createMovie(@Valid @RequestBody MovieDTORequest movieDTORequest) {

        logger.info("Method createMovie " + helper.convertObjectToString(movieDTORequest) + " started! ");

        MovieDTOResponse movieDTOResponse = movieService.createMovie(movieDTORequest);
        movieDTOResponse.setHref(WebMvcLinkBuilder.linkTo(methodOn(MovieController.class).getMovieById(movieDTOResponse.getMovieId())).toUriComponentsBuilder().toUriString());
        logger.info("API response - createMovie -> " + helper.convertObjectToString(movieDTOResponse));

        logger.info("Method createMovie finished! ");
        return new ResponseEntity<>(movieDTOResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{movieId:[0-9]+}")
    public ResponseEntity<MovieDTOResponse> updateMovie(@Valid @RequestBody MovieDTORequest movieDTORequest, @PathVariable("movieId") Integer movieId) {

        logger.info("Method updateMovie " + helper.convertObjectToString(movieDTORequest) + "for movieId" + movieId + " started! ");

        MovieDTOResponse movieDTOResponse = movieService.updateMovie(movieDTORequest, movieId);
        movieDTOResponse.setHref(WebMvcLinkBuilder.linkTo(methodOn(MovieController.class).getMovieById(movieDTOResponse.getMovieId())).toUriComponentsBuilder().toUriString());
        logger.info("API response - updateMovie -> " + helper.convertObjectToString(movieDTOResponse));

        logger.info("Method updateMovie finished! ");
        return new ResponseEntity<>(movieDTOResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{movieId:[0-9]+}")
    public void deleteMovie(@PathVariable("movieId") Integer movieId) {

        logger.info("Method deleteMovie by id " + movieId + " started! ");

        movieService.deleteMovie(movieId);

        logger.info("Method deleteMovie by id " + movieId + " finished! ");
    }


    //moje dodato
    @GetMapping(value = "/genre/{genreId:[0-9]+}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable("genreId") Integer genreId) {

        logger.info("Method getGenreById " + genreId + " started! ");

        GenreDTO genreDTO = movieService.getGenreById(genreId);
        logger.info("API response - getGenreById -> " + helper.convertObjectToString(genreDTO));

        logger.info("Method getMovieById " + genreId + " finished! ");
        return new ResponseEntity<>(genreDTO, HttpStatus.OK);
    }
}
