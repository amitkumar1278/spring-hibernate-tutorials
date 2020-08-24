package com.spring.sboot.specifications.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.spring.sboot.specifications.domain.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

}
