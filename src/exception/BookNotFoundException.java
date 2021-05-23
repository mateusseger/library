package exception;

public class BookNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String isbn;
	
	public BookNotFoundException() {
	}
	
	public BookNotFoundException(String isbn) {
		this.isbn = isbn;
	}
	
	@Override
	public String toString() {
		if(this.isbn != null) {			
			return "OCORREU UM ERRO!\n" + 
					"O livro com código ISBN " + this.isbn + " informado, não existe na lista de livros cadastrados.\n" +
					"Favor informar um livro já cadastrado!\n";
		} else {
			return "OCORREU UM ERRO!\n" + 
					"Livro(s) não encontrado(s)!\n";
		}
	}
}