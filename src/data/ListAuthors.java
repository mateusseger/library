package data;

import entities.Author;
import exception.ExistingAuthorException;

public class ListAuthors {
	private Author first;
	// lista encadeada simples
	
	// adiciona no fim (realiza validação para ver se o autor já não existe na lista, caso existir o mesmo não é adicionado)
	public String addAtTheEnd(Author author) {
		try {
			if(this.search(author)) {
				throw new ExistingAuthorException(author.getName(), author.getOriginCountry());
			} else {
				if(this.first == null) {
					this.first = author;
				} else {
					Author aux = this.first;
					while(aux.getNext() != null) {
						aux = aux.getNext();
					}
					aux.setNext(author);
				}
				return "AUTOR CADASTRADO NO FIM DA LISTA!\n";	
			}
		} catch(ExistingAuthorException ea) {
			return "OCORREU UM ERRO!\n" + ea.toString();
		}
	}
	
	// utilizando o método equals sobrescrito na classe Author, para verificar se o autor já existe
	public boolean search(Author author) {
		for(Author aux = this.first; aux != null; aux = aux.getNext()) {
			if (aux.equals(author)) {
				return true;
			}
		}
		return false;
	}
	
	// imprime a lista
	public String showList() {
		if(!this.isEmpty()) {
			String str = "LISTA DE AUTORES:\n";
			int cont = 0;
			for (Author author = this.first; author != null; author = author.getNext()) {
				str+= (cont + 1) + ". NOME: " + author.getName() + " - PAÍS DE ORIGEM: " + author.getOriginCountry() + "\n";
				cont++;
			}
			return str;
		}
		return "LISTA VAZIA!\n";		
	}
	
	// verifica se a lista esta vazia
	public boolean isEmpty() {
		return this.first == null;
	}
}