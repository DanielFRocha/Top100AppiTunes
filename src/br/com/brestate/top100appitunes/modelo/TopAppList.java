package br.com.brestate.top100appitunes.modelo;

import java.util.Date;
import java.util.List;

public class TopAppList {
	private List<TopApp> topApps;
	private Author author;
	private Date updated;
	private String rights;
	
	public TopAppList(List<TopApp> topApps, Author author, 
				Date updated, String rights)
	{
		this.topApps = topApps;
		this.author = author;
		this.updated = updated;
		this.rights = rights;
	}

	public List<TopApp> getTopApps() {
		return topApps;
	}

	public void setTopApps(List<TopApp> topApps) {
		this.topApps = topApps;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}
}
