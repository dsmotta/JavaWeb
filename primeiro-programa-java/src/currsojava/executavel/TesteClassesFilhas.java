package currsojava.executavel;

import cursojava.classes.Aluno;
import cursojava.classes.Diretor;
import cursojava.classes.Pessoa;
import cursojava.classes.Secretario;

public class TesteClassesFilhas {
	
	public static void main(String[] args) {
		
		Aluno aluno = new Aluno();
		aluno.setNome("Douglas");
		aluno.setIdade(21);
		
		Diretor diretor = new Diretor();
		diretor.setNome("Marcos");
		diretor.setRegistroGeral("2222222222222");
		diretor.setIdade(50);
		
		Secretario secretario = new Secretario();
		secretario.setNome("Jo�o");
		secretario.setExperiencia("Administra��o");
		secretario.setIdade(18);
		
		System.out.println(aluno);
		System.out.println(diretor);
		System.out.println(secretario);
		
		System.out.println(aluno.msgMaiorIdade());
		System.out.println(diretor.pessoaMaiorIdade());
		System.out.println(secretario.pessoaMaiorIdade());
		
		System.out.println("Sal�rio Aluno � = " + aluno.salario());
		System.out.println("Sal�rio Diretor � = " + diretor.salario());
		System.out.println("Sal�rio Secret�rio � = " + secretario.salario());
		
		teste(aluno);
		teste(diretor);
		teste(secretario);
		
		
	}
	
	public static void teste(Pessoa pessoa) {
		System.out.println("Essa pessoa �: " + pessoa.getNome() + 
		" Sal�rio: " + pessoa.salario());
	}
	
	

}
