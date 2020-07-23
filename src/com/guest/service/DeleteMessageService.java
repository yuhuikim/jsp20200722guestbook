package com.guest.service;

import java.sql.Connection;

import com.guest.dao.MessageDao;
import com.guest.jdbc.ConnectionProvider;
import com.guest.jdbc.JdbcUtil;
import com.guest.model.Message;

public class DeleteMessageService { //460page
	private static DeleteMessageService instance = new DeleteMessageService();
	
	public static DeleteMessageService getInstance() {
		return instance;
	}
	
	private DeleteMessageService() {
		
	}
	
	public String deleteMessage(int messageId, String password) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			MessageDao messageDao = MessageDao.getInstance();
			Message message = messageDao.select(conn, messageId);
			
			if (message == null) {
				return "메시지 없음";
			}
			//패스워드랑 맞지 않으면
			if (!message.matchPassword(password)) {
				return "패스워드 불일치";
			}
			//성공하면 커밋을 해준다.
			messageDao.delete(conn, messageId);
			conn.commit();
			
			return "삭제 성공";
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return "삭제 실패";
	}
}