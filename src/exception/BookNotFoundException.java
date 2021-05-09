package exception;

public class BookNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private String isbn;
	private int index;
	
	public BookNotFoundException(String isbn) {
		this.isbn = isbn;
	}
	
	public BookNotFoundException(int index) {
		this.index = index;
	}
	
	@Override
	public String toString() {
		if(this.isbn != null) {			
			return "O livro com código ISBN " + this.isbn + " informado, não existe na lista de livros cadastrados.\n" +
					"Favor informar um livro já cadastrado!\n";
		} else {
			return "O índice " + this.index + " não é válido.\n" +
					"Favor informar um índice existente na lista de livros!\n";
		}
	}
}