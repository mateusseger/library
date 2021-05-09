package exception;

public class ExistingBookException extends Exception {
	private static final long serialVersionUID = 1L;
	private String isbn;
	
	public ExistingBookException(String isbn) {
		this.isbn = isbn;
	}
	
	@Override
	public String toString() {
		return "O livro com código ISBN " + this.isbn + " informado, já existe na lista de livros cadastrados.\n" +
				"Favor cadastrar um livro novo!\n";
	}
}