package com.onboarding.movies.moviesws.service;

import com.onboarding.movies.moviesws.client.ActorClient;
import com.onboarding.movies.moviesws.client.GenreClient;
import com.onboarding.movies.moviesws.dao.MovieDAO;
import com.onboarding.movies.moviesws.dao.RoleDAO;
import com.onboarding.movies.moviesws.exception.ResourceNotFoundException;
import com.onboarding.movies.moviesws.mapper.ObjectUtilMapper;
import com.onboarding.movies.moviesws.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

import static com.onboarding.movies.moviesws.helper.ImageHelper.*;
import static com.onboarding.movies.moviesws.helper.YearCalculator.calculateYear;

@Service
public class MovieService {

    @Autowired
    MovieDAO movieDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    ObjectUtilMapper mapper;

    @Autowired
    ActorClient actorClient;

    @Autowired
    GenreClient genreClient;

    public List<MovieDTOResponse> getMovies() {
        List<MovieEntity> movieEntityList= movieDAO.getMovies();
        if(movieEntityList == null){
            throw new ResourceNotFoundException("No movies found.");
        }

        int n = 0;
        List<MovieDTOResponse> movieDTOResponseList = mapper.mapList(movieEntityList, MovieDTOResponse.class);
        for(MovieDTOResponse movieDTOResponse: movieDTOResponseList) {
            //set movieImage
            String file = "src\\main\\resources\\static\\" + movieDTOResponse.getMovieImageName();
            String base64Image = encodeImageToBase64(file);
            movieDTOResponse.setMovieImage(base64Image);
            //set genre na osnovu id-a
            ResponseEntity<GenreDTO> genreDTORes = genreClient.getGenreById(movieEntityList.get(n).getGenreId());
            if(genreDTORes.getBody() == null) {//!genreDTORes.hasBody()
                throw new ResourceNotFoundException("Genre with id " + movieEntityList.get(n).getGenreId() + "not found.");
            }
            n++;
            GenreDTO genreDTO = genreDTORes.getBody();
            movieDTOResponse.setGenre(genreDTO.getGenreName());
            //set role
            List<RoleEntity> roleEntityList = roleDAO.getRoles(movieDTOResponse.getMovieId());
            List<RoleDTOResponse> roles = mapper.mapList(roleEntityList, RoleDTOResponse.class);
            for (RoleDTOResponse roleDTOResponse: roles) {
                ResponseEntity<ActorDTO> actorDTORes = actorClient.getActorById(roleDTOResponse.getActorId());
                if(actorDTORes.getBody() == null) {//!actorDTORes.hasBody()
                    throw new ResourceNotFoundException("Actor with id " + roleDTOResponse.getActorId() + "not found.");
                }
                ActorDTO actorDTO = actorDTORes.getBody();
                roleDTOResponse.setActorImage(actorDTO.getActorImage());
                roleDTOResponse.setImageName(actorDTO.getImageName());
                roleDTOResponse.setFullName(actorDTO.getFullName());
            }
            movieDTOResponse.setRoles(roles);
        }

        return movieDTOResponseList;
    }

    public MovieDTOResponse getMovieById(Integer movieId) {

        MovieEntity movieEntity= movieDAO.getMovieById(movieId);
        if(movieEntity == null){
            throw new ResourceNotFoundException("Movie with Id:" + movieId + " is not found.");
        }

        MovieDTOResponse movieDTOResponse = mapper.map(movieEntity, MovieDTOResponse.class);
        //set movieImage
        String file = "src\\main\\resources\\static\\" + movieDTOResponse.getMovieImageName();
        String base64Image = encodeImageToBase64(file);
        movieDTOResponse.setMovieImage(base64Image);
        //set genre na osnovu id-a
        ResponseEntity<GenreDTO> genreDTORes = genreClient.getGenreById(movieEntity.getGenreId());
        if(genreDTORes.getBody() == null) {//!genreDTORes.hasBody()
            throw new ResourceNotFoundException("Genre with id " + movieEntity.getGenreId() + "not found.");
        }
        GenreDTO genreDTO = genreDTORes.getBody();
        movieDTOResponse.setGenre(genreDTO.getGenreName());
        //set role
        List<RoleEntity> roleEntityList = roleDAO.getRoles(movieId);
        List<RoleDTOResponse> roles = mapper.mapList(roleEntityList, RoleDTOResponse.class);
        for (RoleDTOResponse roleDTOResponse: roles) {
            ResponseEntity<ActorDTO> actorDTORes = actorClient.getActorById(roleDTOResponse.getActorId());
            if(actorDTORes.getBody() == null) {//!actorDTORes.hasBody()
                throw new ResourceNotFoundException("Actor with id " + roleDTOResponse.getActorId() + "not found.");
            }
            ActorDTO actorDTO = actorDTORes.getBody();
            roleDTOResponse.setActorImage(actorDTO.getActorImage());
            roleDTOResponse.setImageName(actorDTO.getImageName());
            roleDTOResponse.setFullName(actorDTO.getFullName());
        }
        movieDTOResponse.setRoles(roles);

        return movieDTOResponse;
    }

    public List<MovieDTOResponse> getMoviesByName(String name) {

        List<MovieEntity> movieEntityList= movieDAO.getMoviesByName(name);
        if(movieEntityList == null){
            throw new ResourceNotFoundException("Movies with name:" + name + " is not found.");
        }

        int n = 0;
        List<MovieDTOResponse> movieDTOResponseList = mapper.mapList(movieEntityList, MovieDTOResponse.class);
        for(MovieDTOResponse movieDTOResponse: movieDTOResponseList) {
            //set movieImage
            String file = "src\\main\\resources\\static\\" + movieDTOResponse.getMovieImageName();
            String base64Image = encodeImageToBase64(file);
            movieDTOResponse.setMovieImage(base64Image);
            //set genre na osnovu id-a
            ResponseEntity<GenreDTO> genreDTORes = genreClient.getGenreById(movieEntityList.get(n).getGenreId());
            if(genreDTORes.getBody() == null) {//!genreDTORes.hasBody()
                throw new ResourceNotFoundException("Genre with id " + movieEntityList.get(n).getGenreId() + "not found.");
            }
            n++;
            GenreDTO genreDTO = genreDTORes.getBody();
            movieDTOResponse.setGenre(genreDTO.getGenreName());
            //set role
            List<RoleEntity> roleEntityList = roleDAO.getRoles(movieDTOResponse.getMovieId());
            List<RoleDTOResponse> roles = mapper.mapList(roleEntityList, RoleDTOResponse.class);
            for (RoleDTOResponse roleDTOResponse: roles) {
                ResponseEntity<ActorDTO> actorDTORes = actorClient.getActorById(roleDTOResponse.getActorId());
                if(actorDTORes.getBody() == null) {//!actorDTORes.hasBody()
                    throw new ResourceNotFoundException("Actor with id " + roleDTOResponse.getActorId() + "not found.");
                }
                ActorDTO actorDTO = actorDTORes.getBody();
                roleDTOResponse.setActorImage(actorDTO.getActorImage());
                roleDTOResponse.setImageName(actorDTO.getImageName());
                roleDTOResponse.setFullName(actorDTO.getFullName());
            }
            movieDTOResponse.setRoles(roles);
        }

        return movieDTOResponseList;
    }

    public MovieDTOResponse createMovie(MovieDTORequest movieDTORequest) {

        MovieEntity movieEntity = mapper.map(movieDTORequest, MovieEntity.class);

        if(movieDTORequest.getMovieImage() !=  null) {
            String[] imageData = splittedImageData(movieDTORequest.getMovieImage());
            String imageExtension = getImageExtension(imageData[0]);

            UUID uuid = UUID.randomUUID();
            String file = "src//main//resources//static//" + uuid + "." + imageExtension;

            saveBase64Image(imageData[1], file);
            movieEntity.setMovieImageName(uuid + "." + imageExtension);
        }
        calculateYear(movieEntity); //uzima releaseDate i racuna year i setuje ga
        movieEntity.setMovieId(null);
        MovieEntity returnedMovieEntity = movieDAO.createMovie(movieEntity);

        MovieDTOResponse movieDTOResponse = mapper.map(returnedMovieEntity, MovieDTOResponse.class);
        movieDTOResponse.setMovieImage(movieDTORequest.getMovieImage());

        //get genre from genre-ws
        ResponseEntity<GenreDTO> genreDTORes = genreClient.getGenreById(returnedMovieEntity.getGenreId());
        if(genreDTORes.getBody() == null) {//!genreDTORes.hasBody()
            throw new ResourceNotFoundException("Genre with id " + movieDTORequest.getGenreId() + "not found.");
        }
        GenreDTO genreDTO = genreDTORes.getBody();
        movieDTOResponse.setGenre(genreDTO.getGenreName());

        //spustiti role u bazu //dodati u movieDTOResponse
        List<RoleDTORequest> actors = movieDTORequest.getActors();
        List<RoleEntity> roleEntityList = mapper.mapList(actors, RoleEntity.class);
        for (RoleEntity roleEntity: roleEntityList) {
            roleEntity.setMovieId(movieDTOResponse.getMovieId());
        }
        List<RoleEntity> returnedRoleEntityList = roleDAO.createRoles(roleEntityList);

        //mapiraj u roleDTOResponse, dodaj info iz actor-a i dodati u movieDTOResponse
        List<RoleDTOResponse> roleDTOResponseList = mapper.mapList(returnedRoleEntityList, RoleDTOResponse.class);
        for (RoleDTOResponse roleDTOResponse: roleDTOResponseList) {
            ResponseEntity<ActorDTO> actorDTORes = actorClient.getActorById(roleDTOResponse.getActorId());
            if(actorDTORes.getBody() == null) {//!actorDTORes.hasBody()
                throw new ResourceNotFoundException("Actor with id " + roleDTOResponse.getActorId() + "not found.");
            }
            ActorDTO actorDTO = actorDTORes.getBody();
            roleDTOResponse.setActorImage(actorDTO.getActorImage());
            roleDTOResponse.setImageName(actorDTO.getImageName());
            roleDTOResponse.setFullName(actorDTO.getFullName());
        }
        movieDTOResponse.setRoles(roleDTOResponseList);

        //href je settovan u controlleru

        return movieDTOResponse;
    }

    public MovieDTOResponse updateMovie(MovieDTORequest movieDTORequest, Integer movieId) {

        MovieEntity movieEntityOld= movieDAO.getMovieById(movieId);
        if(movieEntityOld == null) {
            throw new ResourceNotFoundException("Movie with Id:" + movieId + " is not found.");
        }

        MovieEntity movieEntity = mapper.map(movieDTORequest, MovieEntity.class);
        movieEntity.setMovieId(movieId);

        if(!Objects.equals(movieDTORequest.getMovieImage(), "")){
            if(movieEntityOld.getMovieImageName() == null){
                String[] imageData = splittedImageData(movieDTORequest.getMovieImage());
                String imageExtension = getImageExtension(imageData[0]);
                UUID uuid = UUID.randomUUID();
                String file = "src//main//resources//static//" + uuid + "." + imageExtension;
                saveBase64Image(imageData[1], file);

                movieEntity.setMovieImageName(uuid + "." + imageExtension);
            }
            else {
                String[] imageData = splittedImageData(movieDTORequest.getMovieImage());
                String imageExtension = getImageExtension(imageData[0]);

                String oldFile = movieEntityOld.getMovieImageName();
                String[] newFileSplit = oldFile.split("\\.");

                String newFile = "src\\main\\resources\\static\\" + newFileSplit[0] + "." + imageExtension;
                saveBase64Image(imageData[1], newFile);

                movieEntity.setMovieImageName(newFileSplit[0] + "." + imageExtension);
            }
        }

        if(movieDTORequest.getReleaseDate() != null) {
            calculateYear(movieEntity);
        }

        MovieEntity returnedMovieEntity = movieDAO.updateMovie(movieEntity);

        MovieDTOResponse movieDTOResponse = mapper.map(returnedMovieEntity, MovieDTOResponse.class);
        movieDTOResponse.setMovieImage(movieDTORequest.getMovieImage());

        //get genre from genre-ws
        if(movieDTORequest.getGenreId() != null) {
            ResponseEntity<GenreDTO> genreDTORes = genreClient.getGenreById(returnedMovieEntity.getGenreId());
            if (genreDTORes.getBody() == null) {
                throw new ResourceNotFoundException("Genre with id " + movieDTORequest.getGenreId() + "not found.");
            }
            GenreDTO genreDTO = genreDTORes.getBody();
            movieDTOResponse.setGenre(genreDTO.getGenreName());
        }

        //spustiti role u bazu ili ih pokupi ako je u request-u null
        List<RoleDTORequest> actors = movieDTORequest.getActors();
        if(actors != null) {
            List<RoleEntity> roleEntityList = mapper.mapList(actors, RoleEntity.class);
            for (RoleEntity roleEntity : roleEntityList) {
                roleEntity.setMovieId(movieDTOResponse.getMovieId());
            }
            List<RoleEntity> returnedRoleEntityList = roleDAO.updateRoles(roleEntityList);

            //mapiraj u roleDTOResponse, dodaj info iz actor-a i dodati u movieDTOResponse
            List<RoleDTOResponse> roleDTOResponseList = mapper.mapList(returnedRoleEntityList, RoleDTOResponse.class);
            for (RoleDTOResponse roleDTOResponse : roleDTOResponseList) {
                ResponseEntity<ActorDTO> actorDTORes = actorClient.getActorById(roleDTOResponse.getActorId());
                if (actorDTORes.getBody() == null) {//!actorDTORes.hasBody()
                    throw new ResourceNotFoundException("Actor with id " + roleDTOResponse.getActorId() + "not found.");
                }
                ActorDTO actorDTO = actorDTORes.getBody();
                roleDTOResponse.setActorImage(actorDTO.getActorImage());
                roleDTOResponse.setImageName(actorDTO.getImageName());
                roleDTOResponse.setFullName(actorDTO.getFullName());
            }
            movieDTOResponse.setRoles(roleDTOResponseList);
        } else {
            roleDAO.deleteRoles(movieId);
        }

        return movieDTOResponse;
    }

    public void deleteMovie(Integer movieId) {

        MovieEntity movieEntity= movieDAO.getMovieById(movieId);
        if(movieEntity == null){
            throw new ResourceNotFoundException("Movie with Id:" + movieId + " is not found.");
        }

        roleDAO.deleteRoles(movieId);

        String file = "src\\main\\resources\\static\\" + movieEntity.getMovieImageName();
        deleteFile(file);

        movieDAO.deleteMovie(movieId);
    }




    //moje dodato
    public GenreDTO getGenreById(Integer genreId) {
        //get genre from genre-ws
        ResponseEntity<GenreDTO> genreDTORes = genreClient.getGenreById(genreId);
        if(genreDTORes.getBody() == null) {//!genreDTORes.hasBody()
            throw new NoSuchElementException("Genre with id " + genreId + "not found.");
        }
        GenreDTO genreDTO = genreDTORes.getBody();
        return genreDTO;
    }
}
