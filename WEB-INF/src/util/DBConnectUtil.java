package util;

import java.sql.*;

public class DBConnectUtil {
	public static Connection getConnection(){
		String host="jdbc:postgresql://ZK047E45/bus_manage";
		String username="postgres";
		String password="postgres";

		try {
			Class.forName("org.postgresql.Driver");
			Connection connect=DriverManager.getConnection(host,username,password);
			connect.setAutoCommit(false);
			return connect;
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}

}
