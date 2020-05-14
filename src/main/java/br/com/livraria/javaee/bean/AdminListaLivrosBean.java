package br.com.livraria.javaee.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.javaee.dao.LivroDao;
import br.com.livraria.javaee.model.Livro;

@Model
public class AdminListaLivrosBean {
	
	@Inject
	LivroDao dao;
	
	private List<Livro> livros = new ArrayList<>();
	
	public List<Livro> getLivros() {
		this.livros = dao.listar();
		return livros;
	}

}
