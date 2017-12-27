package com.sw.map;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sw.map.services.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
	Optional<Service> findByName(String name);
}
