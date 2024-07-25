package dao;

import java.util.ArrayList;
import java.util.List;

import common.DBUtils;
import entity.Genre;
import entity.Manager;
import entity.Movie;

public class MovieDao {

	/**
	 * 영화 등록
	 */
	public int registerMovie(final Movie movie) {
		final String query = "INSERT INTO Movie (manager_id, title, genre, director, release_year) VALUES (?, ?, ?, ?, ?); ";

		return DBUtils.executeUpdate(query, (pstmt) -> {
			pstmt.setString(1, movie.getManager().getId());
			pstmt.setString(2, movie.getTitle());
			pstmt.setString(3, movie.getGenre().toString());
			pstmt.setString(4, movie.getDirector());
			pstmt.setInt(5, movie.getReleaseYear());
		});
	}

	/**
	 * 영화 수정
	 */
	public int updateMovie(final Movie movie) {
		final String query = "UPDATE Movie SET title = ?, genre = ?, director = ?, release_year = ? WHERE id = ?; ";

		return DBUtils.executeUpdate(query, (pstmt) -> {
			pstmt.setString(1, movie.getTitle());
			pstmt.setString(2, movie.getGenre().toString());
			pstmt.setString(3, movie.getDirector());
			pstmt.setInt(4, movie.getReleaseYear());
			pstmt.setInt(5, movie.getId());
		});
	}

 	/**
	 * 영화 삭제
 	 */
	public int deleteMovie(final int movieId) {
		final String query = "DELETE FROM Movie WHERE id = ?; ";

		return DBUtils.executeUpdate(query, (pstmt) -> {
			pstmt.setInt(1, movieId);
		});
	}

	/**
	 * 영화 목록 조회
	 */
	public List<Movie> movieList() {
		final String query = "SELECT m.id, m.title, m.genre, m.director, m.release_year "
							+ "FROM Movie m "
							+ "JOIN Manager mgr ON m.manager_id = mgr.id "
							+ "ORDER BY m.release_year DESC;";

		return DBUtils.executeQueryList(
			query,
			rs -> {
				final Movie movie = new Movie();
				final Manager manager = new Manager();

				movie.setId(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setGenre(Genre.valueOf(rs.getString("genre")));
				movie.setDirector(rs.getString("director"));
				movie.setReleaseYear(rs.getInt("release_year"));
				movie.setManager(manager);

				return movie;
			}
		);
	}

	/**
	 * 키워드로 영화 검색
	 */
	public List<Movie> searchMovie(final String keyword) {
		final String query = "SELECT id, title, genre, director, release_year "
							+ "FROM Movie "
							+ "WHERE title LIKE ? "
							+ "ORDER BY release_year DESC; ";

		return DBUtils.executeQuery(
			query,
			pstmt -> pstmt.setString(1, "%" + keyword + "%"),
			rs -> {
				final Movie movie = new Movie();
				final List<Movie> movies = new ArrayList<>();

				movie.setId(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setGenre(Genre.valueOf(rs.getString("genre")));
				movie.setDirector(rs.getString("director"));
				movie.setReleaseYear(rs.getInt("release_year"));

				movies.add(movie);

				return movies;
			}
		);
	}

	/**
	 * 영화 상세 조회
	 */
	public Movie movieDetail(final int movieId) {
		final String query = "SELECT * FROM Movie WHERE id = ?; ";

		return DBUtils.executeQuery(
			query,
			pstmt -> pstmt.setInt(1, movieId),
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
