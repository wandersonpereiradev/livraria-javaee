package br.com.livraria.javaee.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.livraria.javaee.model.Usuario;

public class UsuarioDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}

}
