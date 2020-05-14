package br.com.livraria.javaee.bean;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.javaee.dao.LivroDao;
import br.com.livraria.javaee.model.Livro;

@Model
public class LivroDetalheBean {

	@Inject
	private LivroDao dao;
	
	private Livro livro;
	
	private Integer id;
	
	public void carregaDetalhe() {
		this.livro = dao.buscaPorId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
}








