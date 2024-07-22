package dao;

import java.sql.Date;
import java.sql.PreparedStatement;

import common.DBUtils;
import entity.User;

public class UserDao {

	private static PreparedStatement pstmt;

	/**
	 * 사용자 가입
	 */
	public int signup(final User user) {
		final String query = "INSERT INTO User(username, email, password, created_at) VALUES (?, ?, ?, ?); ";

		return DBUtils.executeUpdate(query, (s) -> {
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setDate(4, new Date(user.getCreatedAt().getTime()));
		});
	}

	/**
	 * 사용자 찾기
	 */
	public User findUserById(final int id) {
		final String query = "SELECT * FROM User WHERE id = ?; ";

		return DBUtils.executeQuery(
			query,
			pstmt -> pstmt.setInt(1, id),
			rs -> {
				final User user = new User();

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));

				return user;
			}
		);
	}
}
