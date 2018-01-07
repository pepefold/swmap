package com.sw.map.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
	Optional<Service> findByName(String name);
	Optional<Service> findById(Long id);
}
