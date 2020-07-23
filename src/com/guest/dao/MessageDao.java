package com.guest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.guest.jdbc.JdbcUtil;
import com.guest.model.Message;


public class MessageDao {
	private static MessageDao messageDao = new MessageDao();

	public static MessageDao getInstance() {
		return messageDao;
	}

	private MessageDao() {
	}

	public int insert(Connection conn, Message message) throws SQLException {
		// 1. 클래스 로딩 : listener 에서 이미 로딩됨
		// 2. 연결 생성 : 파라미터로 받음
		// 3. statement 생성 : 메소드 내
		// 4. 쿼리 실행 : 메소드 내
		// 5. 결과 처리 : 호출한 곳에서.
		// 6. 자원 닫기
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO guestbook_message "
					+ "(guest_name, password, message) "
					+ "values (?, ?, ?) ");
			pstmt.setString(1, message.getGuestName());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public Message select(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM "
					+ "guestbook_message WHERE message_id=?");
			
			pstmt.setInt(1, messageId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return makeMessageFromResultSet(rs);
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	private Message makeMessageFromResultSet(ResultSet rs) throws SQLException {
		Message message = new Message();
		message.setId(rs.getInt("message_id"));
		message.setGuestName(rs.getString("guest_name"));
		message.setPassword(rs.getString("password"));
		message.setMessage(rs.getString("message"));
		
		return message;
	}
	
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT count(*) FROM "
					+ "guestbook_message");
			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(rs, stmt);
		}
	}
	
	public List<Message> selectList(Connection conn, int firstRow, int endRow) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM"
					+ " guestbook_message "
					+ " ORDER BY message_id "
					+ " DESC LIMIT ?, ?");
			
			pstmt.setInt(1, firstRow - 1);
			pstmt.setInt(2, endRow - firstRow + 1);
			rs = pstmt.executeQuery();
			
			List<Message> messageList = new ArrayList<Message>();
			
			while (rs.next()) {
				messageList.add(makeMessageFromResultSet(rs));
			}
			
			return messageList;
			
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	
	public int delete(Connection conn, int messageId) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("DELETE FROM "
					+ " guestbook_message WHERE message_id=? ");
			pstmt.setInt(1, messageId);
			
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}




