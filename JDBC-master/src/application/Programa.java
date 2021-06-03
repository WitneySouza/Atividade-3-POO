package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import entities.Aluno;
import jdbc.AlunoJDBC;

public class Programa {

	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
        try {
        	
            int opcao = 0;
            Scanner console = new Scanner(System.in);
            
            do {
                System.out.print("######## Menu ########"
                                 + "\n1- Cadastrar"
                                 + "\n2- Listar"
                                 + "\n3- Alterar"
                                 + "\n4- Excluir"
                                 + "\n5- Sair");
                System.out.print("\n\tOpção: ");
                opcao = Integer.parseInt(console.nextLine());
                System.out.println("\n\n\n\n\n");
                
                if (opcao == 1){
                    
                    Aluno a = new Aluno();
                    AlunoJDBC acao = new AlunoJDBC();
                    
                    System.out.print("\n*** Cadastrar Aluno ***\n\r");
                    System.out.print("Id: ");
                    a.setId(Integer.parseInt(console.nextLine()));
                    System.out.print("Nome: ");
                    a.setNome(console.nextLine());
                    System.out.print("Sexo: ");
                    a.setSexo(console.nextLine());
                    System.out.print("Data de nascimento: ");
                    a.setDt_nasc( new Date(console.nextLine()) );
                    
                    acao.salvar(a);
                    console.nextLine();
                    System.out.println("\n\n\n\n\n");
                }
                if(opcao == 2) {
                	
                	AlunoJDBC alunojdbc = new AlunoJDBC();
                	List<Aluno> alunos = alunojdbc.listar();
                	
                	for(Aluno aluno: alunos) {
                		System.out.println("Id: " + aluno.getId());
                		System.out.println("Nome: "+ aluno.getNome());
                		System.out.println("Sexo: " + aluno.getSexo());
                		System.out.println("Data Nascimento: "+ aluno.getDt_nasc());
                		System.out.println();
                		
                		
                		
                	}
                }
                if(opcao == 3) {
                	
                	Aluno a = new Aluno();
                	AlunoJDBC acao = new AlunoJDBC();
                	
                    System.out.print("\n*** Alterar Aluno ***\n\r");

                    System.out.print("Nome: ");
                    a.setNome(console.nextLine());
                    System.out.print("Sexo: ");
                    a.setSexo(console.nextLine());
                    System.out.print("Data de nascimento: ");
                    a.setDt_nasc( new Date(console.nextLine()) );
                    System.out.print("Id: ");
                    a.setId(Integer.parseInt(console.nextLine()));
                    acao.alterar(a);
                    
                    console.nextLine();
                    System.out.println("\n\n\n\n\n");
                	
                }
                if(opcao == 4) {
                	Aluno aluno = new Aluno();
                	AlunoJDBC alunojdbc = new AlunoJDBC();
                	
                    System.out.print("\n*** Excluir Aluno ***\n\r");
                    System.out.print("Id: ");
                    aluno.setId(Integer.parseInt(console.nextLine()));
                    
                	alunojdbc.apagar(aluno.getId());
                	
                }
            } while(opcao != 5);
            
            System.out.println("Fechar o programa.");
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
	}
}
