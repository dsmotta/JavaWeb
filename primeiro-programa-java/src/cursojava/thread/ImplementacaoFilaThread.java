package cursojava.thread;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImplementacaoFilaThread extends Thread {
	
	private static ConcurrentLinkedQueue<ObjetoFilaThread> pilha_fila = 
			new ConcurrentLinkedQueue<ObjetoFilaThread>();
	
	public static void add(ObjetoFilaThread objetoFilaThread) {
		pilha_fila.add(objetoFilaThread);
					
	}
	
	@Override
	public void run() {
		
		System.out.println("Fila Rodando");
		
			
		
		while(true) {
		
		synchronized (pilha_fila) {/*Bloqueia o acesso a esta lista por outros processos*/
			
			Iterator iteracao = pilha_fila.iterator();

			while(iteracao.hasNext()) {/*Enquanto houver dados na lista vai processar*/
				
				ObjetoFilaThread processar = (ObjetoFilaThread) iteracao.next();/*Pega objeto atual*/
				
				/*Pode processar procesos grandes com envio em massa de e-mails
				 * Gerar uma lista enorme de PDF ... e etc*/
				System.out.println("-----------------------------------------");
				
				System.out.println(processar.getEmail());
				System.out.println(processar.getNome());
				
				iteracao.remove();
				try {
					Thread.sleep(1000);/*Dar um tempo para descarga de memória*/
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		}
		
		try {
			Thread.sleep(1000);/*Processou toda a lista da um tempo para limpeza de de memória*/
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		}
		
		
	}
	
	

}
