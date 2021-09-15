package com.vestibulartio.repository;

import org.springframework.data.repository.CrudRepository;

import com.vestibulartio.models.Convidado;
import com.vestibulartio.models.EventoConvidado;
import com.vestibulartio.models.EventoConvidadoPK;

public interface EventoConvidadoRepository extends CrudRepository<EventoConvidado, EventoConvidadoPK>{

}
