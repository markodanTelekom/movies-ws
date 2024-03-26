package com.onboarding.movies.moviesws.client;

import com.onboarding.movies.moviesws.model.GenreDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient(name = "genres-ws", url = "http://localhost:8091", path = "/genres")
public interface GenreClient {

    @GetMapping(value = "/{genreId:[0-9]+}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable("genreId") Integer genreId);
}
