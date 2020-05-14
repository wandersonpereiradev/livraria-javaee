package br.com.livraria.javaee.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.javaee.dao.LivroDao;
import br.com.livraria.javaee.model.CarrinhoCompras;
import br.com.livraria.javaee.model.CarrinhoItem;
import br.com.livraria.javaee.model.Livro;

@Model
public class CarrinhoComprasBean {
	
	@Inject
	LivroDao livroDao;
	
	@Inject
	CarrinhoCompras carrinho;

	public String add(Integer id) {
		Livro livro = livroDao.buscaPorId(id);
		CarrinhoItem item = new CarrinhoItem(livro);
		carrinho.add(item);
		return "carrinho?faces-redirect=true";
	}
	
	public List<CarrinhoItem> getItens() {
		return new ArrayList<CarrinhoItem>(carrinho.getItens());
	}
	
	public void remover(CarrinhoItem item) {
		carrinho.remover(item);
	}
}
