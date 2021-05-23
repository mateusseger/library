package entities;

public class Book {
	private String title, isbn, publishingComp;
	private Author[] authors;
	private int yearOfPublic;
	private Book prev, next;
	
	public Book(String title, String isbn, Author[] authors, String publishingComp, int yearOfPublic) {
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
		this.publishingComp = publishingComp;
		this.yearOfPublic = yearOfPublic;
	}
	
	// sobrescrita do método toString()
	@Override
	public String toString() {
		String str = "Título: " + this.title +
					 "\nISBN: " + this.isbn +
					 "\nAutor(es): ";
		for(int i = 0; i < this.authors.length; i++) {
			if(i == (this.authors.length-1)) {
				str += this.authors[i].getName() + " - " + this.authors[i].getOriginCountry();
			} else {
				str += this.authors[i].getName() + " - " + this.authors[i].getOriginCountry() + " | ";
			}
		}
		str += "\nEditora: " + this.publishingComp +
			   "\nAno de publicação: " + this.yearOfPublic + 
			   "\n";
		return str;
	}
	
	// sobrescrita do método equals()
	@Override
	public boolean equals(Object o) {
		if(o.getClass() != this.getClass()) {
			return false;
		}
		if(this.isbn.compareTo(((Book)o).isbn) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublishingComp() {
		return publishingComp;
	}

	public void setPublishingComp(String publishingComp) {
		this.publishingComp = publishingComp;
	}

	public Author[] getAuthors() {
		return authors;
	}

	public void setAuthors(Author[] authors) {
		this.authors = authors;
	}
	
	public int getYearOfPublic() {
		return yearOfPublic;
	}

	public void setYearOfPublic(int yearOfPublic) {
		this.yearOfPublic = yearOfPublic;
	}
	
	public Book getPrev() {
		return prev;
	}

	public void setPrev(Book prev) {
		this.prev = prev;
	}

	public Book getNext() {
		return next;
	}

	public void setNext(Book next) {
		this.next = next;
	}
}