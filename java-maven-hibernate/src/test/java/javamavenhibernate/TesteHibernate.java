package javamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {
	
	//-----------------------------------METODO DE CRIAÇÃO DAS TABELAS-------------------------------------------//
		@Test
		public void testeHibernateUtil1() { // cria a tabela no banco
			HibernateUtil.getEntityManager();
		}
		//-----------------------------------------------------------------------------------------------------------------------//
	
	//------------------------------------METODOS QUE USAM DAO GENERICO -------------------------------------------//
		@Test
		public void testeHibernateUtil() {//salvar no banco
			
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			
			UsuarioPessoa pessoa = new UsuarioPessoa();
					
			pessoa.setNome("Eduarto");
			pessoa.setSobrenome("Costa");
			pessoa.setEmail("sdaasdfas");
			pessoa.setLogin("asdfasdfasd");
			pessoa.setSenha("afadsa");
				
			daoGeneric.salvar(pessoa);
			
		}
		
	@Test
	public void testeBuscar() {//buscar
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeBusca2r() {//buscar
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(1L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeUpdate() {//update
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);
		pessoa = daoGeneric.pesquisar(pessoa);
		
		pessoa.setNome("Nome atualizado");
	
		daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeDelete() {//deleta registro
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(4L, UsuarioPessoa.class);
				
		daoGeneric.deletarPorId(pessoa);
		
				
	}
	
	@Test
	public void testeConsulta() {//lista todos os registros do banco
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
				
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println((usuarioPessoa));
			System.out.println("---------------------------------------------------");
		}				
	}
	//-----------------------------------------------------------------------------------------------------------------------//
	//METODOS QUE NÃO UTILIZAM OS METDOS DO DAO - OS METODOS SÃO CRIADOS EXTERNAMENTE
	
	@Test
	public void testeQueryList() {//listando com uma condição
		
		DaoGeneric<UsuarioPessoa>daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = 'Douglas' ").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("---------------------------------------------------");
		}
		
	}
	
	@Test
	public void testeQueryListMaxResult(){//listando e definindo o maximo de restultados mostrados na consulta
		
		DaoGeneric<UsuarioPessoa>daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa order by nome ").setMaxResults(3).getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("---------------------------------------------------");
		}
		
	}
	
	@Test
	public void testeQueryListParameter() {//usando parametros
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = :nome").setParameter("nome", "Marcos").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test
	public void testeQueryListParamete2r() {//usando dois parametros 
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome").
				setParameter("nome", "asdfasdasda").setParameter("sobrenome", "Donisete").getResultList();
								
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test
	public void testeQuerySomaIdade() {//calculo de matematicos
		
		DaoGeneric<UsuarioPessoa>daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Double somaIdade = (double) daoGeneric.getEntityManager().createQuery(" select avg(u.idade) from UsuarioPessoa u").getSingleResult();
		
		System.out.println("Soma das Idades: " + somaIdade );
		
	}
	
	//-----------------------------------------------------------------------------------------------------------------------//

	//----------------------------------------------USANDO NamedQuery---------------------------------------------//
	
	@Test
	public void testeNamedQuery1() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.listaTodos").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	@Test
	public void testeNamedQueryBuscaPorNome() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa>list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome","Marcos").getResultList();
		
		for(UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
	}
	//-----------------------------------------------------------------------------------------------------------------------//

	//----------------------------INSERT NO BANCO COM RELACIONAMENTO DE TABELAS-------------------------------------//

	@Test
	public void testeGravaTelefone() {
		
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar2(6L, UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		
		telefoneUser.setTipo("Celular");
		telefoneUser.setNumero("777777777777");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefoneUser);
		
	}
	
	@Test
	public void testeConsultaTelefones() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar2(6L,UsuarioPessoa.class);
		
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("--------------------------------------------------------------------------");
		}
	}
	
	
}


