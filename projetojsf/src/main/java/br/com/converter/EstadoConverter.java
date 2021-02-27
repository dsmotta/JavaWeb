package br.com.converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.entidades.Estados;
import br.com.jpautil.JPAUtil;

@FacesConverter(forClass = Estados.class, value = "estadoConverter")/*VALUE LIGA O CONVERTER AO COMBO BOX*/
public class EstadoConverter  implements Converter, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private JPAUtil jpaUtil;

	@Override /* Retorna objeto inteiro - objeto será chamado quando salvar vem da tela para o servidor  */ 
	public Object getAsObject(FacesContext context, UIComponent component, String codigoEstado) {
		
		EntityManager entityManager = CDI.current().select(EntityManager.class).get();
		
		Estados estados = (Estados) entityManager.find(Estados.class,Long.parseLong(codigoEstado));
		
		return estados;
	}

	@Override /* Retorna apernas o código em String - vem do servidor para tela */
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		
		if (estado == null) {
			return null;
		}
		if (estado instanceof Estados) {
			
			Long id = ((Estados) estado).getId();
			String idEstado = String.valueOf(id);
			return idEstado;
			
		}else {
			return estado.toString();
		}
		
		
		
	}

}
