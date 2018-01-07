package com.sw.map.location;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * Location repository
 * @author Dali
 *
 */
public interface LocationRepository extends JpaRepository<Location, Long>
{
	/**
	 * Find by service name
	 * @param name the service name
	 * @return
	 */
	Collection<Location> findByContractorName(String name);
}
