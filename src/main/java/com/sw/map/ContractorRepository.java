package com.sw.map;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sw.map.services.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
	Optional<Contractor> findByServiceIdAndId(Long serviceId, Long id);
}
