package userInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

import data.ListAuthors;
import data.ListBooks;
import entities.Author;
import entities.Book;

public class Interface {
	private String menuVector[] = { "Cadastrar livro", 
			"Cadastrar autor",
			"Remover livro",
			"Consultar", 
		    "Sair" };
	private String consultationOptions[] = { "Consultar todas as informações de um livro cadastrado pelo seu código ISBN",
			"Consultar os títulos dos livros cadastrados em ordem alfabética",
			"Consultar os títulos dos livros cadastrados de um determinado autor",
			"Consultar os títulos dos livros publicados em um determinado intervalo de anos",
			"Consultar título, autor e editora de todos os livros que contém, em seu título, uma palavra ou frase determinada",
			"Voltar para o menu inicial" };
	private Scanner input = new Scanner(System.in);
	private ListAuthors listAuthors = new ListAuthors();
	private ListBooks listBooks = new ListBooks();


	public void menu() {
		int option = 0;
		
		try {
			while(option != this.menuVector.length) {
				System.out.println("\n___________________\n");
				System.out.println("----LIBRARY APP----");
				System.out.println("___________________\n");
				for(int i = 0; i < this.menuVector.length; i++) {
					System.out.println((i+1) + ". " + menuVector[i]);
				}
				option = this.input.nextInt();
				switch(option) {
				case 1:
					this.input.nextLine();
					registerBook();
					break;
				case 2:
					this.input.nextLine();
					registerAuthor();
					break;
				case 3:
					this.input.nextLine();
					removeBook();
					break;
				case 4:
					consult();
					break;
				case 5:
					return;
				}
			}
		} catch(InputMismatchException im) {
			System.out.println("OCORREU UM ERRO!");
			System.out.println("A entrada de dados realizada pelo usuário não é esperada! Sistema voltará ao menu inicial.");
			this.input.nextLine();
			this.menu();
		}	
	}

	private void registerBook() {
		System.out.println("Digite o título do livro: ");
		String title = this.input.nextLine();
		System.out.println("Digite o código ISBN do livro: ");
		String isbn = this.input.nextLine();
		
		// registro dos autores do livro
		System.out.println("Digite quantos são os autores do livro: ");
		int quantAuthors = this.input.nextInt();
		Author[] authors = new Author[quantAuthors];
		this.input.nextLine();
		
		for(int i = 0; i < quantAuthors; i++) {
			boolean aux = false;
			String name = null;
			String originCountry = null;
			
			// executa enquanto o autor informado pelo usuário não existir na lista de autores
			while(aux == false) {
				System.out.println("Autor " + (i+1) + " de " + quantAuthors + ":\n");
				System.out.println("Digite o nome completo do autor: ");
				name = this.input.nextLine();
				System.out.println("Digite o país de origem do autor: ");
				originCountry = this.input.nextLine();
				
				authors[i] = new Author(name, originCountry);
					
				if(this.listAuthors.search(authors[i])) {
					aux = true;
					// se o autor existir na lista de autores, ainda validamos se o autor informado não é repetido no atual processo de cadastro
					for(int y = 0; y < i; y++) {
						if (authors[y].equals(authors[i])) {
							System.out.println("AUTOR REPETIDO! FAVOR INFORMAR UM AUTOR DIFERENTE.");
							aux = false;
						}
					}	
							
				} else {
					System.out.println("OCORREU UM ERRO!\n" + 
										"Autor(a) " + authors[i].getName() + " natural do(a) " + authors[i].getOriginCountry() + ", não existe na lista de autores cadastrados.\n" +
										"Favor informar um autor já cadastrado!\n");
					System.out.println("Para tentar um outro autor, digite 1. Para cancelar o cadastro deste livro e realizar o cadastro do autor primeiro, digite 2: ");
					int decision = input.nextInt();
					if (decision == 2) {
						System.out.println("CADASTRO DE LIVRO CANCELADO!");
						return;
					} else {
						this.input.nextLine();
					}
				}
			}
		}
		
		System.out.println("Digite o nome da editora do livro: ");
		String publishingComp = this.input.nextLine();
		System.out.println("Digite o ano de publicação do livro: ");
		int yearOfPublic = this.input.nextInt();
		
		Book newBook = new Book(title, isbn, authors, publishingComp, yearOfPublic);	
			
		// adiciona o livro na lista de livros, caso o mesmo não existir
		System.out.println(this.listBooks.addAtTheEnd(newBook));
	}
	
	private void registerAuthor() {
		System.out.println("Digite o nome do autor: ");
		String name = this.input.nextLine();
		System.out.println("Digite o seu país de origem: ");
		String originCountry = this.input.nextLine();
		Author newAuthor = new Author(name, originCountry);
		
		// adiciona o autor na lista de autores, caso o mesmo não existir
		System.out.println(this.listAuthors.addAtTheEnd(newAuthor));
	}
	
	private void removeBook() {
		System.out.println("Digite o código ISBN do livro: ");
		String isbn = this.input.nextLine();
		
		Book book = this.listBooks.getBookByISBN(isbn);
		
		// processo de confirmação para deletar o livro
		if(book != null) {
			System.out.println(book.toString());
			System.out.println("Para remover o livro acima digite '1' e para cancelar digite '2': ");
			int decision = this.input.nextInt();
			if(decision == 1) {
				System.out.println(this.listBooks.removeBook(book));					
			} else {
				System.out.println("REMOÇÃO DE LIVRO CANCELADO!");
			}
		}
	}
	
	private void consult() {
		int option = 0;
		while(option != consultationOptions.length) {
			for(int i = 0; i < this.consultationOptions.length; i++) {
				System.out.println((i+1) + ". " + consultationOptions[i]);
			}
			option = this.input.nextInt();	
			switch(option) {
			case 1:
				this.input.nextLine();
				System.out.println("Informe o código ISBN do livro que deseja consultar: ");
				String isbn = this.input.nextLine(); 
				Book book = this.listBooks.getBookByISBN(isbn);
				// imprime o livro, caso ele existir
				if (book != null) {
					System.out.println(book.toString() + "\n");												
				}
				break;
			case 2:
				this.listBooks.order();
				System.out.println(this.listBooks.showList());
				break;
			case 3:
				this.input.nextLine();
				System.out.println("Informe o nome do autor no qual deseja filtrar os livros: ");
				String author = this.input.nextLine(); 
				System.out.println(this.listBooks.filterByAuthor(author));
				break;
			case 4:
				System.out.println("Informe o ano inicial do intervalo de tempo: ");
				int startPeriod = this.input.nextInt();
				System.out.println("Informe o ano final do intervalo de tempo: ");
				int endPeriod = this.input.nextInt();
				System.out.println(this.listBooks.filterByPeriod(startPeriod, endPeriod));
				break;
			case 5:
				this.input.nextLine();
				System.out.println("Informe a palavra/frase contida no título:");
				String contExpression = this.input.nextLine();
				System.out.println(this.listBooks.filterByCont(contExpression));
				break;
			case 6:
				return;
			}
		}
	}
}