package io.bootify.my_app.repos;

import io.bootify.my_app.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Long> {
}
