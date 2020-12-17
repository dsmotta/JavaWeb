package currsojava.executavel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import cursojava.classes.Aluno;
import cursojava.classes.Diretor;
import cursojava.classes.Disciplina;
import cursojava.classesauxiliares.FuncaoAutenticacao;
import cursojava.constantes.StatusAluno;
import cursojava.excessao.ExcessaoProcessarNota;

public class PrimeiraClasseJava {
	
	/* MAIN � auto executavel em Java */
	
	public static void main(String[] args) {
				
		try {
			
		lerArquivo();
			
		String usuario = JOptionPane.showInputDialog("Usu�rio: ");
		String senha = JOptionPane.showInputDialog("Senha: ");
		
		//Secretario secretario = new Secretario();/*Acessando direto do objeto - usando Interface*/
		
				
		//PermitirAcesso permitiracesso = new Secretario(usuario, senha);
		
		if (new FuncaoAutenticacao(new Diretor(usuario, senha)).autenticar()) { /* Travando o contrato para autorizar exclusividade*/
		
		/*
		int nota1 = 80;
		int nota2 = 70;
		int nota3 = 60;
		int nota4 = 50;
		
		int media = 0;
		
		media = (nota1 + nota2 + nota3 + nota4) / 4;
		*/
	
		/*Condi��o l�gica IF e ELSE*/
		
		/*
		if (media >=70) {
			System.out.println("Aluno reprovado: " + media);
		}else if (media >=40 && media <= 69){
			System.out.println("Aluno em recupera��o: " + media);
		}else {
			System.out.println("Aluno reprovado: " + media);
		}
		*/
		
		/*Operadores Tern�rios: para micro valida��es*/
		/*
		String saidaResultado = media >= 70 ? "Aluno Aprovado" : (media >=40 && media <=69) ? "Aluno Reupera��o" : "Aluno Reprovado";
		System.out.println(saidaResultado);
		*/
		
		
		
		/*Operadores L�gicos Aninhados: s�o pere��es dentro de opera��es*/
		
		/*
		if (media >= 50) {
			if (media >= 70) {
				if (media > 90) {
					System.out.println("Aluno aprovado direto - Parab�ns " + media);				
				}else {
					System.out.println("Aluno aprovado direto " + media);
				}
			}else {
				System.out.println("Aluno em recupera��o " + media);
			}
		}else {
			System.out.println("Aluno reprovado direto " + media);
		}
		*/
		
		/* SWITCH CASE: Opera��es extas (testa valores) */
		
		/*
		int dia = 4;
		switch (dia) {
		case 1:
			System.out.println("domingo");
			break;
		case 2:
			System.err.println("segunda");
			break;
		case 3:
			System.out.println("ter�a");
			break;

		default:System.out.println("outro dia qualquer");
			break;
		}
		*/
		
		/* Operadores Relacionais == != > < >= <=   */
		
		/*
		int valor1 = 11;
		int valor2 = 10;
		
		if (valor1 == valor2) {
			System.out.println("Valores iguais");
		}else {
			System.out.println("Valores diferentes");
		}
		
		if (valor1 != valor2) {
			System.out.println("Valores diferentes");
		}else {
			System.out.println("Valores iguais");
		}
		*/
		
		/* Estrutura de repeti��o WHILE (enquanto for verdadeiro)*/
		
		/*
		int numero = 0;
		
		while (numero <= 3) {
			System.out.println("O numero atual �: " + numero);
			numero++;
		}
		*/
		
		/* Estrutura de repeti��o DO WHILE (primeiro verifica depois executa)*/
		
		/*
		int numero2 = 0;
		
		do {
			System.out.println("O numero atual � : " + numero2);
			numero2++;
		}while (numero2 <= 60);
		
		*/
		
		/* Estrutura de repeti��o FOR com BREAK (parada)*/
		
		/*
		for (int numero = 0; numero <= 10; numero ++) {
			if (numero == 3) {
				System.out.println("Numero encontrado: " + numero);
				System.out.println("parando a execu��o....");
				break;
			}
		}
		*/
		
		/* Estrutura de repeti��o FOR e CONTINUE */
		 
		/*
		for (int numero = 0; numero <= 10; numero ++) {
			if (numero == 3 || numero == 6 || numero == 9) {
				System.out.println("Numero encontrado: " + numero);
				continue;
				
			}
			System.out.println("Processando la�o de repeti��o");
		}
		*/
		
		/* M�dulo: Resto da divis�o % (MOD) */
		
		/*
		double carro = 9;
		double pessoa =2;
		
		double resto = carro % pessoa; /*MOD*/
		
		/*System.out.println("Sobraram exatamente: " + resto + " carro ");*/
		
		/* Entrada de dados */
		/*		
		String carro = JOptionPane.showInputDialog("Quantidade de Carros?");
		String pessoas = JOptionPane.showInputDialog("Quantidade de pessoas?");
		
		double carroNumero = Double.parseDouble(carro);
		double pessoaNumero = Double.parseDouble(pessoas);
		
		int divisao = (int) (carroNumero / pessoaNumero);
		
		double resto = carroNumero % pessoaNumero;
		
		int resposta = JOptionPane.showConfirmDialog(null, "Deseja ver o resultado da divis�o?");
		if (resposta == 0) {
			JOptionPane.showMessageDialog(null, "Divis�o foi: " + " " + divisao + " por pessoa");
		}else {
			System.out.println("N�o quiz ver resultado");
		}
		resposta = JOptionPane.showConfirmDialog(null, "Deseja ver o RESTO da divis�o?");
		if (resposta == 0) {
			JOptionPane.showMessageDialog(null, "O Resto da divis�o �: " + resto);
		}else {
			System.out.println("N�o quiz ver resultado");
		}
		*/
		
		/* Programa Dinamico de Media de Notas*/
		
		/*
		String nota1 = JOptionPane.showInputDialog("Informe a nota 1:");
		String nota2 = JOptionPane.showInputDialog("Informe a nota 2:");
		String nota3 = JOptionPane.showInputDialog("Informe a nota 3:");
		String nota4 = JOptionPane.showInputDialog("Informe a nota 4:");
		
		double dnota1 = Double.parseDouble(nota1);
		double dnota2 = Double.parseDouble(nota2);
		double dnota3 = Double.parseDouble(nota3);
		double dnota4 = Double.parseDouble(nota4);
		double media = (dnota1 + dnota2 + dnota3 + dnota4) / 4;
		
		/* M�dia para aprova��o � 70*/
		
		/*
		if (media >= 50) {
			if (media >= 70) {
				JOptionPane.showMessageDialog(null, "Aprovado media de: " + media);
			}else {
				JOptionPane.showMessageDialog(null, "Recupera��o media de: " + media);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Reprovado media de: " + media);
		}
		*/
		
		/* Cria o objeto real na mem�ria ou instancia */
		/* aluno1, aluno2, aluno3, aluno4, aluno 5 � uma referencia para o objeto aluno */		
		
		/*
		Aluno aluno1 = new Aluno(); /* Aqui ser� o Jo�o*/
		/*aluno1.nome = "Jo�o";
		/*System.out.println("Nome do aluno 1: " + aluno1.nome);
		
		/*Aluno aluno2 = new Aluno(); /* Aqui ser� o Pedro*/
		/*Aluno aluno3 = new Aluno(); /* Aqui ser� o Alex*/ 

		/* Passando Parametros */
		/*Aluno aluno4 = new Aluno("Maria");
		 */
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		HashMap<String, List<Aluno>> maps = new HashMap<String, List<Aluno>>();
		
				
		for (int qtd = 0; qtd <= 1;qtd++) {
		
		String nome = JOptionPane.showInputDialog("Nome do aluno: " + (qtd + 1));
		String idade = JOptionPane.showInputDialog("Idade: ");
		/*String dataNasc = JOptionPane.showInputDialog("Nascimento: ");
		String rg = JOptionPane.showInputDialog("RG: ");
		String cpf = JOptionPane.showInputDialog("CPF: ");*/
		
		Aluno aluno = new Aluno();
		aluno.setNome(nome);
		aluno.setIdade(Integer.valueOf(idade));
		/*aluno5.setDataNasc(dataNasc);
		aluno5.setRegistroGeral(rg);
		aluno5.setNumeroCPF(cpf);*/
		
		for (int i = 1; i <= 1; i++ ) {
			String nomeDisciplina = JOptionPane.showInputDialog("Disciplina "+ i + ":" );
			String notaDisciplina = JOptionPane.showInputDialog("Nota "+ i + ":");
			
			Disciplina disciplina = new Disciplina();
			disciplina.setDisciplina(nomeDisciplina);
			//disciplina.setNota(Double.valueOf(notaDisciplina));
			
			aluno.getDisciplinas().add(disciplina);
		}
		
		int escolha = JOptionPane.showConfirmDialog(null, "Deseja remover Disciplina ?");
		
		if (escolha == 0) {
			
			int continuarRemover = 0;
			int posicao = 1;
			
			while (continuarRemover == 0) {
				
				String disciplinaRemover = JOptionPane.showInputDialog("Remover Disciplia 1,2,3,4");
				aluno.getDisciplinas().remove(Integer.valueOf(disciplinaRemover).intValue() - posicao);
				posicao++;
				continuarRemover = JOptionPane.showConfirmDialog(null, "Continuar a remover ?");
			}
			
		}
		
		alunos.add(aluno);
		
		}
		
		maps.put(StatusAluno.APROVADO, new ArrayList<Aluno>());
		maps.put(StatusAluno.REPROVADO, new ArrayList<Aluno>());
		maps.put(StatusAluno.RECUPERACAO, new ArrayList<Aluno>());
		
		for (Aluno aluno : alunos) {
			
			if (aluno.getAprovado2().equalsIgnoreCase(StatusAluno.APROVADO)) {
				maps.get(StatusAluno.APROVADO).add(aluno);
			}else
			if (aluno.getAprovado2().equalsIgnoreCase(StatusAluno.RECUPERACAO)) {
				maps.get(StatusAluno.RECUPERACAO).add(aluno);
			}else {
				maps.get(StatusAluno.REPROVADO).add(aluno);
			}
			
		}
		
		System.out.println("*****************Lista dos Aprovados**************");
		
		for(Aluno aluno : maps.get(StatusAluno.APROVADO)) {
			System.out.println("Aluno: " + aluno.getNome());
			System.out.println("Resultado: " + aluno.getAprovado2() + " M�dia: " + aluno.getMediaNota());
			System.out.println("---------------------------------------------");
		}
		
		System.out.println("*****************Lista dos Reprovados**************");
		
		for(Aluno aluno : maps.get(StatusAluno.REPROVADO)) {
			System.out.println("Aluno: " + aluno.getNome());
			System.out.println("Resultado: " + aluno.getAprovado2() + " M�dia: " + aluno.getMediaNota());
			System.out.println("---------------------------------------------");
		}
		
		System.out.println("*****************Lista dos Recupera��o**************");
		
		for(Aluno aluno : maps.get(StatusAluno.RECUPERACAO)) {
			System.out.println("Aluno: " + aluno.getNome());
			System.out.println("Resultado: " + aluno.getAprovado2() + " M�dia: " + aluno.getMediaNota());
			System.out.println("---------------------------------------------");
		}
		
		
			
			
					
		}else {
			JOptionPane.showMessageDialog(null, "Usu�rio ou Senha inv�lido!");
			main(args);
		}
		
		
		}catch (Exception e) {/*Trata excess�o especifica somente*/
			
			StringBuilder saida = new StringBuilder();
			
			
			/*Imprime erro no console Java*/
			e.printStackTrace();
			
			/*Mensagem do erro ou causa*/
			System.out.println("Mensagem: " + e.getMessage());
			
			for (int pos = 0; pos < e.getStackTrace().length; pos++) {
					
				saida.append("\n Classe de erro: " + e.getStackTrace()[pos].getClassName());
				saida.append("\n M�todo de erro: " + e.getStackTrace()[pos].getMethodName());
				saida.append("\n Linha de erro: " + e.getStackTrace()[pos].getLineNumber());
				saida.append("\n Class: " + e.getClass().getName());
			}
			
			JOptionPane.showMessageDialog(null, "Erro de convers�o de numero" + saida.toString());
						
		}finally {/*Sempre � executado ocorrendo erros ou n�o*/
			JOptionPane.showMessageDialog(null, "Obriga por aprender Java");
		}
	
	}
	
	public static void lerArquivo() throws FileNotFoundException  {
		
		File fil = new File("c://lines.txt");
		Scanner scanner = new Scanner(fil);
		
	}

	
	

				
	}


