package test;

import java.sql.Connection;

import util.DBConnectUtil;

public class DBConnectUtilTest {
	public static void main(String[] args) {
		Connection con=DBConnectUtil.getConnection();
		System.out.println("DB接続できました。"+ con);

	}


}
