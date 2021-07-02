package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Employee;
import services.EmployeeServiceImpl;

public class EmpServlet extends HttpServlet {
	
	EmployeeServiceImpl es = new EmployeeServiceImpl();
	public Gson gson = new Gson();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String uri = request.getRequestURI();
		
		System.out.println(uri);
				
		HttpSession session = request.getSession();
				
		session.setMaxInactiveInterval(0);
		
		switch(uri) {
			case "/TRMS/emp":{
				//
				break;
			}
			case "/TRMS/EmpServlet/Login":{
				response.setHeader("Access-Control-Allow-Origin","*");
				String un = request.getParameter("un");
				String pw = request.getParameter("pw");
				//System.out.println(un + " " + pw);
				Employee emp = es.getByUserName(un);
				
				if((emp != null) && (emp.getPass().equals(pw))) {
					//response.getWriter().append("success");
					response.getWriter().append(gson.toJson(emp, Employee.class));
				}
				else {
					response.getWriter().append("fail");
				}
									
				break;
			}
			default:{
				break;
			}
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request,response);
	}
}
