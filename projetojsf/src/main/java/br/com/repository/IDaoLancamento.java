package br.com.repository;

import java.io.Serializable;
import java.util.List;

import br.com.entidades.Lancamento;

public interface IDaoLancamento extends Serializable {

	List<Lancamento> consultar(Long codUser);
	
	
	
}