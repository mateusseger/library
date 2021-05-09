package data;

import entities.Author;
import entities.Book;
import exception.BookNotFoundException;
import exception.ExistingBookException;

public class ListBooks {
	private Book first, last;
	// lista encadeada dupla
	
	// adiciona livro no inicio da lista
	public String addAtTheBeginning(Book book) {
		try {
			if(this.search(book)) {
				throw new ExistingBookException(book.getIsbn());				
			} else {
				if(this.emptyList()) {
					this.first = book;
					this.last = book;
				} else {
					book.setNext(this.first);
					this.first.setPrev(book);
					this.first = book;
				}
				return "LIVRO CADASTRADO NO INÍCIO DA LISTA!\n";
			}
		} catch(ExistingBookException eb) {
			return "OCORREU UM ERRO!\n" + eb.toString();
		}
	}
	
	// adiciona livro no fim da lista
	public String addAtTheEnd(Book book) {
		try {
			if(this.search(book)) {
				throw new ExistingBookException(book.getIsbn());				
			} else {
				if(this.emptyList()) {
					this.first = book;
					this.last = book;
				} else {
					book.setPrev(this.last);
					this.last.setNext(book);
					this.last = book;	
				}
				return "LIVRO CADASTRADO NO FIM DA LISTA!\n";
			}
		} catch(ExistingBookException eb) {
			return "OCORREU UM ERRO!\n" + eb.toString();
		}
	}
	
	// utilizando o método equals sobrescrito na classe Book, para verificar se o livro já existe
	public boolean search(Book book) {
		for(Book aux = this.first; aux != null; aux = aux.getNext())
			if (aux.equals(book)) {
				return true;
			}
		return false;
	}
	
	// remove o ultimo livro da lista
	public Book removeAtTheEnd() {
		if(!this.emptyList()) {
			Book aux = this.last;
			if (this.first == this.last){
				this.first = null;
				this.last = null;
			} else {
				this.last = this.last.getPrev();
				this.last.setNext(null);
			}
			return aux;
		}
		return null;
	}
	
	// remove o livro passado como parâmetro
	public String removeBook(Book book) {
		try {
			if(!this.emptyList()) {
				for(Book aux = this.first; aux != null; aux = aux.getNext()) {
					if(aux.equals(book)) {
						if(this.first == this.last) {
							this.first = null;
							this.last = null;
						} else if(aux == this.first) {
							this.first = this.first.getNext();
							this.first.setPrev(null);
						} else if(aux == this.last) {
							this.last = this.last.getPrev();
							this.last.setNext(null);
						} else {
							aux.getPrev().setNext(aux.getNext());
							aux.getNext().setPrev(aux.getPrev());
						}
						return "LIVRO REMOVIDO!\n";
					}
				}
			}
			throw new BookNotFoundException(book.getIsbn());	
		} catch(BookNotFoundException bnf) {
			return "OCORREU UM ERRO!\n" + bnf.toString();
		}
	}
	
	// obtem o tamanho da lista
	public int getSize() {
		if(!this.emptyList()) {
			Book aux = this.first;
			int count = 0;
			while (aux != null) {
				count++;
				aux = aux.getNext();
			}
			return count;
		}
		return 0;
	}
	
	// obtem o livro na posicao index (caso não existir, retorna null)
	public Book getBookByIndex(int index) {
		try {
			if(!((getSize()-1) < index || index < 0)) {
				Book aux = this.first;
				for(int i = 0; i < index; i++) {
					aux = aux.getNext();
				}
				return aux;
			}
			throw new BookNotFoundException(index);
		} catch(BookNotFoundException bnf) {
			System.out.println("OCORREU UM ERRO!\n" + bnf.toString());
			return null;
		}
	}
	
	// obtem o livro pelo codigo ISBN
	public Book getBookByISBN(String isbn) {		
		try {
			if(!this.emptyList()) {
				Book aux = this.first;
				while(aux != null) {
					if(aux.getIsbn().equals(isbn)) {
						return aux;
					}
					aux = aux.getNext();
				}
			}
			throw new BookNotFoundException(isbn);
		} catch(BookNotFoundException bnf) {
			System.out.println("OCORREU UM ERRO!\n" + bnf.toString());
			return null;
		}
	}
	
	// ordena a lista em ordem alfabetica utilizando o algoritmo quicksort
	public void order() {
		this.quickSort(this.first, this.last);
	}
	
	// consulta com filtro pelo autor
	public String filterByAuthor(String author) {
		int cont = 0;
		String str = "LISTA DE LIVROS DO AUTOR " + author + ":\n";
		if(!this.emptyList()) {
			Book aux = this.first;
			while(aux != null) {
				for(int i = 0; i < aux.getAuthors().length; i++) {				
					if(aux.getAuthors()[i].getName().equalsIgnoreCase(author)) {
						str+= (cont + 1) + ". TÍTULO: " + aux.getTitle() + "\n";
						cont++;
						break;
					}
				}
				aux = aux.getNext();
			}
		}
		if(cont == 0) {
			return "NENHUM LIVRO ENCONTRADO PARA ESTE AUTOR!\n"; 			
		} else {
			return str;
		}
	}
	
	// consulta com filtro pelo período
	public String filterByPeriod(int firstPeriod, int finalPeriod) {
		int cont = 0;
		String str = "LISTA DE LIVROS PUBLICADOS ENTRE " + firstPeriod + " E " + finalPeriod + ":\n";
		if(!this.emptyList()) {
			Book aux = this.first;
			while(aux != null) {
				if((aux.getYearOfPublic() >= firstPeriod) && (aux.getYearOfPublic() <= finalPeriod)) {
					str+= (cont + 1) + ". TÍTULO: " + aux.getTitle() + "\n";
					cont++;
				}
				aux = aux.getNext();
			}
		}
		if(cont == 0) {
			return "NENHUM LIVRO ENCONTRADO PARA ESTE PERÍODO!\n";
		} else {
			return str;
		}
	}
	
	// consulta com filtro de palavra/frase
	public String filterByCont(String contExpression) {
		int cont = 0;
		String str = "LISTA DE LIVROS QUE CONTÉM A PALAVRA/FRASE {" + contExpression + "}:\n";	
		if(!this.emptyList()) {
			Book aux = this.first;
			while(aux != null) {			
				if(aux.getTitle().toLowerCase().contains(contExpression.toLowerCase())) {
					str+= (cont + 1) + ". TÍTULO: " + aux.getTitle() + " | AUTOR(ES): ";					
					for(int i = 0; i < aux.getAuthors().length; i++) {
						if(i == (aux.getAuthors().length - 1)) {
							str+= aux.getAuthors()[i].getName() + " | ";
						} else {
							str+= aux.getAuthors()[i].getName() + " - ";						
						}
					}					
					str+= "EDITORA: " + aux.getPublishingComp() + "\n";						
					cont++;
				}
				aux = aux.getNext();
			}
		}
		if(cont == 0) {
			return "NENHUM LIVRO ENCONTRADO CONTENDO A PALAVRA/FRASE INFORMADA!\n";
		} else {
			return str;			
		}
	}
	
	// retorna toda a lista de livros
	public String showList() {
		if(!this.emptyList()) {
			String str = "LISTA DE LIVROS:\n";
			int cont = 0;
			for (Book book = this.first; book != null; book = book.getNext()) {
				str+= (cont + 1) + ". TÍTULO: " + book.getTitle() +"\n";
				cont++;
			}
			return str;
		}
		return "LISTA VAZIA!\n";
	}
	
	// verifica se a lista esta vazia
	public boolean emptyList() {
		return this.first == null;
	}
 
	// método QuickSort
	private void quickSort(Book first, Book last) {
		// a ultima condição (first != last.getNext()) valida se pivot não é o ultimo elemento daquela seção de metodo recursivo
		if((last != null) && (first != last) && (first != last.getNext())) { 
			Book p = partition(first, last);
			this.quickSort(first, p.getPrev());
			this.quickSort(p.getNext(), last);
		}
	}
	
	private Book partition(Book first, Book last) {
		// inicia o pivo com o ultimo elemento
		String pivot = last.getTitle();		
		Book i = first.getPrev();
		Book j = first;
		while(j != last) {
			// determina a ordem do ordenamento	
			// procura uma string que venha antes ou que seja igual ao pivot (alfabeticamento)
			if(j.getTitle().compareTo(pivot) <= 0) {
				if(i == null) {
					i = first;
				} else {
					i = i.getNext();
				}
				replacement(i, j);
			}
			j = j.getNext();
		}
		if(i == null) {
			i = first;			
		} else {
			i = i.getNext();
		}
		replacement(i, last);
		return i;
	}
	
	private void replacement(Book original, Book substitute) {
		String title = original.getTitle();
		String isbn = original.getIsbn();
		Author[] authors = original.getAuthors();
		String publishingComp = original.getPublishingComp();
		int yearOfPublic = original.getYearOfPublic();
		
		original.setTitle(substitute.getTitle());
		original.setIsbn(substitute.getIsbn());
		original.setAuthors(substitute.getAuthors());
		original.setPublishingComp(substitute.getPublishingComp());
		original.setYearOfPublic(substitute.getYearOfPublic());
		
		substitute.setTitle(title);
		substitute.setIsbn(isbn);
		substitute.setAuthors(authors);
		substitute.setPublishingComp(publishingComp);
		substitute.setYearOfPublic(yearOfPublic);		
	}
}