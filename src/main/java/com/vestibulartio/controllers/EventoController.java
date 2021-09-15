package com.vestibulartio.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vestibulartio.models.Convidado;
import com.vestibulartio.models.Evento;
import com.vestibulartio.models.EventoConvidado;
import com.vestibulartio.models.EventoConvidadoPK;
import com.vestibulartio.repository.ConvidadoRepository;
import com.vestibulartio.repository.EventoConvidadoRepository;
import com.vestibulartio.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	@Autowired
	private EventoConvidadoRepository ec;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value= "/cadastrarEvento", method=RequestMethod.POST)
	public ResponseEntity<?> form(Evento evento){
		er.save(evento);
		return ResponseEntity.status(HttpStatus.CREATED).body(evento);
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos(){
		ModelAndView mv = new ModelAndView("listaevento");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEventos");
		mv.addObject("evento", evento);
		
		Iterable<Convidado> convidado = cr.findByEvento(evento);
		mv.addObject("convidado", convidado);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	@Transactional
	public String gravarConvidadoEvento(@PathVariable("codigo") long codigo,
			@RequestParam("cpf") long cpf,
			@RequestParam("cursos") String cursos, 
			@RequestParam("email") String email,
			@RequestParam("endereco") String endereco,
			@RequestParam("nome") String nome,
			@RequestParam("rg") long rg,
			@RequestParam("telefone") String telefone){
		
		Convidado convidado;
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEventos");
		mv.addObject("evento", evento);
		
		//if (!cr.existsById(cpf)) {
	
			convidado = new Convidado();
			convidado.setCpf(cpf);
			convidado.setCursos(cursos);
			convidado.setEmail(email);
			convidado.setEndereco(endereco);
			convidado.setNome(nome);
			convidado.setRg(rg);
			convidado.setTelefone(telefone);
			convidado.setEvento(evento);
			cr.save(convidado);
			mv.addObject("convidado", convidado);
		//}
		
		EventoConvidadoPK eventoConvidadoPK = new EventoConvidadoPK();
		eventoConvidadoPK.setCodigo(codigo);
		eventoConvidadoPK.setConvidado_cpf(cpf);
		
		/*if (ec.existsById(eventoConvidadoPK))
		{
			
			
		}*/
		
		EventoConvidado eventoConvidado = new EventoConvidado();
		eventoConvidado.setEventoConvidadoPK(eventoConvidadoPK);
		ec.save(eventoConvidado);
		
		return "redirect:/{codigo}";
	}
	
	@RequestMapping(value="/deletarEvento")
	public String deletarEvento(long codigo){
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";
	}

	@RequestMapping("/deletarConvidado")
	@Transactional
	public String deletarConvidado(@RequestParam long cpf,
			@RequestParam long codigo){
		
		EventoConvidadoPK eventoConvidadoPK = new EventoConvidadoPK();
		eventoConvidadoPK.setCodigo(codigo);
		eventoConvidadoPK.setConvidado_cpf(cpf);
		ec.deleteById(eventoConvidadoPK);
		
		Convidado convidado = cr.findByCpf(cpf);
		cr.deleteById(cpf);
		
		return "redirect:/" + codigo;
	}
	
}
