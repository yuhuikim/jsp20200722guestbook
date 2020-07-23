package com.guest.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guest.model.Message;
import com.guest.service.GetMessageListService;
import com.guest.service.MessageListView;

/**
 * Servlet implementation class GetMessageListServiceTestServlet
 */
@WebServlet("/GetMessageListServiceTestServlet")
public class GetMessageListServiceTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMessageListServiceTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetMessageListService service = GetMessageListService.getInstance();
		MessageListView list = service.getMessageList(3);
		
		System.out.println(list);
		List<Message> l = list.getMessageList();
		
		for (Message me : l) {
			System.out.println(me);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}