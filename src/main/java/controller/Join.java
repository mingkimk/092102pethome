package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.MemberDAO;
import db.MemberDTO;

/**
 * Servlet implementation class Join
 */
@WebServlet("/Join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Join() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String inputId = request.getParameter("id");
		String inputPwd = request.getParameter("pwd");
		String inputName = request.getParameter("name");
		int inputAge = Integer.parseInt(request.getParameter("age"));
		int inputGender = Integer.parseInt(request.getParameter("gender"));
		String inputInterest = request.getParameter("interest");
		String inputFree = request.getParameter("free");
		
		MemberDAO mDAO = MemberDAO.getInstance();
		MemberDTO o = new MemberDTO();
		o.setId(inputId);
		o.setPwd(inputPwd);
		o.setName(inputName);
		o.setAge(inputAge);
		o.setGender(inputGender);
		o.setInterest(inputInterest);
		o.setFree(inputFree);
		
		mDAO.insert(o);
		
		
		String view = "Login2.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
