package entity;

import java.util.Date;

public class Review {

	private int id;
	private String comment;
	private int rating;
	private Date createdAt;
	private User user;
	private Movie movie;

	public Review() {}

	public Review(
		final String comment,
		final int rating,
		final User user,
		final Movie movie,
		final Date createdAt
	) {
		this.comment = comment;
		this.rating = rating;
		this.user = user;
		this.movie = movie;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
