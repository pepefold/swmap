package com.sw.map.contractor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
	Optional<Contractor> findByServiceIdAndId(Long serviceId, Long id);
	Optional<Contractor> findById(Long id);
}
