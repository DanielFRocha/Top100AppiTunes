package br.com.brestate.top100appitunes.modelo;

public class Artist {
	private String label;
	private String url;
	
	public Artist(String label, String url)
	{
		this.label = label;
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
