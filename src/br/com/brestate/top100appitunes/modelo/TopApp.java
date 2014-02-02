package br.com.brestate.top100appitunes.modelo;

import java.util.Date;

public class TopApp {
	private String name;
	private String[] imgURLs;
	private String summary;
	private double price;
	private String contentType;
	private String rights;
	private String title;
	private String link;
	private Id id;
	private Artist artist;
	private Category category;
	private Date releaseDate;
	
	
	public TopApp(String name, String[] imgURLs, 
				String summary, double price, String contentType,
				String rights, String title, String link, Id id,
				Artist artist, Category category, Date releaseDate)
	{
		this.name = name;
		this.imgURLs = imgURLs;
		this.summary = summary;
		this.price = price;
		this.contentType = contentType;
		this.rights = rights;
		this.title = title;
		this.link = link;
		this.id = id;
		this.artist = artist;
		this.category = category;
		this.releaseDate = releaseDate;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getImgURLs() {
		return imgURLs;
	}

	public void setImgURLs(String[] imgURLs) {
		this.imgURLs = imgURLs;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	
}
