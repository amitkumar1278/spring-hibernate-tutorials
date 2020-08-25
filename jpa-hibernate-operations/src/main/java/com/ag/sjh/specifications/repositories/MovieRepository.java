package com.ag.sjh.specifications.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ag.sjh.specifications.domain.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

}
