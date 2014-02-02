package br.com.brestate.top100appitunes.modelo;

public class Id {
	private String label;
	private int id;
	private String bundleId;
	
	public Id(String label, int id, String bundleId)
	{
		this.label = label;
		this.id = id;
		this.bundleId = bundleId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBundleId() {
		return bundleId;
	}

	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}
}
