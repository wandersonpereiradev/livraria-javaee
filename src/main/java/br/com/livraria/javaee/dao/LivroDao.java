package br.com.livraria.javaee.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.livraria.javaee.model.Livro;

public class LivroDao {

	@PersistenceContext
	private EntityManager manager;

	public void salvar(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> listar() {
		String jpql = "select distinct(l) from Livro l" + " join fetch l.autores";

		return manager.createQuery(jpql, Livro.class).getResultList();
	}

	public List<Livro> ultimosLancamentos() {
		//sql para retornar os livros
		String jpql = "select l from Livro l order by l.dataPublicacao desc";
		//retornando a lista com, no máximo, 5 resultados
		return manager.createQuery(jpql, Livro.class).setMaxResults(5).getResultList();
	}

	public List<Livro> demaisLivros() {
		String jpql = "select l from Livro l order by l.id desc";
		return manager.createQuery(jpql, Livro.class).getResultList();
	}

	public Livro buscaPorId(Integer id) {
		String jpql = "select l from Livro l join fetch l.autores "
				+ "where l.id = :id";
		return manager.createQuery(jpql, Livro.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}
