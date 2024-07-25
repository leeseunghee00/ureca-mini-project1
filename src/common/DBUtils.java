package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	public static int executeUpdate(String query, SQLConsumer<PreparedStatement> sqlConsumer) {
		int result = -1;

		try	{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			sqlConsumer.accept(pstmt);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(pstmt, conn);
		}

		return result;
	}

	public static <T> T executeQuery(String query, SQLConsumer<PreparedStatement> sqlConsumer, SQLFunction<T, ResultSet> rsMapper) {
		T result = null;

		try	{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			sqlConsumer.accept(pstmt);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rsMapper.apply(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, conn);
		}

		return result;
	}

	public static <T> List<T> executeQueryList(String query, SQLFunction<T, ResultSet> rsMapper) {
		List<T> result = new ArrayList<>();

		try	{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(rsMapper.apply(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, conn);
		}

		return result;
	}

	public static <T> List<T> executeQueryList(String query, SQLConsumer<PreparedStatement> sqlConsumer, SQLFunction<T, ResultSet> rsMapper) {
		List<T> result = new ArrayList<>();

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			sqlConsumer.accept(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(rsMapper.apply(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.releaseConnection(rs, pstmt, conn);
		}

		return result;
	}

	@FunctionalInterface
	public interface SQLConsumer<T> {
		void accept(T t) throws SQLException;
	}

	@FunctionalInterface
	public interface SQLFunction<R, T> {
		R apply(T t) throws SQLException;
	}
}
