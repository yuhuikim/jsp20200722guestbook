package com.guest.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guest.service.GetMessageListService;
import com.guest.service.MessageListView;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String pageStr = request.getParameter("page");
		int page = 1;
		
		if (pageStr != null) {
			page = Integer.valueOf(pageStr);
		}
		
		//GetMessageListService는 페이지 넘버링을 가져오는 역할, 여러개 가져올 필요 없으니까 getInstance로 하기
		GetMessageListService service = GetMessageListService.getInstance();
		MessageListView list = service.getMessageList(page);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}