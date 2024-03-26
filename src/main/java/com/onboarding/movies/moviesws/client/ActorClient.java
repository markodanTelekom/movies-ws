package com.onboarding.movies.moviesws.client;

import com.onboarding.movies.moviesws.model.ActorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "actor-ws", url = "http://localhost:8089", path = "/actors")
public interface ActorClient {

    @GetMapping(value = "/{actorId:[0-9]+}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable("actorId") Integer actorId);
}
