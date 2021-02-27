package br.com.converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.entidades.Cidades;
import br.com.jpautil.JPAUtil;

@FacesConverter(forClass = Cidades.class, value = "cidadeConverter")/*VALUE LIGA O CONVERTER AO COMBO BOX*/
public class CidadesConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private JPAUtil jpaUtil;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCidade) {// retorna o objeto inteiro
		
		EntityManager entityManager = CDI.current().select(EntityManager.class).get();
		
		Cidades cidades = (Cidades) entityManager.find(Cidades.class, Long.parseLong(codigoCidade));
		
		return cidades;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cidade) { // retorna uma string
		
		if (cidade == null) {
			return null;
		}
		if(cidade instanceof Cidades) {
			return ((Cidades) cidade).getId().toString();
		}else {
			return cidade.toString();
		}
				
		
	}

}
