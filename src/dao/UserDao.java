package dao;

import java.sql.Date;

import common.DBUtils;
import entity.User;

public class UserDao {

	/**
	 * 사용자 가입
	 */
	public int signup(final User user) {
		final String query = "INSERT INTO User(id, username, email, password, created_at) VALUES (?, ?, ?, ?, ?); ";

		return DBUtils.executeUpdate(query, (pstmt) -> {
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			pstmt.setDate(5, new Date(user.getCreatedAt().getTime()));
		});
	}

	/**
	 * 사용자 찾기
	 */
	public User findUserByEmail(final String email) {
		final String query = "SELECT * FROM User WHERE email = ?; ";

		return DBUtils.executeQuery(
			query,
			pstmt -> pstmt.setString(1, email),
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
