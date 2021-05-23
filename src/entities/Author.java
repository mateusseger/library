package entities;

public class Author {
	private String name, originCountry;
	private Author next;
	
	public Author(String name, String originCountry) {
		this.name = name;
		this.originCountry = originCountry;
	}
	
	@Override
	// sobrescrita do método toString()
	public String toString() {
		String str = "Nome: " + this.name +
					 "\nPaís de origem: " + this.originCountry +
					 "\n";
		return str;
	}
	
	// sobrescrita do método equals()
	@Override
	public boolean equals(Object o) {
		if(o.getClass() != this.getClass()) {
			return false;
		}
		if(this.name.compareToIgnoreCase(((Author)o).name) == 0 && 
				this.originCountry.compareToIgnoreCase(((Author)o).originCountry) == 0) {
			return true;
		} else {
			return false;
		}
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	
	public Author getNext() {
		return next;
	}

	public void setNext(Author next) {
		this.next = next;
	}
}