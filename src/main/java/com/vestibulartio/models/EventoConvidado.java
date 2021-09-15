package com.vestibulartio.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="evento_convidado")
public class EventoConvidado {

		@EmbeddedId
		private EventoConvidadoPK eventoConvidadoPK ;

		public EventoConvidadoPK getEventoConvidadoPK() {
			return eventoConvidadoPK;
		}

		public void setEventoConvidadoPK(EventoConvidadoPK eventoConvidadoPK) {
			this.eventoConvidadoPK = eventoConvidadoPK;
		}

		
		
}
