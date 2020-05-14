package br.com.livraria.javaee.conf;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {
	
	@RequestScoped // para durar apenas numa requisição
	@Produces // para o CDI criar o objeto
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
