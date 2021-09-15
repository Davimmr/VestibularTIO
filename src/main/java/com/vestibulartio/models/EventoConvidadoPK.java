package com.vestibulartio.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EventoConvidadoPK implements Serializable {
	
	@Column(name="codigo")
	private long codigo;
	@Column(name="convidado_cpf")
	private long convidado_cpf;
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public long getConvidado_cpf() {
		return convidado_cpf;
	}
	public void setConvidado_cpf(long convidado_cpf) {
		this.convidado_cpf = convidado_cpf;
	}

}
