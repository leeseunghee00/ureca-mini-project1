package dao;

import java.sql.Date;
import java.util.List;

import common.DBUtils;
import entity.Genre;
import entity.Movie;
import entity.Review;
import entity.User;

public class ReviewDao {

	/**
	 * 리뷰 작성
	 */
	public int writeReview(final Review review) {
		final String query = "INSERT INTO Review (user_id, movie_id, comment, rating, created_at) VALUES (?, ?, ?, ?, ?)";

		return DBUtils.executeUpdate(query, (pstmt) -> {
			pstmt.setInt(1, review.getUser().getId());
			pstmt.setInt(2, review.getMovie().getId());
			pstmt.setString(3, review.getComment());
			pstmt.setInt(4, review.getRating());
			pstmt.setDate(5, new Date(review.getCreatedAt().getTime()));
		});
	}

	/**
	 * 리뷰 수정
	 */
	public int updateReview(final Review review) {
		final String query = "UPDATE Review SET comment = ?, rating = ? WHERE id = ?; ";

		return DBUtils.executeUpdate(query, (pstmt) -> {
			pstmt.setString(1, review.getComment());
			pstmt.setInt(2, review.getRating());
			pstmt.setInt(3, review.getId());
		});
	}

	/**
	 * 리뷰 삭제
	 */
	public int deleteReview(final int reviewId) {
		final String query = "DELETE FROM Review WHERE id = ?; ";

		return DBUtils.executeUpdate(query, (pstmt) -> {
			pstmt.setInt(1, reviewId);
		});
	}

	/**
	 * 영화 리뷰 조회
	 */
	public List<Review> getMovieReviews(final int movieId) {
		final String query = "SELECT r.id, u.username, r.rating, r.comment, r.created_at "
							+ "FROM Review r "
							+ "JOIN User u ON r.user_id = u.id "
							+ "WHERE r.movie_id = ? "
							+ "ORDER BY r.created_at; ";

		return DBUtils.executeQueryList(
			query,
			pstmt -> pstmt.setInt(1, movieId),
			rs -> {
				final User user = new User();
				final Review review = new Review();

				user.setUsername(rs.getString("username"));
				review.setId(rs.getInt("id"));
				review.setRating(rs.getInt("rating"));
				review.setComment(rs.getString("comment"));
				review.setCreatedAt(rs.getDate("created_at"));
				review.setUser(user);

				return review;
			}
		);
	}

	/**
	 * 비밀번호로 리뷰 작성자 매칭 확인
	 */
	public String getUserPasswordByReviewId(int reviewId) {
		final String query = "SELECT u.password "
							+ "FROM Review r "
							+ "JOIN User u ON r.user_id = u.id "
							+ "WHERE r.id = ?";
		final String password = null;

		DBUtils.executeQuery(
			query,
			pstmt -> pstmt.setInt(1, reviewId),
			rs -> rs.getString("password")
		);

		return password;
	}

	// 리뷰ID 로 리뷰 조회
	public Review reviewDetail(final int reviewId) {
		final String query = "SELECT * FROM Review WHERE id = ?; ";

		return DBUtils.executeQuery(
			query,
			pstmt -> pstmt.setInt(1, reviewId),
			rs -> {
				final User user = findUserByReviewId(reviewId);
				final Movie movie = findMovieByReviewId(reviewId);
				final Review review = new Review();

				review.setId(rs.getInt("id"));
				review.setComment(rs.getString("comment"));
				review.setRating(rs.getInt("rating"));
				review.setUser(user);
				review.setMovie(movie);

				return review;
			}
		);
	}

	// 리뷰ID 로 작성자 조회
	private User findUserByReviewId(final int reviewId) {
		final String query = "SELECT u.id, u.username, u.email, u.password, u.created_at "
							+ "FROM Review r "
							+ "JOIN User u ON r.user_id = u.id "
							+ "WHERE r.id = ?; ";

		return DBUtils.executeQuery(
			query,
			pstmt -> pstmt.setInt(1, reviewId),
			rs -> {
				final User user = new User();

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setCreatedAt(rs.getDate("created_at"));

				return user;
			}
		);
	}

	// 리뷰ID 로 영화 조회
	private Movie findMovieByReviewId(final int reviewId) {
		final String query = "SELECT m.id, m.title, m.genre, m.director, m.release_year "
							+ "FROM Review r "
							+ "JOIN Movie m ON r.movie_id = m.id "
							+ "WHERE r.id = ?; ";

		return DBUtils.executeQuery(
			query,
			pstmt -> pstmt.setInt(1, reviewId),
			rs -> {
				final Movie movie = new Movie();

				movie.setId(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setGenre(Genre.valueOf(rs.getString("genre")));
				movie.setDirector(rs.getString("director"));
				movie.setReleaseYear(rs.getInt("release_year"));

				return movie;
			}
		);
	}
}
