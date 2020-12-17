package cursojava.classes;

import java.util.ArrayList;
import java.util.List;

import cursojava.constantes.StatusAluno;

public class Aluno extends Pessoa  {
	
	/* Abritudos do Aluno*/
	private String dataMatricula;
	private String nomeEscola;
	private String serieMatriculado;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	public void setDisciplinas(String string) {
		this.disciplinas = string;
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
		
	/* Recebe dados */
	public String getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(String dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	public String getSerieMatriculado() {
		return serieMatriculado;
	}

	public void setSerieMatriculado(String serieMatriculado) {
		this.serieMatriculado = serieMatriculado;
	}
	

	
	

	/* Metodo que retorna a média do aluno */
	public double getMediaNota() {
		double somaNotas = 0.0;
		for (Disciplina disciplina : disciplinas) {
			somaNotas += disciplina.getMediaNotas();
			
		}
		return somaNotas / disciplinas.size();
	}
	
	

	/* Método que retorno TRUE para aprovado e false para reprovado */
	public boolean getAlunoAprovado() {
		double media = this.getMediaNota();
		if (media >= 70) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getAprovado2() {
		double media = this.getMediaNota();
		if (media >= 50) {
			if (media >= 70) {
				return StatusAluno.APROVADO;
			}else {
				return StatusAluno.RECUPERACAO;
			}
		}else {
			return StatusAluno.REPROVADO;
		}
	}

	
	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", idade=" + idade + ", dataNasc=" + dataNasc + ", registroGeral="
				+ registroGeral + ", numeroCPF=" + numeroCPF + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroCPF == null) ? 0 : numeroCPF.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (numeroCPF == null) {
			if (other.numeroCPF != null)
				return false;
		} else if (!numeroCPF.equals(other.numeroCPF))
			return false;
		return true;
	}
	
	@Override
	public boolean pessoaMaiorIdade() {
		return idade >= 21;
	}

	public String msgMaiorIdade() {
		return this.pessoaMaiorIdade() ? "Aluno Maior de Idade" : "Aluno Menor de Idade";
	}

	@Override
	public double salario() {
		
		return 1500.90;
	}
	
	
		
	

}
