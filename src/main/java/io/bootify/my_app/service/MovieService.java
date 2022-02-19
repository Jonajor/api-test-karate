package io.bootify.my_app.service;

import io.bootify.my_app.domain.Movie;
import io.bootify.my_app.model.MovieDTO;
import io.bootify.my_app.repos.MovieRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> mapToDTO(movie, new MovieDTO()))
                .collect(Collectors.toList());
    }

    public MovieDTO get(final Long id) {
        return movieRepository.findById(id)
                .map(movie -> mapToDTO(movie, new MovieDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Movie create(final MovieDTO movieDTO) {
        final Movie movie = new Movie();
        mapToEntity(movieDTO, movie);
        return movieRepository.save(movie);
    }

    public Movie update(final Long id, final MovieDTO movieDTO) {
        final Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(movieDTO, movie);
       return movieRepository.save(movie);
    }

    public void delete(final Long id) {
        movieRepository.deleteById(id);
    }

    private MovieDTO mapToDTO(final Movie movie, final MovieDTO movieDTO) {
        movieDTO.setId(movie.getId());
        movieDTO.setDescription(movie.getDescription());
        return movieDTO;
    }

    private Movie mapToEntity(final MovieDTO movieDTO, final Movie movie) {
        movie.setDescription(movieDTO.getDescription());
        return movie;
    }

}
