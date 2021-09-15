package com.vestibulartio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.vestibulartio.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, Long>{

	Evento findByCodigo(long codigo);
	
}

