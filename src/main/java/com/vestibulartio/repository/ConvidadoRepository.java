package com.vestibulartio.repository;

import org.springframework.data.repository.CrudRepository;

import com.vestibulartio.models.Convidado;
import com.vestibulartio.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, Long>{
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByCpf(Long cpf);
}
