package br.com.brestate.top100appitunes.modelo;

public class Category {
	private int id;
	private String term;
	private String scheme;
	private String label;
	
	public Category(int id, String term, String scheme, String label)
	{
		this.id = id;
		this.term = term;
		this.scheme = scheme;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
