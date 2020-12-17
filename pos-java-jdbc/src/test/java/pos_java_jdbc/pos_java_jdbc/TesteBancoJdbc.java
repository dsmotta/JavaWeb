package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {

	@Test
	public void initSalvaUsusario() {
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();

		userposjava.setNome("Jeferson");
		userposjava.setEmail("jsefff@gmail.com");

		userPosDAO.salvar(userposjava);

	}

	@Test
	public void initSalvaTelefone() {

		UserPosDAO dao = new UserPosDAO();
		Telefone telefone = new Telefone();
		telefone.setNumero_tel("16 9988-7676");
		telefone.setTipo_tel("Residencial");
		telefone.setUsuariopessoa(4L);
		dao.salvaTelefone(telefone);

	}

	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.listar();

			for (Userposjava userposjava : list) {

				System.out.println(userposjava);
				System.out.println("-------------------------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void iniListar_telefone() {

		UserPosDAO dao = new UserPosDAO();

		try {
			List<Telefone> list = dao.lista_tel();

			for (Telefone telefone : list) {

				System.out.println(telefone);
				System.out.println("----------------------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initBuscar() {

		UserPosDAO dao = new UserPosDAO();

		Userposjava userposjava;
		try {
			userposjava = dao.buscar(2L);
			System.out.println(userposjava);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initBuscaTelefoneID() {
		UserPosDAO dao = new UserPosDAO();
		Telefone telefone = new Telefone();

		telefone = dao.buscaTelefoneId(1L);
		System.out.println(telefone);
	}

	@Test
	public void initBusca_nome() {

		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.busca_nome("'Douglas'");
			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initbuscaTelefoneTipo() {

		UserPosDAO dao = new UserPosDAO();

		try {
			List<Telefone> list = dao.listaTelefoneTipo("Celular");
			for (Telefone telefone : list) {

				System.out.println(telefone);
				System.out.println("-----------------------------------------------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initAtualizar() {

		try {
			UserPosDAO dao = new UserPosDAO();
			Userposjava userposjava = dao.buscar(2L);
			userposjava.setNome("Nome atualizado");

			dao.atualizar(userposjava);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void atualizaTelefone() {

		UserPosDAO dao = new UserPosDAO();
		Telefone telefone = new Telefone();
		telefone = dao.buscaTelefoneId(3L);
		telefone.setNumero_tel("11 1111-1111");
		telefone.setTipo_tel("alterado");

		dao.atualizaTelefone(telefone);

	}

	@Test
	public void initDeletar() {
		try {

			UserPosDAO dao = new UserPosDAO();
			dao.deletar(2L);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initDeletaTelefone() {

		try {

			UserPosDAO dao = new UserPosDAO();
			dao.deletaTelefone(4L);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void initListaTelefoneNome() {

		UserPosDAO dao = new UserPosDAO();
		try {

			String nome = JOptionPane.showInputDialog("Digite o nome do usuario:");
			List<BeanUserFone> list = dao.listaUserFone(nome);

			for (BeanUserFone beanUserFone : list) {

				System.out.println(beanUserFone);
				System.out.println("--------------------------------------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testeDelete_telefone_Usuario() {

		UserPosDAO dao = new UserPosDAO();
		dao.deletaFones_por_User(6L);
	}

}
