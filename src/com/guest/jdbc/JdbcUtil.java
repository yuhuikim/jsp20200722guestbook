package com.guest.jdbc;

import java.sql.Connection;

//깔끔하게 하기 위해서 자주 사용하는 것은 JdbcUtil로 만들어 놓은 것임
public class JdbcUtil {
	// ...하면 여러 개의 파일을 받을 수 있다.
	public static void close(AutoCloseable... resource) {
		try {
			for (AutoCloseable res : resource) {
				res.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}


