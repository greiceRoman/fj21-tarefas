package br.com.caelum.tarefas.controller;

import java.sql.SQLException;
import java.util.Calendar;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.dao.JdbcTarefaDao;
import br.com.caelum.tarefas.model.Tarefa;

@Controller
public class TarefasController {
	
	
	@RequestMapping("novaTarefa")
	  public String form() {
	    return "tarefa/formulario";
	  }
	
	  
	  @RequestMapping("adicionaTarefa")
	  public String adiciona(@Valid Tarefa tarefa, BindingResult result) throws SQLException {
		  
		  if(result.hasFieldErrors("descricao")) {
		    return "tarefa/formulario";
		  }   
		  tarefa.setFinalizado(true);
		 JdbcTarefaDao dao = new JdbcTarefaDao();
	    dao.adiciona(tarefa);
	    return "tarefa/adicionada";
	  }
	  @RequestMapping("listaTarefas")
	  public String lista(Model model) throws SQLException {
	    JdbcTarefaDao dao = new JdbcTarefaDao();
	    model.addAttribute("tarefas", dao.Lista());
	    return "tarefa/lista";
	  }
	  @RequestMapping("removeTarefa")
	  public String remove(Tarefa tarefa) {
	    JdbcTarefaDao dao = new JdbcTarefaDao();
	    dao.remove(tarefa);
	    return "redirect:listaTarefas";
	  }
	  @RequestMapping("mostraTarefa")
	  public String mostra(Long id, Model model) {
	    JdbcTarefaDao dao = new JdbcTarefaDao();
	    model.addAttribute("tarefa", dao.buscaPorId(id));
	    return "tarefa/mostra";
	  }
	  @RequestMapping("alteraTarefa")
	  public String altera(Tarefa tarefa) {
	    JdbcTarefaDao dao = new JdbcTarefaDao();
	    dao.altera(tarefa);
	    return "redirect:listaTarefas";
	  }
}
