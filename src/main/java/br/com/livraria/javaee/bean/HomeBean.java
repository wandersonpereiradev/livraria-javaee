package br.com.livraria.javaee.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.javaee.dao.LivroDao;
import br.com.livraria.javaee.model.Livro;

@Model
public class HomeBean {

	@Inject
	private LivroDao dao;
	
	private List<Livro> livros = new ArrayList<>();
	
	public List<Livro> ultimosLancamentos() {
		this.livros = dao.ultimosLancamentos();
		return livros;
	}
	
	public List<Livro> demaisLivros() {
		this.livros = dao.demaisLivros();
		return livros;
	}
	


}
