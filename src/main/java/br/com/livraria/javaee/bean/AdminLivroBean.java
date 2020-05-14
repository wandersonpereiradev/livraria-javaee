package br.com.livraria.javaee.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import br.com.livraria.javaee.dao.AutorDao;
import br.com.livraria.javaee.dao.LivroDao;
import br.com.livraria.javaee.infra.FileSaver;
import br.com.livraria.javaee.model.Autor;
import br.com.livraria.javaee.model.Livro;

@Named
@RequestScoped
public class AdminLivroBean {

	@Inject
	LivroDao dao;
	
	@Inject
	FileSaver fileSaver;

	@Inject
	private AutorDao autorDao;

	@Inject
	private FacesContext context;

	private List<Integer> autoresId = new ArrayList<>(); // ArrayList para não dar nullpointerException

	private Livro livro = new Livro();

	private Part capaLivro;

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@Transactional // para abrir, executar e fechar a transação
	public String salvar() throws IOException {
		dao.salvar(livro);

		// definindo o caminho para a imagem no seridor
		livro.setCapaPath(fileSaver.caminhoCapaLivro(capaLivro, "livros"));
		System.out.println("Livro inserido: " + livro);

		this.livro = new Livro();
		this.autoresId = new ArrayList<>();

		// Escopo Flash para manter a mensagem entre um request e outro
		context.getExternalContext().getFlash().setKeepMessages(true);

		// mensagem que será exibida ao salvar o livro
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));

		return "lista?faces-redirect=true";
	}

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}

}
