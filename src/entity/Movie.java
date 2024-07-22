package entity;

public class Movie {

	private int id;
	private String title;
	private Genre genre;
	private String director;
	private int releaseYear;
	private Manager manager;

	public Movie() {}

	public Movie(
		final int id,
		final String title,
		final Genre genre,
		final String director,
		final int releaseYear,
		final Manager manager
	) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.director = director;
		this.releaseYear = releaseYear;
		this.manager = manager;
	}

	public Movie(
		final int id,
		final String title,
		final Genre genre,
		final String director,
		final int releaseYear
	) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.director = director;
		this.releaseYear = releaseYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
