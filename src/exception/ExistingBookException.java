package exception;

public class ExistingBookException extends Exception {
	private static final long serialVersionUID = 1L;
	private String isbn;
	
	public ExistingBookException(String isbn) {
		this.isbn = isbn;
	}
	
	@Override
	public String toString() {
		return "OCORREU UM ERRO!\n" + 
				"O livro com código ISBN " + this.isbn + " informado, já existe na lista de livros cadastrados.\n" +
				"Favor informar um livro diferente!\n";
	}
}