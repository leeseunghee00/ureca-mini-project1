package dao;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.DBUtils;
import entity.Review;
import entity.User;

public class ReviewDao {

	private static PreparedStatement pstmt;

	/**
	 * 리뷰 작성
	 */
	public int writeReview(final Review review) {
		final String query = "INSERT INTO Review (user_id, movie_id, comment, rating, created_at) VALUES (?, ?, ?, ?, ?)";

		return DBUtils.executeUpdate(query, (s) -> {
			pstmt.setInt(1, review.getUser().getId());
			pstmt.setInt(2, review.getMovie().getId());
			pstmt.setString(3, review.getComment());
			pstmt.setInt(4, review.getRating());
			pstmt.setTimestamp(5, new Timestamp(review.getCreatedAt().getTime()));
		});
	}

	/**
	 * 리뷰 수정
	 */
	public int updateReview(final Review review) {
		final String query = "UPDATE Review SET comment = ?, rating = ? WHERE id = ?; ";
		int ret = -1;

		DBUtils.executeUpdate(query, (s) -> {
			pstmt.setString(1, review.getComment());
			pstmt.setInt(2, review.getRating());
			pstmt.setInt(3, review.getId());
		});

		return ret;
	}

	/**
	 * 리뷰 삭제
	 */
	public int deleteReview(final int reviewId) {
		final String query = "DELETE FROM Review WHERE id = ?; ";

		return DBUtils.executeUpdate(query, (s) -> {
			pstmt.setInt(1, reviewId);
		});
	}

	/**
	 * 영화 리뷰 조회
	 */
	public List<Review> findReviewsByMovieId(int movieId) {
		final String query = "SELECT r.id, u.username, r.comment, r.rating, r.created_at "
							+ "FROM Review r "
							+ "JOIN User u ON r.user_id = u.id "
							+ "WHERE r.movie_id = ? "
							+ "ORDER BY r.created_at; ";

		return DBUtils.executeQuery(
			query,
			pstmt -> pstmt.setInt(1, movieId),
			rs -> {
				final List<Review> reviews = new ArrayList<>();

				while (rs.next()){
					final Review review = new Review();
					final User user = new User();

					review.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					review.setComment(rs.getString("comment"));
					review.setRating(rs.getInt("rating"));
					review.setCreatedAt(rs.getDate("created_at"));
					review.setUser(user);

					reviews.add(review);
				}

				return reviews;
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
}
