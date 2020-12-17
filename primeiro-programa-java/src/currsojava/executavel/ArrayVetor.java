package currsojava.executavel;

import javax.swing.JOptionPane;

import cursojava.classes.Aluno;
import cursojava.classes.Disciplina;

public class ArrayVetor {
	
	public static void main(String[] args) {
		
		double[] notas = {0.0, 0.0, 0.0, 0.0};
		
		
		String qtdeAlunos = JOptionPane.showInputDialog("Digite quantos alunos desja cadastrar");
		int qtd = Integer.valueOf(qtdeAlunos);
		
				
		Aluno[] arrayAlunos = new Aluno[qtd];
		
		for(int pos = 0; pos < arrayAlunos.length; pos++) {
			
			Aluno aluno = new Aluno();
			
			aluno.setNome(JOptionPane.showInputDialog("Digite nome do " + (pos + 1) + " aluno: "));
			aluno.setNomeEscola(JOptionPane.showInputDialog("Digite nome escola: "));
			
			int rsp = JOptionPane.showConfirmDialog(null, "Deseja Lançar Disciplinas?", "Atenção", JOptionPane.INFORMATION_MESSAGE);
			if(rsp == 0) {
				
				String resp = JOptionPane.showInputDialog("Digite a quantidade de Disciplinas:");
				for(int i = 0; i < Integer.parseInt(resp); i++) {
					
					Disciplina disciplina = new Disciplina();
					disciplina.setDisciplina(JOptionPane.showInputDialog("Disciplina " + (i + 1) + " = "));
					
									
					for (int p = 0; p < disciplina.getNota().length; p++ ) {
						
						notas[p] = Double.valueOf(JOptionPane.showInputDialog("Nota " + (p + 1) + " = "));
						disciplina.setNota(notas);
						
					}
					
					aluno.getDisciplinas().add(disciplina);
					
					
				}
				
				
			
			}
			arrayAlunos[pos] = aluno;
		}
		
		for(int pos = 0; pos < arrayAlunos.length; pos++) {
			
			System.out.println("Nome do aluno: " + arrayAlunos[pos].getNome());
						
			for(Disciplina d : arrayAlunos[pos].getDisciplinas()) {
		
				System.out.println("Disciplina: "+ d.getDisciplina());
				
				for(int posnota = 0; posnota < d.getNota().length; posnota++) {
					
					System.out.println("Notas: " + posnota + " = " + d.getNota()[posnota]);
					
				}
				System.out.println("Media das Notas: " + d.getMediaNotas());
				System.out.println("Maior Nota: " + d.maiorNota());
				System.out.println("Menor Nota: " + d.menorNota());
				System.out.println("-----------------------------------------------------------------------");
			}
			
			
		
		}
		
	}

}

