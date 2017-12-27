package com.sw.map;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sw.map.services.Location;

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
	Collection<Location> findByServiceName(String name);
}
