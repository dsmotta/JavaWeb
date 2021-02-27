package br.com.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.JPasswordField;

import br.com.entidades.Lancamento;


@Named
public class IDaoLancamentoImpl implements IDaoLancamento {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> consultar(Long codUser) {
		
		List<Lancamento> lista = null;
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		lista = entityManager.createQuery(" from Lancamento where usuario.id = " + codUser).getResultList();
		
		transaction.commit();
		
		
		return lista;
	}

}
