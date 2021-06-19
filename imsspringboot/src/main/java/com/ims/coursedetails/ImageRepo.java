package com.ims.coursedetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<ImageDetails, Integer> {
	Optional<ImageDetails> findByName(String name);
}